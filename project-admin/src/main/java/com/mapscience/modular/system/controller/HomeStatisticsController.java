package com.mapscience.modular.system.controller;

import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.system.model.Company;
import com.mapscience.modular.system.service.impl.CompanyServiceImpl;
import com.mapscience.modular.system.service.impl.EmployeeServiceImpl;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mapscience.core.base.controller.BaseController;
/**
 * 首页统计控制器
 */
public class HomeStatisticsController extends BaseController{

    /**
     * 公司
     */
    @Autowired
    private CompanyServiceImpl companyService;

    /**
     * 人员
     */
    @Autowired
    private EmployeeServiceImpl employeeService;

    /**
     * 查询公司信息及坐标
     * @param organize
     * @return
     */
    @RequestMapping("findOrgList.do")
    @ResponseBody
    public ResponseVal findOrgList(Company organize) {
       return null;
    }

}
