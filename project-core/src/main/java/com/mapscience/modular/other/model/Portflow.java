package com.mapscience.modular.other.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 流程表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-09
 */
@TableName("t_portflow")
public class Portflow extends Model<Portflow> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 节点编号
     */
    @TableField("current_station")
    private String currentStation;
    /**
     * 操作人员
     */
    @TableField("current_emp")
    private String currentEmp;
    /**
     * 操作时间
     */
    @TableField("current_times")
    private Date currentTimes;
    /**
     * 流程类型编号
     */
    @TableField("flow_type")
    private String flowType;
    /**
     * 状态(0:待审核1：已申领2：通过，3：驳回)
     */
    private Integer state;
    /**
     * 事件id
     */
    @TableField("event_id")
    private String eventId;
    /**
     * 评审意见
     */
    private String opinion;
    @TableField("create_time")
    private Date createTime;

    /**
     * 启东人
     */
    private String starter;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(String currentStation) {
        this.currentStation = currentStation;
    }

    public String getCurrentEmp() {
        return currentEmp;
    }

    public void setCurrentEmp(String currentEmp) {
        this.currentEmp = currentEmp;
    }

    public Date getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(Date currentTimes) {
        this.currentTimes = currentTimes;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Portflow{" +
        "id=" + id +
        ", currentStation=" + currentStation +
        ", currentEmp=" + currentEmp +
        ", currentTimes=" + currentTimes +
        ", flowType=" + flowType +
        ", state=" + state +
        ", eventId=" + eventId +
        ", opinion=" + opinion +
        ", createTime=" + createTime +
        ", starter=" + starter +
        "}";
    }
}
