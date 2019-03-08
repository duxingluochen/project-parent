package com.mapscience.modular.facilities.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacInfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 设施基本信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface IFacInfoService extends IService<FacInfo> {
    List<FacInfo> selectFacPageList(Page<FacInfo> page, FacInfo facInfo);
}
