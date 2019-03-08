package com.mapscience.modular.facilities.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 设施基本信息 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface FacInfoMapper extends BaseMapper<FacInfo> {

    List<FacInfo> selectFacPageList(Page<FacInfo> page, FacInfo facInfo);

}
