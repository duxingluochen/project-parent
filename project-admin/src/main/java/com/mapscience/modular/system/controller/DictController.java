package com.mapscience.modular.system.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.node.ZTreeNode;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.modular.system.model.Dept;
import com.mapscience.modular.system.model.Dict;
import com.mapscience.modular.system.service.IDictService;
import com.mapscience.modular.system.warpper.DictWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 字典控制
 */
@Controller
@RequestMapping(value = "dict")
public class DictController extends BaseController {

    private final String PREFIX = "/modular/system/dict/";

    @Autowired
    private IDictService dictService;

    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "dict";
    }

    /**
     * 分页查询字典
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPageList")
    public PageInfoBT<Dict> getPageList(Dict dict) {
        Page<Dict> page = new PageFactory<Dict>().defaultPage();
        List<Map<String, Object>> list = dictService.list(page,dict.getName());
        return super.packForBT(page.setRecords((List<Dict>) super.warpObject(new DictWarpper(list))));
    }

    @ResponseBody
    @RequestMapping(value = "getDictList/{code}")
    public List<ZTreeNode> getDictList(@PathVariable String code) {
        List<ZTreeNode> list = dictService.selectTreeListByParentCode(code);
        return list;
    }
}
