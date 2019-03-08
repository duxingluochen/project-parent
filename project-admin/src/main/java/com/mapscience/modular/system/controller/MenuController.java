package com.mapscience.modular.system.controller;

import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.Permission;
import com.mapscience.core.common.constant.Const;
import com.mapscience.core.common.constant.factory.ConstantFactory;
import com.mapscience.core.common.constant.state.MenuStatus;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.node.ZTreeNode;
import com.mapscience.core.util.ToolUtil;
import com.mapscience.modular.system.model.Menu;
import com.mapscience.modular.system.service.IMenuService;
import com.mapscience.modular.system.warpper.MenuWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private final String PREFIX = "/modular/system/menu/";

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "")
    public String index() {
        return PREFIX + "menu";
    }

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping(value = "/menu_add")
    public String menuAdd() {
        return PREFIX + "menu_add";
    }

    /**
     * 获取菜单列表
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String menuName, @RequestParam(required = false) String level) {
        /*List<Map<String, Object>> menus = this.menuService.selectMenus(menuName, level);
        return super.warpObject(new MenuWarpper(menus));*/
        return null;
    }


    /**
     * 获取角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/menuTreeListByRoleId/{roleId}")
    public List<ZTreeNode> menuTreeListByRoleId(@PathVariable String roleId) {
        /*List<String> menuIds = this.menuService.getMenuIdsByRoleId(roleId);
        if (ToolUtil.isEmpty(menuIds)) {
            List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
            return roleTreeList;
        } else {
            List<ZTreeNode> roleTreeListByUserId = this.menuService.menuTreeListByMenuIds(menuIds);
            return roleTreeListByUserId;
        }*/
        return null;
    }

    /**
     * 查询菜单树
     * @return
     */
    @RequestMapping(value = "/selectMenuTreeList")
    @ResponseBody
    public List<ZTreeNode> selectMenuTreeList() {
        /*List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;*/
        return null;
    }

    /**
     * 新增菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/add")
    // @BussinessLog(value = "菜单新增", key = "name", dict = MenuDict.class)
    @ResponseBody
    public ResponseVal add(Menu menu) {
       /* //判断是否存在该编号
        String existedMenuName = ConstantFactory.me().getMenuNameByCode(menu.getCode());
        if (ToolUtil.isNotEmpty(existedMenuName)) {
            throw new ProjectException(ProjectStatusEnum.USER_ALREADY_REG);
        }
        menu.setStatus(MenuStatus.ENABLE.getCode());
        if (menu.getLevels() == 1) {
            menu.setPcodes("[0],");
        }
        if (menu.getLevels() == 2) {
            menu.setPcodes("[0],[system],");
        }
        if (menu.getLevels() == 3) {
            menu.setPcodes("[0],[system],[" + menu.getPcode() + "],");
        }
        //添加用户
        boolean b = menuService.insert(menu);
        if (b) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }*/

       return null;
    }

    /**
     * 删除菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/remove")
    // @BussinessLog(value = "删除菜单", key = "menuId", dict = MenuDict.class)
    @ResponseBody
    public ResponseVal remove(String menuId) {
       /* if (ToolUtil.isEmpty(menuId)) {
            throw new ProjectException(ProjectStatusEnum.USER_ALREADY_REG);
        }
        this.menuService.delMenuContainSubMenus(menuId);
        return super.responseBody(ProjectStatusEnum.SUCCESS);*/
       return null;
    }

}
