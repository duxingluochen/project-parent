package com.mapscience.modular.vehicle.model;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 车辆基本信息
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
@TableName("vcle_info")
public class VcleInfo extends Model<VcleInfo> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 车辆名称
     */
    @TableField("vcle_name")
    private String vcleName;
    /**
     * 车牌号
     */
    @TableField("plate_number")
    private String plateNumber;
    /**
     * 发动机号
     */
    @TableField("engine_number")
    private String engineNumber;
    /**
     * 作业类型
     */
    @TableField("operation_type")
    private String operationType;
    /**
     * 所属作业队伍
     */
    @TableField("operation_team")
    private String operationTeam;
    /**
     * 所属部门
     */
    private String department;
    /**
     * 品牌型号
     */
    @TableField("brand_model")
    private String brandModel;
    /**
     * 载重量（吨）[存水量]
     */
    private BigDecimal deadweight;
    /**
     * 开始使用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd" ,  timezone="GMT+8")
    @TableField("start_using_time")
    private Date startUsingTime;
    /**
     * 使用年限
     */
    @TableField("service_life")
    private BigDecimal serviceLife;
    /**
     * 上次年审时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd" ,  timezone="GMT+8")
    @TableField("last_inspection_time")
    private Date lastInspectionTime;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 用油型号
     */
    @TableField("oil_type")
    private String oilType;
    /**
     * 车辆照片
     */
    private String picture;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVcleName() {
        return vcleName;
    }

    public void setVcleName(String vcleName) {
        this.vcleName = vcleName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationTeam() {
        return operationTeam;
    }

    public void setOperationTeam(String operationTeam) {
        this.operationTeam = operationTeam;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public BigDecimal getDeadweight() {
        return deadweight;
    }

    public void setDeadweight(BigDecimal deadweight) {
        this.deadweight = deadweight;
    }

    public Date getStartUsingTime() {
        return startUsingTime;
    }

    public void setStartUsingTime(Date startUsingTime) {
        this.startUsingTime = startUsingTime;
    }

    public BigDecimal getServiceLife() {
        return serviceLife;
    }

    public void setServiceLife(BigDecimal serviceLife) {
        this.serviceLife = serviceLife;
    }

    public Date getLastInspectionTime() {
        return lastInspectionTime;
    }

    public void setLastInspectionTime(Date lastInspectionTime) {
        this.lastInspectionTime = lastInspectionTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "VcleInfo{" +
                "id=" + id +
                ", vcleName=" + vcleName +
                ", plateNumber=" + plateNumber +
                ", engineNumber=" + engineNumber +
                ", operationType=" + operationType +
                ", operationTeam=" + operationTeam +
                ", department=" + department +
                ", brandModel=" + brandModel +
                ", deadweight=" + deadweight +
                ", startUsingTime=" + startUsingTime +
                ", serviceLife=" + serviceLife +
                ", lastInspectionTime=" + lastInspectionTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", oilType=" + oilType +
                ", picture=" + picture +
                "}";
    }
}
