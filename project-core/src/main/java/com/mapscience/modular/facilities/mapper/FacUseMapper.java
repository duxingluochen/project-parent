package com.mapscience.modular.facilities.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacUse;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设施使用 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface FacUseMapper extends BaseMapper<FacUse> {

    //设施使用分页查询
    List<Map<String, Object>> selectPageList(Page<FacUse> page, @Param("facName") String facName);

}
