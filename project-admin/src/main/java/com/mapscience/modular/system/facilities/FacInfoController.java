package com.mapscience.modular.system.facilities;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.modular.facilities.model.FacInfo;
import com.mapscience.modular.facilities.service.IFacInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/facinfo")
public class FacInfoController extends BaseController {
    private final String PREFIX = "/modular/facilities/facInfo/";

    @Autowired
    private IFacInfoService facInfoService;

    @RequestMapping("/index")
    public String fac_index() {
        return PREFIX + "facilitiesList";
    }

    @ResponseBody
    @RequestMapping("/getFacPageList")
    public PageInfoBT<FacInfo> getFacPageList(FacInfo facInfo) {
        Page<FacInfo> page = new PageFactory<FacInfo>().defaultPage();
        List<FacInfo> list = facInfoService.selectFacPageList(page,facInfo);
        return this.packForBT(page.setRecords(list));
    }

    //设备详情页面
    @RequestMapping("/facilitiesInfo")
    public String facilitiesInfo(@RequestParam(value = "id")String id, Model model) {
        if (id != null && !id.isEmpty()) {
            model.addAttribute("fac", facInfoService.selectById(id));
        }
        return PREFIX + "facilitiesInfo";
    }

    //新增
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public ResponseVal add(FacInfo facInfo) {
        String id = facInfo.getId();
        if (id == null || id.isEmpty()) {
            facInfo.setId(UUID.randomUUID().toString().replace("-",""));
        }
        if(facInfoService.insertOrUpdate(facInfo)) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

    //删除
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseVal del(@RequestParam(value = "id")String id) {
        if(facInfoService.deleteById(id)) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }
}
