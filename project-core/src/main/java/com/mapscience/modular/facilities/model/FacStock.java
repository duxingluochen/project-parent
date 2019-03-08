package com.mapscience.modular.facilities.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设施库存
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@TableName("fac_stock")
public class FacStock extends Model<FacStock> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 设施id
     */
    @TableField("fac_id")
    private String facId;
    /**
     * 设施库存数量
     */
    @TableField("fac_numer")
    private Integer facNumer;
    /**
     * 设施类型
     */
    @TableField("fac_type")
    private String facType;
    /**
     * 存放地点
     */
    @TableField("fac_address")
    private String facAddress;
    /**
     * 设施管理人员
     */
    @TableField("fac_manager")
    private String facManager;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 版本
     */
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacId() {
        return facId;
    }

    public void setFacId(String facId) {
        this.facId = facId;
    }

    public Integer getFacNumer() {
        return facNumer;
    }

    public void setFacNumer(Integer facNumer) {
        this.facNumer = facNumer;
    }

    public String getFacType() {
        return facType;
    }

    public void setFacType(String facType) {
        this.facType = facType;
    }

    public String getFacAddress() {
        return facAddress;
    }

    public void setFacAddress(String facAddress) {
        this.facAddress = facAddress;
    }

    public String getFacManager() {
        return facManager;
    }

    public void setFacManager(String facManager) {
        this.facManager = facManager;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() { return version; }

    public void setVersion(Integer version) { this.version = version; }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FacStock{" +
        "id=" + id +
        ", facId=" + facId +
        ", facNumer=" + facNumer +
        ", facType=" + facType +
        ", facAddress=" + facAddress +
        ", facManager=" + facManager +
        ", createTime=" + createTime +
        ", version=" + version +
        "}";
    }
}
