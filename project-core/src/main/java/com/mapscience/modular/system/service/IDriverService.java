package com.mapscience.modular.system.service;

import com.mapscience.modular.system.model.Driver;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 司机表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2018-12-28
 */
public interface IDriverService extends IService<Driver> {
    Driver getByUserId(String userId);

    List<Driver> getDriverList();
}
