package com.mapscience.modular.vehicle.warpper;

import com.mapscience.core.base.warpper.BaseControllerWarpper;
import com.mapscience.core.common.constant.factory.ConstantFactory;
import com.mapscience.core.util.ToolUtil;

import java.util.Map;

public class VcleInfoWarpper extends BaseControllerWarpper {

    public VcleInfoWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        String driId = (String) map.get("dri_id");
        if (ToolUtil.isEmpty(driId)){
            map.put("driName", "--");
        }else {
            map.put("driName", ConstantFactory.me().getDriverName(driId));
        }
    }
}
