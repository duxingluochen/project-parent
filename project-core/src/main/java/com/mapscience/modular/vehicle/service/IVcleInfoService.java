package com.mapscience.modular.vehicle.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.vehicle.model.VcleInfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆基本信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
public interface IVcleInfoService extends IService<VcleInfo> {

    List<VcleInfo> getVcleInfoList(Page<VcleInfo> page, VcleInfo vcleInfo);

    List<Map<String, Object>> getVcleInfoProportionlist();

}
