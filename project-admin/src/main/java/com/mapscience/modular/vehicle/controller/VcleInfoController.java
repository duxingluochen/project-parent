package com.mapscience.modular.vehicle.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.modular.other.model.Picture;
import com.mapscience.modular.other.service.IPictureService;
import com.mapscience.modular.system.model.Dept;
import com.mapscience.modular.system.model.Driver;
import com.mapscience.modular.system.service.IDeptService;
import com.mapscience.modular.system.service.IDriverService;
import com.mapscience.modular.system.warpper.DeptWarpper;
import com.mapscience.modular.vehicle.model.VcleBelong;
import com.mapscience.modular.vehicle.model.VcleInfo;
import com.mapscience.modular.vehicle.service.IVcleBelongService;
import com.mapscience.modular.vehicle.service.IVcleInfoService;
import com.mapscience.modular.vehicle.warpper.VcleInfoWarpper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "vcleInfo")
public class VcleInfoController extends BaseController {
    @Autowired
    private IVcleInfoService iVcleInfoService;
    @Autowired
    private IDriverService iDriverService;
    @Autowired
    private IVcleBelongService iVcleBelongService;
    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private IPictureService iPictureService;

    private final String PREFIX = "/modular/vehicle/vcleInfo/";

    @RequestMapping(value = "index")
    public String toVcleInfo() {
        return PREFIX + "vcleInfo";
    }

    @RequestMapping(value = "add")
    public String toVcleInfo_Add(Model model) {
        List<Driver> list = iDriverService.getDriverList();
        if (list.size() > 0) {
            model.addAttribute("driver", list);
        }
        return PREFIX + "vcleInfo_add";
    }

    @RequestMapping(value = "edit")
    public String toVcleInfo_Edit(@RequestParam(value = "vcleId") String vcleId, Model model) {
        List<Driver> listOne = iDriverService.getDriverList();
        if (listOne.size() > 0) {
            model.addAttribute("driver", listOne);
            model.addAttribute("vcleId", vcleId);
        }
        return PREFIX + "vcleInfo_edit";
    }

    /**
     * 获取车辆信息列表
     *
     * @param vcleInfo
     * @return
     */
    @RequestMapping(value = "getVcleInfoList")
    @ResponseBody
    public PageInfoBT<VcleInfo> getVcleInfoList(VcleInfo vcleInfo) {
        Page<VcleInfo> page = new PageFactory<VcleInfo>().defaultPage();
        List<VcleInfo> list = iVcleInfoService.getVcleInfoList(page, vcleInfo);
        return super.packForBT(page.setRecords(list));
    }

    /**
     * 车辆添加
     *
     * @param vcleInfo
     * @return
     */
    @RequestMapping(value = "insert")
    @ResponseBody
    public ResponseVal insert(VcleInfo vcleInfo, String driverId) {
        vcleInfo.setCreateTime(new Date());
        vcleInfo.setUpdateTime(new Date());
        VcleBelong vcleBelong = new VcleBelong();
        Boolean bool = iVcleInfoService.insert(vcleInfo);
        vcleBelong.setCreateTime(new Date());
        vcleBelong.setDriId(driverId);
        vcleBelong.setState(1);
        vcleBelong.setVcleId(vcleInfo.getId());
        vcleBelong.setUpdateTime(new Date());
        Boolean boolOne = iVcleBelongService.insert(vcleBelong);
        if (bool && boolOne) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

    /**
     * 车辆信息修改
     *
     * @param vcleInfo
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public ResponseVal update(VcleInfo vcleInfo, String driverId) {
        vcleInfo.setUpdateTime(new Date());
        Boolean bool = iVcleInfoService.updateById(vcleInfo);
        Boolean boolOne = iVcleBelongService.getDriverByVcleId(vcleInfo.getId(), driverId);
        if (!boolOne) {
            VcleBelong vcleBelong = new VcleBelong();
            vcleBelong.setCreateTime(new Date());
            vcleBelong.setDriId(driverId);
            vcleBelong.setState(1);
            vcleBelong.setVcleId(vcleInfo.getId());
            vcleBelong.setUpdateTime(new Date());
            Boolean boolTwo = iVcleBelongService.insert(vcleBelong);
            if (boolTwo && bool) {
                return super.responseBody(ProjectStatusEnum.SUCCESS);
            }
        }
        if (bool) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

    /**
     * 编辑车辆详情
     *
     * @param vcleId
     * @return
     */
    @RequestMapping(value = "getVcleInfo")
    @ResponseBody
    public Map<String, Object> getVcleInfo(@RequestParam(value = "vcleId") String vcleId) {
        Map<String, Object> map = new HashMap<>();
        VcleInfo vcleInfo = iVcleInfoService.selectById(vcleId);
        List<Map<String, Object>> list = iVcleBelongService.getVcleBelongListByVcleId(vcleId);
        Dept dept = iDeptService.selectById(vcleInfo.getDepartment());
        Picture picture = iPictureService.selectById(vcleInfo.getPicture());
        if (vcleInfo != null && list.size() > 0) {
            map.put("vcleInfo", vcleInfo);
            map.put("list", (List<VcleBelong>) super.warpObject(new VcleInfoWarpper(list)));
            map.put("deptName", dept.getFullname());
            map.put("picPath", picture.getPicPath());
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }

    /**
     * 车辆删除
     *
     * @param vcleInfo
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public ResponseVal delete(VcleInfo vcleInfo) {
        Boolean bool = false;
        if (vcleInfo != null) {
            if (vcleInfo.getId() != null && !"".equals(vcleInfo.getId())) {
                bool = iVcleInfoService.deleteById(vcleInfo.getId());
            }
        }
        if (bool) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

    /**
     * 获取车辆统计数据
     *
     * @return
     */
    @RequestMapping(value = "getVcleInfoProportionlist")
    @ResponseBody
    public List getVcleInfos() {
        List list = iVcleInfoService.getVcleInfoProportionlist();
        return list;
    }
}
