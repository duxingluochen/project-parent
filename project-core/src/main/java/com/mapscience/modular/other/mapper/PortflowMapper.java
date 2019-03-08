package com.mapscience.modular.other.mapper;

import com.mapscience.modular.other.model.Portflow;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 流程表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-01-09
 */
public interface PortflowMapper extends BaseMapper<Portflow> {

    //获取第二节点状态（用来判断是否可以编辑业务数据，返回状态不为0，则表示已申领或已流转）
    Portflow selectSecondNodeByEventId(@Param("eventId") String eventId);

    int deleteByEventId(@Param("eventId") String eventId);

    /**
     *获取流程进度
     * @param eventId 事件id
     * @return
     */
    List<Map<String, Object>> selectFlowNodeSchedule(@Param("eventId") String eventId);

}
