package com.mapscience.modular.system.warpper;

import com.mapscience.core.base.warpper.BaseControllerWarpper;
import com.mapscience.core.common.constant.factory.ConstantFactory;
import com.mapscience.core.constant.IsMenu;

import java.util.Map;

public class MenuWarpper extends BaseControllerWarpper {
    public MenuWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getMenuStatusName((Integer) map.get("status")));
        map.put("isMenuName", IsMenu.valueOf((Integer) map.get("ismenu")));
    }
}
