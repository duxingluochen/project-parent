package com.mapscience.modular.system.facilities;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.modular.facilities.model.FacStock;
import com.mapscience.modular.facilities.model.FacUse;
import com.mapscience.modular.facilities.service.IFacInfoService;
import com.mapscience.modular.facilities.service.IFacStockService;
import com.mapscience.modular.facilities.service.IFacUseService;
import com.mapscience.modular.system.service.IUserService;
import com.mapscience.modular.system.warpper.FacUseWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("facUse")
public class FacUseController extends BaseController {
    private final String PREFIX = "/modular/facilities/facUse/";

    @Autowired
    private IFacUseService facUseService;

    @Autowired
    private IFacStockService facStockService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
    public String fac_index() {
        return PREFIX + "facUseList";
    }

    @ResponseBody
    @RequestMapping("/getPageList")
    public PageInfoBT<FacUse> getPageList(@RequestParam(value = "facName")String facName) {
        Page<FacUse> page = new PageFactory<FacUse>().defaultPage();
        List<Map<String, Object>> list = facUseService.selectPageList(page,facName);
        return this.packForBT(page.setRecords((List<FacUse>) super.warpObject(new FacUseWarpper(list))));
    }

    //设备详情页面
   /* @RequestMapping("/facUseInfo")
    public String facilitiesInfo(@RequestParam(value = "id")String id, Model model) {
        model.addAttribute("facList", facStockService.selectStockAndFacName());
        model.addAttribute("user", userService.getUser());
        if (id != null && !id.isEmpty()) {
            model.addAttribute("facUse", facUseService.selectById(id));
        }
        return PREFIX + "facUseInfo";
    }*/

    //新增或编辑
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public ResponseVal addOrUpdate(FacUse facUse) {
        if(facUseService.addOrUpdate(facUse)) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

    //删除
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseVal delete(@RequestParam(value = "id")String id) {
        if(facUseService.deleteUse(id)) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

}
