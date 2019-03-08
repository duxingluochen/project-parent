package com.mapscience.modular.system.warpper;

import com.mapscience.core.base.warpper.BaseControllerWarpper;
import com.mapscience.core.common.constant.factory.ConstantFactory;
import com.mapscience.core.util.ToolUtil;

import java.util.Map;

public class DeptWarpper extends BaseControllerWarpper {

    public DeptWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        String pid = (String) map.get("pid");
        if (ToolUtil.isEmpty(pid)){
            map.put("pName", "--");
        }else {
            map.put("pName", ConstantFactory.me().getDeptName(pid));
        }
    }
}
