package com.mapscience.modular.facilities.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacMaintain;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设施维护 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
public interface FacMaintainMapper extends BaseMapper<FacMaintain> {

    List<Map<String, Object>> selectPageList(Page<FacMaintain> page, @Param("facName")String facName, @Param("selection")String selection, @Param("userId")String userId);

}
