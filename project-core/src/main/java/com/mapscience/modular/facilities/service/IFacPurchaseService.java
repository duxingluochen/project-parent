package com.mapscience.modular.facilities.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacPurchase;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 设施采购 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface IFacPurchaseService extends IService<FacPurchase> {

    List<FacPurchase> selectPageList(Page<FacPurchase> page, String facName);

}
