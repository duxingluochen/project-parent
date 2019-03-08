package com.mapscience.modular.system.facilities;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.modular.facilities.model.FacInfo;
import com.mapscience.modular.facilities.model.FacStock;
import com.mapscience.modular.facilities.model.FacUse;
import com.mapscience.modular.facilities.service.IFacInfoService;
import com.mapscience.modular.facilities.service.IFacStockService;
import com.mapscience.modular.facilities.service.IFacUseService;
import com.mapscience.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/facStock")
public class FacStockController extends BaseController {
    private final String PREFIX = "/modular/facilities/facStock/";

    @Autowired
    private IFacStockService facStockService;

    @Autowired
    private IFacInfoService facInfoService;

    @Autowired
    private IFacUseService facUseService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
    public String list() {
        return PREFIX + "facStockList";
    }

    @ResponseBody
    @RequestMapping("/getFacStockList")
    public PageInfoBT<FacStock> getFacStockList(@RequestParam(value = "facName")String facName, @RequestParam(value = "facType")String facType) {
        Page<FacStock> page = new PageFactory<FacStock>().defaultPage();
        List<FacStock> list = facStockService.selectPageList(page,facName,facType);
        return this.packForBT(page.setRecords(list));
    }

    @ResponseBody
    @RequestMapping("/getFacStockById")
    public FacStock getFacStockById(@RequestParam(value = "id")String id) {
        FacStock facStock = facStockService.selectById(id);
        return facStock;
    }

    //设备库存详情页面
  /*  @RequestMapping("/facStockInfo")
    public String facStockInfo(@RequestParam(value = "id")String id, Model model) {
        model.addAttribute("facList", facInfoService.selectList(new EntityWrapper<>()));
        model.addAttribute("user", userService.getUser());
        if (id != null && !id.isEmpty()) {
            model.addAttribute("facStock", facStockService.selectById(id));
        }
        return PREFIX + "facStockInfo";
    }*/

    //新增或编辑
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public ResponseVal addOrUpdate(FacStock facStock) {
        String id = facStock.getId();
        if (id == null || id.isEmpty()) {
            facStock.setId(UUID.randomUUID().toString().replace("-",""));
            facStock.setVersion(1);
        }
        if(facStockService.insertOrUpdate(facStock)) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

    //删除
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseVal delete(@RequestParam(value = "id")String id) {
        //FacStock facStock = facStockService.selectById(id);
        Wrapper<FacUse> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("facstock_Id", id);
        List<FacUse> list = facUseService.selectList(wrapper);
        if(list.size() > 0) {
            return super.responseBody(ProjectStatusEnum.FAC_STOCK_USE_ERROR);
        } else if (facStockService.deleteById(id)) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

}
