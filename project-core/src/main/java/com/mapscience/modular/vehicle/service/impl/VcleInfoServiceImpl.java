package com.mapscience.modular.vehicle.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.vehicle.model.VcleInfo;
import com.mapscience.modular.vehicle.mapper.VcleInfoMapper;
import com.mapscience.modular.vehicle.service.IVcleInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆基本信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
@Service
public class VcleInfoServiceImpl extends ServiceImpl<VcleInfoMapper, VcleInfo> implements IVcleInfoService {

    /**
     * 获取车辆基本信息列表
     *
     * @param page
     * @param vcleInfo
     * @return
     */
    @Override
    public List<VcleInfo> getVcleInfoList(Page<VcleInfo> page, VcleInfo vcleInfo) {
        List<VcleInfo> list = baseMapper.getVcleInfoList(page, vcleInfo);
        return list;
    }

    /**
     * 获取车辆类型车辆名称占比
     *
     * @return
     */
    @Override
    public List getVcleInfoProportionlist() {
        List<List<Map<String, Object>>> list = new ArrayList<>();
        List<Map<String, Object>> listOne = baseMapper.getVcleInfoslist();
        List<Map<String, Object>> listTwo = baseMapper.getVcleInfoNamelist();
        list.add(listOne);
        list.add(listTwo);
        return list;
    }
}
