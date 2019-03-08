package com.mapscience.modular.system.warpper;

import com.mapscience.core.base.warpper.BaseControllerWarpper;
import com.mapscience.core.common.constant.factory.ConstantFactory;

import java.util.Map;

public class FacMaintainWarpper extends BaseControllerWarpper {
    public FacMaintainWarpper(Object obj) { super(obj); }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        String id = (String) map.get("id");
        map.put("schedule", ConstantFactory.me().getFlowNodeSchedule(id));
    }

}
