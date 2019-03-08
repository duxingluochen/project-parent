package com.mapscience.modular.facilities.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacStock;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设施库存 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface IFacStockService extends IService<FacStock> {

    //库存分页查询
    List<FacStock> selectPageList(Page<FacStock> page, String facName, String facType);

    //根据设施查询库存
    FacStock selectFacStockByfacId(String facId);

    //查询设施名称及对应库存地址
    List<Map<String, Object>> selectStockAndFacName();

}
