package com.mapscience.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.config.properties.FtpProperties;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.BussinessLog;
import com.mapscience.core.common.annotion.Permission;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.constant.Const;
import com.mapscience.core.common.constant.dictmap.UserDict;
import com.mapscience.core.common.constant.state.ManagerStatus;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.datascope.DataScope;
import com.mapscience.core.exception.ProjectException;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.core.shiro.ShiroKit;
import com.mapscience.core.util.ToolUtil;
import com.mapscience.modular.other.service.IPictureService;
import com.mapscience.modular.system.model.Dict;
import com.mapscience.modular.system.model.Driver;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IDictService;
import com.mapscience.modular.system.service.IDriverService;
import com.mapscience.modular.system.service.IRoleService;
import com.mapscience.modular.system.service.IUserService;
import com.mapscience.modular.system.warpper.UserWarpper;
import org.apache.commons.net.ftp.FTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

    private final String PREFIX = "/modular/system/user/";

    @Autowired
    private IUserService userService;

    @Autowired
    private IDriverService driverService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private FtpProperties ftpProperties;

    @RequestMapping(value = "index")
    public String user(Model model) {
        model.addAttribute("role", roleService.selectList(new EntityWrapper<>()));
        return PREFIX + "user";
    }

    @RequestMapping(value = "user_add")
    public String user_add() {
        return PREFIX + "user_add";
    }

    @RequestMapping(value = "user_edit")
    public String user_edit( @RequestParam(value = "id") String id, Model model) {
        /*User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("pic", pictureService.selectById(user.getAvatar()));
        model.addAttribute("driver", driverService.getByUserId(user.getId()));
        return PREFIX + "user_edit";*/
        return null;
    }

    /**
     * 跳转到角色分配页面
     */
    //@RequiresPermissions("/mgr/role_assign")  //利用shiro自带的权限检查
    @Permission
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable String userId, Model model) {
        /*if (ToolUtil.isEmpty(userId)) {
            throw new ProjectException(ProjectStatusEnum.REQUEST_NULL);
        }
        User user = userService.selectById(userId);
        model.addAttribute("userId", user.getId());
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_roleassign";*/
        return null;
    }

    /**
     * 分页查询用户
     *
     * @param name
     * @param roleid
     * @param deptid
     * @return
     */
    @ResponseBody
    @Permission
    @RequestMapping(value = "getUserList")
    public PageInfoBT<User> getUserList(@RequestParam(required = false) String name, @RequestParam(required = false) String roleid, @RequestParam(required = false) String deptid) {
      /*  Page<User> page = new PageFactory<User>().defaultPage();
        if (ShiroKit.isAdmin()) {
            List<Map<String, Object>> users = userService.selectUsers(page, null, name, roleid, deptid);
            return this.packForBT(page.setRecords((List<User>) super.warpObject(new UserWarpper(users))));
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            List<Map<String, Object>> users = userService.selectUsers(page, dataScope, name, roleid, deptid);
            return this.packForBT(page.setRecords((List<User>) super.warpObject(new UserWarpper(users))));
        }*/
        return null;

    }


    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add")
    @Permission(Const.ADMIN_NAME)
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
    public ResponseVal add(User user, Driver driver) {
        /*//验证用户是否被注册
        User byAccount = userService.getByAccount(user.getAccount());
        if (byAccount != null) {
            throw new ProjectException(ProjectStatusEnum.USER_ALREADY_REG);
        }

        // 完善账号信息
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreatetime(new Date());

        //添加用户
        boolean b = userService.insert(user);
        if (b) {
            if(driver.getLicenseNumber() == null & driver.getLicenseNumber().isEmpty()) {
                //非司机，直接返回结果
                return super.responseBody(ProjectStatusEnum.SUCCESS);
            } else {
                //保存司机信息
                driver.setUserId(user.getId());
                driver.setStatus(1);
                b = driverService.insert(driver);
                if (b) {
                    return super.responseBody(ProjectStatusEnum.SUCCESS);
                } else {
                    userService.deleteById(user.getId());
                    return super.responseBody(ProjectStatusEnum.ERROR);
                }
            }
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }*/
        return null;
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update")
    @Permission(Const.ADMIN_NAME)
    public ResponseVal update(User user, Driver driver, @RequestParam(value = "driverId")String driverId) {
       /* boolean b = userService.insertOrUpdate(user);
        if (b) {
            if(driver.getLicenseNumber() == null || driver.getLicenseNumber().isEmpty()) {
                if(driverId != null && !driverId.isEmpty()) {
                    //司机变为非司机，修改司机状态为删除
                    Driver old = driverService.selectById(driverId);
                    old.setStatus(2);
                    driverService.updateById(old);
                }
                return super.responseBody(ProjectStatusEnum.SUCCESS);
            } else {
                //保存司机信息
                driver.setUserId(user.getId());
                driver.setId(driverId);
                driver.setStatus(1);
                b = driverService.insertOrUpdate(driver);
                if (b) {
                    return super.responseBody(ProjectStatusEnum.SUCCESS);
                } else {
                    userService.deleteById(user.getId());
                    return super.responseBody(ProjectStatusEnum.ERROR);
                }
            }
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }*/
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "freeze")
    @Permission(Const.ADMIN_NAME)
    @BussinessLog(value = "冻结/启用用户", key = "userId", dict = UserDict.class)
    public ResponseVal freeze(@RequestParam(value = "userId") String userId, @RequestParam(value = "userStatus") String userStatus) {
       /* if (ToolUtil.isEmpty(userId)) {
            throw new ProjectException(ProjectStatusEnum.REQUEST_NULL);
        }
        //不能冻结管理员
        if (Const.ADMIN_ID.equals(userId)) {
            throw new ProjectException(ProjectStatusEnum.CANT_FREEZE_ADMIN);
        }
        //判断当前用户是否有操作权限
        assertAuth(userId);
        int status = 0;
        if ("启用".equals(userStatus)) {//解除冻结
            status = userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        } else if ("冻结".equals(userStatus)) {
            status = userService.setStatus(userId, ManagerStatus.OK.getCode());
        }
        if (status > 0) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        }
        return super.responseBody(ProjectStatusEnum.ERROR);*/
        return null;
    }


    /**
     * 删除管理员（逻辑删除）
     */
    @Permission
    @ResponseBody
    @RequestMapping("/delete")
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    public ResponseVal delete(@RequestParam(value = "userId") String userId) {
        /*if (ToolUtil.isEmpty(userId)) {
            throw new ProjectException(ProjectStatusEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ProjectException(ProjectStatusEnum.CANT_DELETE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.DELETED.getCode());
        return super.responseBody(ProjectStatusEnum.SUCCESS);*/
        return null;
    }


    /**
     * 分配角色
     */
    @ResponseBody
    @RequestMapping("/setRole")
    @Permission(Const.ADMIN_NAME)
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    public ResponseVal setRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {
       /* if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new ProjectException(ProjectStatusEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ProjectException(ProjectStatusEnum.CANT_CHANGE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setRoles(userId, roleIds);
        return super.responseBody(ProjectStatusEnum.SUCCESS);*/
        return null;
    }


    /**
     * 重置管理员的密码
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseVal reset(@RequestParam(value = "userId") String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ProjectException(ProjectStatusEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        User user = this.userService.selectById(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        boolean b = this.userService.updateById(user);
        return super.responseBody(ProjectStatusEnum.SUCCESS);

    }


    @ResponseBody
    @RequestMapping(value = "getUser")
    public List<User> getUser() {
        /*List<User> user = userService.getUser();
        return user;*/
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "getUserById")
    public User getUserById(User user) {
        /*User userById = userService.getUserById(user.getId());
        return userById;*/
        return null;
    }


    /**
     * 判断当前登录的用户是否有操作这个用户的权限
     */
    private void assertAuth(String userId) {
        /*if (ShiroKit.isAdmin()) {
            return;
        }
        List<String> deptDataScope = ShiroKit.getDeptDataScope();
        User user = this.userService.selectById(userId);
        String deptid = user.getDeptid();
        if (deptDataScope.contains(deptid)) {
            return;
        } else {
            throw new ProjectException(ProjectStatusEnum.NO_PERMITION);
        }*/

    }

    @ResponseBody
    @RequestMapping(value = "getPieForUserDept")
    public List<Map<String, Object>> getPieForUserDept() {
        /*List<Map<String, Object>> list = userService.selectUserGroupByDept();
        return list;*/
        return null;
    }
}



