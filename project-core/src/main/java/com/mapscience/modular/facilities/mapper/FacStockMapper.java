package com.mapscience.modular.facilities.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacStock;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设施库存 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface FacStockMapper extends BaseMapper<FacStock> {

    //库存分页查询
    List<FacStock> selectPageList(Page<FacStock> page, @Param("facName")String facName, @Param("facType")String facType);

    int updateFacNumer(FacStock facStock);

    FacStock selectFacStockByfacId(@Param("facId") String facId);

    //查询设施名称及对应库存地址
    List<Map<String, Object>> selectStockAndFacName();

}
