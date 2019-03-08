package com.mapscience.modular.facilities.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacInfo;
import com.mapscience.modular.facilities.mapper.FacInfoMapper;
import com.mapscience.modular.facilities.service.IFacInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设施基本信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@Service
public class FacInfoServiceImpl extends ServiceImpl<FacInfoMapper, FacInfo> implements IFacInfoService {
    public List<FacInfo> selectFacPageList(Page<FacInfo> page, FacInfo facInfo) {
        return baseMapper.selectFacPageList(page,facInfo);
    }

}
