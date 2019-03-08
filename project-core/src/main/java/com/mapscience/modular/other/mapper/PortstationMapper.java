package com.mapscience.modular.other.mapper;

import com.mapscience.modular.other.model.Portstation;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 节点表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-09
 */
public interface PortstationMapper extends BaseMapper<Portstation> {

    Portstation selectNextNodeByTaskId(@Param("taskId") String taskId);

}
