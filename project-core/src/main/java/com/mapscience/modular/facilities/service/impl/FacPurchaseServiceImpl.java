package com.mapscience.modular.facilities.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacPurchase;
import com.mapscience.modular.facilities.mapper.FacPurchaseMapper;
import com.mapscience.modular.facilities.service.IFacPurchaseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设施采购 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@Service
public class FacPurchaseServiceImpl extends ServiceImpl<FacPurchaseMapper, FacPurchase> implements IFacPurchaseService {

    public List<FacPurchase> selectPageList(Page<FacPurchase> page, String facName) {
        return baseMapper.selectPageList(page,facName);
    }
}
