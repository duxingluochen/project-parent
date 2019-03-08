package com.mapscience.modular.facilities.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacUse;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设施使用 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface IFacUseService extends IService<FacUse> {

    //设施使用分页查询
    List<Map<String, Object>> selectPageList(Page<FacUse> page, String facName);

    //设施使用新增或修改
    Boolean addOrUpdate(FacUse facUse);

    //设施使用删除
    Boolean deleteUse(String id);

}
