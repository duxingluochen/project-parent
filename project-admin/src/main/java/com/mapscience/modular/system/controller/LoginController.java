package com.mapscience.modular.system.controller;

import com.google.code.kaptcha.Constants;
import com.mapscience.config.properties.FtpProperties;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.exception.InvalidKaptchaException;
import com.mapscience.core.log.LogManager;
import com.mapscience.core.log.factory.LogTaskFactory;
import com.mapscience.core.node.MenuNode;
import com.mapscience.core.shiro.ShiroKit;
import com.mapscience.core.shiro.ShiroUser;
import com.mapscience.core.util.ApiMenuFilter;
import com.mapscience.core.util.KaptchaUtil;
import com.mapscience.core.util.ToolUtil;
import com.mapscience.modular.other.model.Picture;
import com.mapscience.modular.other.service.IPictureService;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IMenuService;
import com.mapscience.modular.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.mapscience.core.support.HttpKit.getIp;


@Controller
public class LoginController extends BaseController {
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IPictureService iPictureService;
    @Autowired
    private FtpProperties ftpProperties;
    //默认路径
    private final String PREFIX = "/modular/";

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login";
        }
    }
    /**
     * 跳转到大屏
     */
    @RequestMapping(value = "/toBigScreen", method = RequestMethod.GET)
    public String toBigScreen() {
        //跳转到大屏
        return PREFIX + "bigScreen/index";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/index")
    public String index() {
        List<String> roleList = ShiroKit.getUser().getRoleList();
        //List<MenuNode> menus = menuService.getMenusByRoleIds(roleList);
        //List<MenuNode> titles = MenuNode.buildTitle(menus);
        //titles = ApiMenuFilter.build(titles);
        //getHttpServletRequest().setAttribute("titles", titles);
        return PREFIX + "index";
    }

    /**
     * 跳转到主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        //获取菜单列表
       /* List<String> roleList = ShiroKit.getUser().getRoleList();
        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login";
        }*/
        //跳转到大屏
        return PREFIX + "bigScreen/index";
    }

    /**
     * 登录
     *
     * @return
     */
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali(User user, @RequestParam(value = "remember", required = false) String remember) {
        if (user != null) {
            if (user.getAccount() != null && !"".equals(user.getAccount())) {
                user.setAccount(user.getAccount().trim());
            }
            if (user.getPassword() != null && !"".equals(user.getPassword())) {
                user.setPassword(user.getPassword().trim());
            }
        }
        //验证验证码是否正确
        if (KaptchaUtil.getKaptchaOnOff()) {
            String kaptcha = super.getPara("kaptcha").trim();
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                throw new InvalidKaptchaException();
            }
        }
        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword().toCharArray());
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        currentUser.login(token);
        ShiroUser shiroUser = ShiroKit.getUser();
        User userInfo = userService.selectById(shiroUser.getId());
       /* Picture picture=iPictureService.selectById(userInfo.getAvatar());

        if(picture!=null){
            super.getSession().setAttribute("avatar",picture.getPicPath());
        }*/
        super.getSession().setAttribute("userInfo", userInfo);
        super.getSession().setAttribute("httpPath",ftpProperties.getHttpPath());
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
        ShiroKit.getSession().setAttribute("sessionFlag", true);
        return REDIRECT + "/";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();
        return REDIRECT + "/login";
    }

}
