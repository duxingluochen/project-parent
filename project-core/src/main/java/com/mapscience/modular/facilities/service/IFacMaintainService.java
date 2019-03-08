package com.mapscience.modular.facilities.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacMaintain;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设施维护 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface IFacMaintainService extends IService<FacMaintain> {

    List<Map<String, Object>> selectPageList(Page<FacMaintain> page, String facName, String selection, String userId);

    int addOrUpdate(FacMaintain facMaintain, String userId);

}
