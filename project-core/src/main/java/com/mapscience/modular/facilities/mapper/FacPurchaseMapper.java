package com.mapscience.modular.facilities.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacPurchase;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 设施采购 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface FacPurchaseMapper extends BaseMapper<FacPurchase> {

    List<FacPurchase> selectPageList(Page<FacPurchase> page, @Param("facName") String facName);
}
