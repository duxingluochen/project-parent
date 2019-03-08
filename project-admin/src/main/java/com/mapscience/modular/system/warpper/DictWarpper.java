package com.mapscience.modular.system.warpper;

import com.mapscience.core.base.warpper.BaseControllerWarpper;
import com.mapscience.core.common.constant.factory.ConstantFactory;
import com.mapscience.core.util.ToolUtil;
import com.mapscience.modular.system.model.Dict;

import java.util.List;
import java.util.Map;

public class DictWarpper extends BaseControllerWarpper {
    public DictWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        StringBuffer detail = new StringBuffer();
        String id = (String) map.get("id");
        List<Dict> dicts = ConstantFactory.me().findInDict(id);
        if (dicts != null) {
            for (Dict dict : dicts) {
                detail.append(dict.getCode() + ":" + dict.getName() + ",");
            }
            map.put("detail", ToolUtil.removeSuffix(detail.toString(), ","));
        }
    }
}
