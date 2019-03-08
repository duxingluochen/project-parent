package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Driver;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 司机表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-12-28
 */
public interface DriverMapper extends BaseMapper<Driver> {
    List<Driver> getDriverList();
}
