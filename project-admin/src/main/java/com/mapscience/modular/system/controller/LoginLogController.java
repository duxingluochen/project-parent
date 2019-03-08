package com.mapscience.modular.system.controller;

import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.annotion.BussinessLog;
import com.mapscience.core.common.annotion.Permission;
import com.mapscience.core.common.annotion.factory.PageFactory;
import com.mapscience.core.common.constant.Const;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.core.page.PageInfoBT;
import com.mapscience.modular.system.model.LoginLog;
import com.mapscience.modular.system.service.ILoginLogService;
import com.mapscience.modular.system.warpper.LogWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "loginLog")
public class LoginLogController extends BaseController {

    private final String PREFIX = "/modular/system/log/";


    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "login_log";
    }

    @Autowired
    private ILoginLogService loginLogService;


    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public PageInfoBT<LoginLog> list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName) {
        /*Page<LoginLog> page = new PageFactory<LoginLog>().defaultPage();
        List<Map<String, Object>> result = loginLogService.getLoginLogs(page, beginTime, endTime, logName, page.getOrderByField(), page.isAsc());
        System.out.println(page.setRecords((List<LoginLog>) super.warpObject(new LogWarpper(result))));
        return super.packForBT(page.setRecords((List<LoginLog>) super.warpObject(new LogWarpper(result))));*/
        return null;
    }


    /**
     * 清空日志
     */
    @BussinessLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseVal delLog() {
        boolean b = SqlRunner.db().delete("delete from sys_login_log");
        if (b) {
            return super.responseBody(ProjectStatusEnum.SUCCESS);
        } else {
            return super.responseBody(ProjectStatusEnum.ERROR);
        }

    }
}
