package com.mapscience.modular.code.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.mapscience.core.base.controller.BaseController;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.status.ProjectStatusEnum;
import com.mapscience.generator.action.config.WebGeneratorConfig;
import com.mapscience.generator.action.model.CodeProperties;
import com.mapscience.modular.code.factory.DefaultTemplateFactory;
import com.mapscience.modular.code.service.TableService;
import com.mapscience.modular.system.model.User;
import com.mapscience.modular.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "code")
public class CodeController extends BaseController {

    @Autowired
    private TableService tableService;

    @Value("${spring.datasource.url}")
    private String url;

    //默认路径
    private final String PREFIX = "/modular/system/code/";

    @RequestMapping(value = "")
    public String blackboard(Model model) {
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("params", DefaultTemplateFactory.getDefaultParams());
        model.addAttribute("templates", DefaultTemplateFactory.getDefaultTemplates());
        return PREFIX + "code";
    }
    @ResponseBody
    @ApiOperation("生成代码")
    @PostMapping(value = "/generate")
    public ResponseVal generate(CodeProperties codeProperties) {
        codeProperties.setUrl(url);
        codeProperties.setUserName("root");
        codeProperties.setPassword("123456");
        WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(codeProperties);
        webGeneratorConfig.doMpGeneration();
        return responseBody(ProjectStatusEnum.SUCCESS);
    }

    @RequestMapping(value = "pp")
    public void test(){
        System.out.println(111);
    }



}
