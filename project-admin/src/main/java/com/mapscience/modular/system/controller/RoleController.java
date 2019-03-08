package com.mapscience.modular.system.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.BussinessLog;
import com.mapscience.core.common.annotion.Permission;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.constant.Const;
import com.mapscience.core.common.constant.dictmap.RoleDict;
import com.mapscience.core.common.constant.factory.ConstantFactory;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.node.ZTreeNode;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.core.util.Convert;
import com.mapscience.core.util.ToolUtil;
import com.mapscience.modular.system.model.Role;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IRoleService;
import com.mapscience.modular.system.service.IUserService;
import com.mapscience.modular.system.warpper.RoleWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "role")
public class RoleController extends BaseController {

    private final String PREFIX = "/modular/system/role/";

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "role";
    }

    /**
     * 跳转到角色分配
     */
    @Permission
    @RequestMapping(value = "/role_assign/{roleId}")
    public String roleAssign(@PathVariable("roleId") String roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ProjectException(ProjectStatusEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(roleId));
        return PREFIX + "role_assign";
    }

    /**
     * 配置权限
     */
    @ResponseBody
    @Permission(Const.ADMIN_NAME)
    @RequestMapping("/setAuthority")
    @BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    public ResponseVal setAuthority(@RequestParam("roleId") String roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new ProjectException(ProjectStatusEnum.REQUEST_NULL);
        }
        //this.roleService.setAuthority(roleId, ids);
        return super.responseBody(ProjectStatusEnum.SUCCESS);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPageRole")
    public PageInfoBT<Role> getPageRole(Role role) {
        Page<Role> page = new PageFactory<Role>().defaultPage();
       /* List<Map<String, Object>> list = roleService.selectRoles(page,super.getPara("roleName"));
        return super.packForBT(page.setRecords((List<Role>) super.warpObject(new RoleWarpper(list))));*/
       return null;
    }


    /**
     * 获取角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable String userId) {
      /*  User theUser = this.userService.selectById(userId);
        String roleid = theUser.getRoleid();
        if (ToolUtil.isEmpty(roleid)) {
            List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
            return roleTreeList;
        } else {
            String[] strArray = Convert.toStrArray(",", roleid);
            List<ZTreeNode> roleTreeListByUserId = this.roleService.roleTreeListByRoleId(strArray);
            return roleTreeListByUserId;
        }*/
      return null;
    }

    /**
     * 获取角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/roleTreeList")
    public List<ZTreeNode> roleTreeList() {
        /*List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
        return roleTreeList;*/
        return null;
    }

    /**
     * 跳转角色新增页面
     */
    @RequestMapping(value = "/role_add")
    public String role_add(@RequestParam(value = "id",required = false) String id, Model model) {
        /*model = roleService.getRoleAddModel(id, model);
        return PREFIX + "role_add";*/
        return "";
    }

    /**
     * 添加角色
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public ResponseVal add(Role role) {
        /*if (roleService.addRole(role) > 0) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }*/
        return null;
    }

    /**
     * 删除角色
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResponseVal delete(@RequestParam(value = "id",required = false) String id) {
        /*if (roleService.delRole(id) > 0) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }*/
        return null;
    }

}
