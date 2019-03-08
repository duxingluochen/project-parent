package com.mapscience.modular.facilities.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设施基本信息
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@TableName("fac_info")
public class FacInfo extends Model<FacInfo> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 设施名
     */
    @TableField("fac_name")
    private String facName;
    /**
     * 设施类型
     */
    @TableField("fac_type")
    private String facType;
    /**
     * 设施用途
     */
    @TableField("fac_purpose")
    private String facPurpose;
    /**
     * 设施单价
     */
    @TableField("fac_price")
    private BigDecimal facPrice;
    /**
     * 设施品牌
     */
    @TableField("fac_brand")
    private String facBrand;
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

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getFacType() {
        return facType;
    }

    public void setFacType(String facType) {
        this.facType = facType;
    }

    public String getFacPurpose() {
        return facPurpose;
    }

    public void setFacPurpose(String facPurpose) {
        this.facPurpose = facPurpose;
    }

    public BigDecimal getFacPrice() {
        return facPrice;
    }

    public void setFacPrice(BigDecimal facPrice) {
        this.facPrice = facPrice;
    }

    public String getFacBrand() {
        return facBrand;
    }

    public void setFacBrand(String facBrand) {
        this.facBrand = facBrand;
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
        return "FacInfo{" +
        "id=" + id +
        ", facName=" + facName +
        ", facType=" + facType +
        ", facPurpose=" + facPurpose +
        ", facPrice=" + facPrice +
        ", facBrand=" + facBrand +
        ", createTime=" + createTime +
        "}";
    }
}
