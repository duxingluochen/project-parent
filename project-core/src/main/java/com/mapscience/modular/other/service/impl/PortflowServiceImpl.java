package com.mapscience.modular.other.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.modular.other.mapper.PortstationMapper;
import com.mapscience.modular.other.model.Portflow;
import com.mapscience.modular.other.mapper.PortflowMapper;
import com.mapscience.modular.other.model.Portstation;
import com.mapscience.modular.other.service.IPortflowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 流程表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-09
 */
@Service
public class PortflowServiceImpl extends ServiceImpl<PortflowMapper, Portflow> implements IPortflowService {
    private static Logger log = LoggerFactory.getLogger(PortflowServiceImpl.class);

    @Resource
    private PortstationMapper portstationMapper;

    /**
     * 发起流程
     * @param flowType  流程类型
     * @param eventId   事件id
     * @param userId    流程发起人id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Boolean startFlow (String flowType, String eventId, String userId) {
        Boolean flag = false;
        Wrapper<Portstation> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("flow_Type", flowType);
        wrapper = wrapper.orderBy("sort");
        List<Portstation> list = portstationMapper.selectList(wrapper);

        //保存发起任务记录
        Portstation startNode = list.get(0);
        Portflow startTask = new Portflow();
        String id = UUID.randomUUID().toString().replace("-","");
        startTask.setId(id);
        startTask.setCurrentStation(startNode.getNodeNumber());
        startTask.setCurrentEmp(userId);
        startTask.setCurrentTimes(new Date());
        startTask.setFlowType(startNode.getFlowType());
        startTask.setState(2);//设为通过
        startTask.setEventId(eventId);
        startTask.setStarter(userId);
        try {
            //保存待办任务记录
            Portstation nextNode = list.get(1);
            Portflow nextTask = getNextTask(nextNode, eventId, userId);
            if (baseMapper.insert(startTask) > 0) {
                if(baseMapper.insert(nextTask) > 0) {
                    flag = true;
                } else {
                    baseMapper.deleteById(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //生成下一节点数据
    public Portflow getNextTask(Portstation nextNode, String eventId, String starter) throws Exception{
        Portflow nextTask = new Portflow();
        nextTask.setId(UUID.randomUUID().toString().replace("-",""));
        nextTask.setCurrentStation(nextNode.getNodeNumber());
        nextTask.setCurrentEmp(getPerson(nextNode.getNodeNumber()));
        nextTask.setFlowType(nextNode.getFlowType());
        nextTask.setState(0);//设为待办
        nextTask.setEventId(eventId);
        nextTask.setStarter(starter);
        return nextTask;
    }

    /**
     * 执行下一步，流程节点走到下一个节点
     * @param taskId    任务ID
     * @param opinion   审批意见
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Boolean next(String taskId, String opinion) throws Exception {
        Boolean flag = false;
        Portflow nowNode = baseMapper.selectById(taskId);
        switch (nowNode.getState()) {
            case 0:
            case 1:
                nowNode.setCurrentTimes(new Date());
                nowNode.setState(2);//设为通过
                nowNode.setOpinion(opinion);
                Portstation nextNode = portstationMapper.selectNextNodeByTaskId(taskId);
                if (nextNode != null) {
                    Portflow nextTask = getNextTask(nextNode, nowNode.getEventId(), nowNode.getStarter());
                    if (baseMapper.updateById(nowNode) > 0) {
                        if(baseMapper.insert(nextTask) > 0) {
                            flag = true;
                        }
                    }
                }
                break;
            case 2:
                log.info("该节点已流转，不能执行下一步操作，taskId：" + taskId);
                break;
            case 3:
                log.info("该节点已驳回，不能执行下一步操作，taskId：" + taskId);
                break;
            default:
                log.info("该节点状态异常，不能执行下一步操作，taskId：" + taskId);
                break;
        }
        return flag;
    }

    //获取第二节点状态
    public int getSecondNodeState(String eventId) {
        Portflow portflow = baseMapper.selectSecondNodeByEventId(eventId);
        return portflow.getState();
    }

    /**
     * 任务申领
     * @param id
     * @return
     */
    public Boolean taskApply(String id) {
        Boolean flag = false;
        //获取任务实例
        Portflow task = baseMapper.selectById(id);
        if (task != null) {
            switch (task.getState()) {
                case 0:
                    task.setState(1);
                    if (baseMapper.updateById(task) > 0) {
                        log.info("申领任务成功：taskid:" + task.getId());
                        flag = true;
                    } else {
                        log.error("申领任务失败：taskid:" + task.getId());
                        break;
                    }
                case 1:
                    flag = true;
                    break;
                default:
                    log.error("申领任务失败：taskid:" + task.getId());
            }
        }
        return flag;
    }

    //撤销流程
    public Boolean flowRevoke(String eventId) {
        Boolean flag = false;
        int state = getSecondNodeState(eventId);
        if(state == 0 && baseMapper.deleteByEventId(eventId) > 0) {
            flag = true;
        }
        return flag;
    }

    //获取节点处理人方法
    private String getPerson(String nodeNumber) throws Exception {
        String userID = "";
        switch (nodeNumber) {
            case "sta012":
                //设施维护审批
                userID = facMaintainApply();
                break;
            case "sta013":
                //设施维护完成
                userID = facMaintainFinish();
                break;
            default:
                break;
        }
        if(userID.isEmpty()) {
            throw new Exception("节点处理人获取失败，nodeNumber：" + nodeNumber);
        }
        return userID;
    }

    //获取设施维护审核节点处理人
    private String facMaintainApply() {
        return "46";
    }

    //获取设施维护完成节点处理人
    private String facMaintainFinish() {
        return "45";
    }

}
