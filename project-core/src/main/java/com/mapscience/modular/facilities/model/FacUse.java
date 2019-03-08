package com.mapscience.modular.facilities.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 设施使用
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@TableName("fac_use")
public class FacUse extends Model<FacUse> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 设施id
     */
    @TableField("facstock_id")
    private String facstockId;
    /**
     * 使用数量
     */
    @TableField("use_number")
    private Integer useNumber;
    /**
     * 使用人
     */
    @TableField("user_id")
    private String userId;
    /**
     * 使用时间
     */
    @TableField("use_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
    private Date useTime;
    /**
     * 使用说明
     */
    private String instructions;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacstockId() {
        return facstockId;
    }

    public void setFacstockId(String facstockId) {
        this.facstockId = facstockId;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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
        return "FacUse{" +
        "id=" + id +
        ", facstockId=" + facstockId +
        ", useNumber=" + useNumber +
        ", userId=" + userId +
        ", useTime=" + useTime +
        ", instructions=" + instructions +
        ", createTime=" + createTime +
        "}";
    }
}
