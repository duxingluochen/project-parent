package com.mapscience.modular.system.warpper;

import com.mapscience.core.base.warpper.BaseControllerWarpper;
import com.mapscience.core.common.constant.factory.ConstantFactory;

import java.util.Map;

public class UserWarpper extends BaseControllerWarpper {
    public UserWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("sexName", ConstantFactory.me().getSexName((Integer) map.get("sex")));
        map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleid")));
        map.put("deptName", ConstantFactory.me().getDeptName((String) map.get("deptid")));
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }
}
