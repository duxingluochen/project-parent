package com.mapscience.modular.other.service;

import com.mapscience.modular.other.model.Portflow;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 流程表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-09
 */
public interface IPortflowService extends IService<Portflow> {

    /**
     * 启动流程
     * @param flowType  流程类型
     * @param eventId   事件id
     * @param userId    流程发起人id
     * @return
     */
    Boolean startFlow(String flowType, String eventId, String userId);

    /**
     * 执行下一步，流程节点走到下一个节点
     * @param taskId    任务ID
     * @param opinion   审批意见
     * @return
     */
    Boolean next(String taskId, String opinion) throws Exception;

    /**
     * 获取第二节点状态（用来判断是否可以编辑业务数据，返回状态不为0，则表示已申领或已流转）
     * @param eventId
     * @return
     */
    int getSecondNodeState(String eventId);

    /**
     * 任务申领
     * @param id
     * @return
     */
    Boolean taskApply(String id);

    /**
     * 撤销流程
     * @param eventId
     * @return
     */
    Boolean flowRevoke(String eventId);

}
