package com.mapscience.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 司机表
 * </p>
 *
 * @author ${author}
 * @since 2018-12-28
 */
@TableName("sys_driver")
public class Driver extends Model<Driver> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 人员id
     */
    @TableField("user_id")
    private String userId;
    /**
     * 驾驶证号
     */
    @TableField("license_number")
    private String licenseNumber;
    /**
     * 驾龄
     */
    @TableField("driving_age")
    private Integer drivingAge;
    /**
     * 准驾车型
     */
    @TableField("vehicle_type")
    private String vehicleType;
    /**
     * 违章次数
     */
    @TableField("peccancy_times")
    private Integer peccancyTimes;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getDrivingAge() {
        return drivingAge;
    }

    public void setDrivingAge(Integer drivingAge) {
        this.drivingAge = drivingAge;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getPeccancyTimes() {
        return peccancyTimes;
    }

    public void setPeccancyTimes(Integer peccancyTimes) {
        this.peccancyTimes = peccancyTimes;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Driver{" +
        "id=" + id +
        ", userId=" + userId +
        ", licenseNumber=" + licenseNumber +
        ", drivingAge=" + drivingAge +
        ", vehicleType=" + vehicleType +
        ", peccancyTimes=" + peccancyTimes +
        ", status=" + status +
        "}";
    }
}
