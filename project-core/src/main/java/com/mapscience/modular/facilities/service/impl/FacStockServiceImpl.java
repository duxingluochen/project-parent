package com.mapscience.modular.facilities.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacStock;
import com.mapscience.modular.facilities.mapper.FacStockMapper;
import com.mapscience.modular.facilities.service.IFacStockService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设施库存 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@Service
public class FacStockServiceImpl extends ServiceImpl<FacStockMapper, FacStock> implements IFacStockService {

    public List<FacStock> selectPageList(Page<FacStock> page, String facName, String facType) {
        return baseMapper.selectPageList(page, facName, facType);
    }

    public FacStock selectFacStockByfacId(String facId) {
        return baseMapper.selectFacStockByfacId(facId);
    }

    public List<Map<String, Object>> selectStockAndFacName() {
        return baseMapper.selectStockAndFacName();
    }
}
