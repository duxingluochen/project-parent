package com.mapscience.modular.vehicle.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.vehicle.model.VcleInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆基本信息 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
public interface VcleInfoMapper extends BaseMapper<VcleInfo> {

    List<VcleInfo> getVcleInfoList(Page<VcleInfo> page, VcleInfo vcleInfo);

    List<Map<String,Object>> getVcleInfoslist();

    List<Map<String,Object>> getVcleInfoNamelist();
}
