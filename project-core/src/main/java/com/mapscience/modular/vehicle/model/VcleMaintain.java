package com.mapscience.modular.vehicle.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 车辆维护
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
@TableName("vcle_maintain")
public class VcleMaintain extends Model<VcleMaintain> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 维护类型（年审，车辆报修）
     */
    private String content;
    /**
     * 维护费用(元)
     */
    @TableField("tain_cost")
    private BigDecimal tainCost;
    /**
     * 维护原因
     */
    @TableField("tain_reason")
    private String tainReason;
    /**
     * 维护前照片
     */
    @TableField("before_photos")
    private String beforePhotos;
    /**
     * 维护后照片
     */
    @TableField("after_photos")
    private String afterPhotos;
    /**
     * 保养存在问题
     */
    private String problems;
    /**
     * 车辆id
     */
    @TableField("vcle_id")
    private String vcleId;
    /**
     * 维护时间
     */
    @TableField("create_time")
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getTainCost() {
        return tainCost;
    }

    public void setTainCost(BigDecimal tainCost) {
        this.tainCost = tainCost;
    }

    public String getTainReason() {
        return tainReason;
    }

    public void setTainReason(String tainReason) {
        this.tainReason = tainReason;
    }

    public String getBeforePhotos() {
        return beforePhotos;
    }

    public void setBeforePhotos(String beforePhotos) {
        this.beforePhotos = beforePhotos;
    }

    public String getAfterPhotos() {
        return afterPhotos;
    }

    public void setAfterPhotos(String afterPhotos) {
        this.afterPhotos = afterPhotos;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getVcleId() {
        return vcleId;
    }

    public void setVcleId(String vcleId) {
        this.vcleId = vcleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "VcleMaintain{" +
        "id=" + id +
        ", content=" + content +
        ", tainCost=" + tainCost +
        ", tainReason=" + tainReason +
        ", beforePhotos=" + beforePhotos +
        ", afterPhotos=" + afterPhotos +
        ", problems=" + problems +
        ", vcleId=" + vcleId +
        ", createTime=" + createTime +
        "}";
    }
}
