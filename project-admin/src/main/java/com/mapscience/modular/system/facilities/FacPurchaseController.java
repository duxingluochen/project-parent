package com.mapscience.modular.system.facilities;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.modular.facilities.model.FacPurchase;
import com.mapscience.modular.facilities.service.IFacPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("facPurchase")
public class FacPurchaseController extends BaseController {

    private final String PREFIX = "/modular/facilities/facPurchase/";

    @Autowired
    private IFacPurchaseService facPurchaseService;

    @RequestMapping("/list")
    public String list() {
        return PREFIX + "facPurchaseList";
    }

    @ResponseBody
    @RequestMapping("/getFacPurchaseList")
    public PageInfoBT<FacPurchase> getFacPurchaseList(@RequestParam(value = "facName")String facName) {
        Page<FacPurchase> page = new PageFactory<FacPurchase>().defaultPage();
        List<FacPurchase> list = facPurchaseService.selectPageList(page,facName);
        return this.packForBT(page.setRecords(list));
    }
}
