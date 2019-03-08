package com.mapscience.modular.vehicle.mapper;

import com.mapscience.modular.vehicle.model.VcleBelong;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆所属司机 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
public interface VcleBelongMapper extends BaseMapper<VcleBelong> {
    List<Map<String, Object>> getDriverByVcleId(@Param("vcleId") String vcleId, @Param("driId") String driId);

    List<VcleBelong> getDriverByVcleIdOrDriId(@Param("vcleId") String vcleId, @Param("driId") String driId);
}
