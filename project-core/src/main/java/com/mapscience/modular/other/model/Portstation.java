package com.mapscience.modular.other.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 节点表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-09
 */
@TableName("t_portstation")
public class Portstation extends Model<Portstation> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 节点名
     */
    private String name;
    /**
     * 流程类型
     */
    @TableField("flow_type")
    private String flowType;
    /**
     * 节点编号
     */
    @TableField("node_number")
    private String nodeNumber;

    /**
     * 排序
     */
    private Integer sort;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(String nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Portstation{" +
        "id=" + id +
        ", name=" + name +
        ", flowType=" + flowType +
        ", nodeNumber=" + nodeNumber +
        ", sort=" + sort +
        "}";
    }
}
