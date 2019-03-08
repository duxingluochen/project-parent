package com.mapscience.modular.system.facilities;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.core.shiro.ShiroUser;
import com.mapscience.modular.facilities.model.FacMaintain;
import com.mapscience.modular.facilities.service.IFacInfoService;
import com.mapscience.modular.facilities.service.IFacMaintainService;
import com.mapscience.modular.other.model.Portflow;
import com.mapscience.modular.other.service.IPictureService;
import com.mapscience.modular.other.service.IPortflowService;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.warpper.FacMaintainWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/facMaintain")
public class FacMaintainController extends BaseController {
    private final String PREFIX = "/modular/facilities/facMaintain/";

    @Autowired
    private IFacMaintainService facMaintainService;

    @Autowired
    private IFacInfoService facInfoService;

    @Autowired
    private IPortflowService portflowService;

    @Autowired
    private IPictureService pictureService;

    @RequestMapping("/list")
    public String list() {
        return PREFIX + "facMaintainList";
    }

   /* @ResponseBody
    @RequestMapping("/getFacMaintainList")
    public PageInfoBT<FacMaintain> getFacMaintainList(@RequestParam(value = "facName")String facName, @RequestParam(value = "selection")String selection) {
        User user = (User)super.getSession().getAttribute("userInfo");
        Page<FacMaintain> page = new PageFactory<FacMaintain>().defaultPage();
        List<Map<String, Object>> list = facMaintainService.selectPageList(page,facName,selection,user.getId());
        return super.packForBT(page.setRecords((List<FacMaintain>) super.warpObject(new FacMaintainWarpper(list))));//this.packForBT(page.setRecords(list));
    }*/

    @RequestMapping("/facMaintainInfo")
    public String facMaintainInfo(@RequestParam(value = "id")String id, Model model) {
        model.addAttribute("facList", facInfoService.selectList(new EntityWrapper<>()));
        if(id != null && !id.isEmpty()) {
            FacMaintain facMaintain = facMaintainService.selectById(id);
            model.addAttribute("facMaintain", facMaintain);
            //维护前照片
            String beforePhotos = facMaintain.getBeforePhotos();
            if (beforePhotos != null && !beforePhotos.isEmpty()) {
                model.addAttribute("picBefore", pictureService.selectById(beforePhotos));
            }
            //维护后照片
            String afterPhotos = facMaintain.getAfterPhotos();
            if (afterPhotos != null && !afterPhotos.isEmpty()) {
                model.addAttribute("picAfter", pictureService.selectById(afterPhotos));
            }
            //判断下一节点是否被申领，若被申领，则不显示提交按钮
            if (id != null && !id.isEmpty() && portflowService.getSecondNodeState(id) != 0){
                model.addAttribute("edit", "N");
            }
        }
        return PREFIX  + "facMaintainInfo";
    }

  /*  //新增或编辑
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public ResponseVal addOrUpdate(FacMaintain facMaintain) {
        User userInfo = (User)super.getSession().getAttribute("userInfo");
        switch (facMaintainService.addOrUpdate(facMaintain, userInfo.getId())){
            case 200:
                return super.responseBody(ProjectStatusEnum.SUCCESS);
            case 1:
                return super.responseBody(ProjectStatusEnum.FLOW_REALY_APPLY);
            case 2:
                return super.responseBody(ProjectStatusEnum.FLOW_REALY_ACCESS);
            case 3:
                return super.responseBody(ProjectStatusEnum.FLOW_REALY_REJECT);
            default:
                return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }*/

    //进入设备审批界面
    @RequestMapping("/facMaintainApproval")
    public String facMaintainApproval(@RequestParam(value = "id")String id, @RequestParam(value = "flowId")String flowId, Model model) {
        model.addAttribute("facList", facInfoService.selectList(new EntityWrapper<>()));
        if(id != null && !id.isEmpty() && id != null && !id.isEmpty()) {
            FacMaintain facMaintain = facMaintainService.selectById(id);
            model.addAttribute("facMaintain", facMaintain);
            //维护前照片
            String beforePhotos = facMaintain.getBeforePhotos();
            if (beforePhotos != null && !beforePhotos.isEmpty()) {
                model.addAttribute("picBefore", pictureService.selectById(beforePhotos));
            }
            //维护后照片
            String afterPhotos = facMaintain.getAfterPhotos();
            if (afterPhotos != null && !afterPhotos.isEmpty()) {
                model.addAttribute("picAfter", pictureService.selectById(afterPhotos));
            }
            Portflow portflow = portflowService.selectById(flowId);
            model.addAttribute("portflow", portflow);
            //判断当前节点是否已流转
            switch (portflow.getState()) {
                case 0:
                    //申领任务
                    portflowService.taskApply(flowId);
                case 1:
                    break;
                case 2:
                case 3:
                    //标示为不可编辑
                    model.addAttribute("edit", "N");
                    break;
                default:
                    break;
            }
        }
        return PREFIX  + "facMaintainApproval";
    }
    @ResponseBody
    @RequestMapping("/saveApproval")
    public ResponseVal saveApproval(Portflow portflow) {
        try {
            if(portflowService.next(portflow.getId(),portflow.getOpinion())){
                return super.responseBody(ProjectStatusEnum.SUCCESS);
            } else {
                return super.responseBody(ProjectStatusEnum.ERROR);
            }
        } catch (Exception e) {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

    //撤销并删除
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseVal delete(@RequestParam(value = "id")String id) {
        FacMaintain facMaintain = facMaintainService.selectById(id);
        if (facMaintainService.deleteById(id)) {
            if (portflowService.flowRevoke(id)) {
                return super.responseBody(ProjectStatusEnum.SUCCESS);
            } else {
                facMaintainService.insert(facMaintain);
                switch (portflowService.getSecondNodeState(id)) {
                    case 1:
                        return super.responseBody(ProjectStatusEnum.FLOW_REALY_APPLY);
                    case 2:
                        return super.responseBody(ProjectStatusEnum.FLOW_REALY_ACCESS);
                    case 3:
                        return super.responseBody(ProjectStatusEnum.FLOW_REALY_REJECT);
                    default:
                }
            }
        }
        return super.responseBody(ProjectStatusEnum.ERROR);

    }
}
