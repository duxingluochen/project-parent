package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Driver;
import com.mapscience.modular.system.mapper.DriverMapper;
import com.mapscience.modular.system.service.IDriverService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 司机表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2018-12-28
 */
@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements IDriverService {
    public Driver getByUserId(String userId) {
        Driver driver = new Driver();
        driver.setUserId(userId);
        return baseMapper.selectOne(driver);
    }

    @Override
    public List<Driver> getDriverList() {
        List<Driver> list=baseMapper.getDriverList();
        return list;
    }
}
