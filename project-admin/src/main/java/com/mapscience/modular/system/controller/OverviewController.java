package com.mapscience.modular.system.controller;

import com.mapscience.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 总览信息
 */
@Controller
@RequestMapping(value = "overview")
public class OverviewController extends BaseController {

    private final String PREFIX = "/modular/admin/";
    private final String PREFIXONE = "/modular/vehicle/";
    private final String PREFIXTWO = "/modular/achievement/";
    private final String PREFIXTHREE = "/modular/monitoring/";
    private final String PREFIXFOUR = "/modular/statistics/";
    private final String PREFIXFIVE = "/modular/commandCentre/";


    /**
     * 跳转到主页
     *
     * @return
     */
    @RequestMapping(value = "index")
    public String overview() {
        return PREFIX + "welcome";
    }

    @RequestMapping(value = "toVehicle")
    public String toVehicle() {
        return PREFIXONE + "index";
    }

    @RequestMapping(value = "toFinance")
    public String toFinance() {
        return PREFIXFOUR + "finance_statistics";
    }

    @RequestMapping(value = "toMonVacle")
    public String toMonVacle() {
        return PREFIXTHREE + "mon_vacle";
    }

    @RequestMapping(value = "toMonReal")
    public String toMonReal() {
        return PREFIXTHREE + "mon_real";
    }

    @RequestMapping(value = "toSanAchieve")
    public String toSanAchieve() {
        return PREFIXTWO + "san_achievement";
    }

    @RequestMapping(value = "toCentre")
    public String toCentre() {
        return PREFIXFIVE + "commandCentre";
    }


}
