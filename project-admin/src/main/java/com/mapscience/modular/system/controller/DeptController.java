package com.mapscience.modular.system.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.Permission;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.constant.Const;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.node.ZTreeNode;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.modular.system.model.Dept;
import com.mapscience.modular.system.model.Role;
import com.mapscience.modular.system.service.IDeptService;
import com.mapscience.modular.system.warpper.DeptWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "dept")
public class DeptController extends BaseController {

    private final String PREFIX = "/modular/system/dept/";

    @Autowired
    private IDeptService deptService;

    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "dept";
    }

    /**
     * 获取部门列表
     *
     * @param dept
     * @return
     */
    @ResponseBody
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "getList")
    public PageInfoBT<Dept> getList(Dept dept) {
        Page<Dept> page = new PageFactory<Dept>().defaultPage();
        List<Map<String, Object>> list = deptService.list(page,dept.getFullname());
        return super.packForBT(page.setRecords((List<Dept>) super.warpObject(new DeptWarpper(list))));
    }

    /**
     * 获取部门的树结构
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "tree")
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 跳转部门新增页面
     */
    @RequestMapping(value = "/dept_add")
    public String dept_add(@RequestParam(value = "id",required = false) String id, Model model) {
        model = deptService.getDeptAddModel(id, model);
        return PREFIX + "dept_add";
    }

    /**
     * 新增部门
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public ResponseVal add(Dept dept) {
        if (deptService.addDept(dept) > 0) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

    /**
     * 新增部门
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResponseVal delete(@RequestParam(value = "id",required = false) String id) {
        if (deptService.delDept(id) > 0) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }
    }

}
