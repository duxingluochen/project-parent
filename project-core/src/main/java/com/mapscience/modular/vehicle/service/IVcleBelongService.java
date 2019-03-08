package com.mapscience.modular.vehicle.service;

import com.mapscience.modular.vehicle.model.VcleBelong;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆所属司机 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
public interface IVcleBelongService extends IService<VcleBelong> {
    Boolean getDriverByVcleId(String vcleId, String driId);
    List<Map<String,Object>> getVcleBelongListByVcleId(String vcleId);
}
