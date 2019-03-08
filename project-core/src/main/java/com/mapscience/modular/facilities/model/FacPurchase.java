package com.mapscience.modular.facilities.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设施采购
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@TableName("fac_purchase")
public class FacPurchase extends Model<FacPurchase> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 设施id
     */
    @TableField("fac_id")
    private String facId;
    /**
     * 采购数量
     */
    @TableField("pur_number")
    private Integer purNumber;
    /**
     * 采购单价
     */
    @TableField("pur_price")
    private BigDecimal purPrice;
    /**
     * 采购总价
     */
    @TableField("pur_total_price")
    private BigDecimal purTotalPrice;
    /**
     * 备注
     */
    private String remark;
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

    public String getFacId() {
        return facId;
    }

    public void setFacId(String facId) {
        this.facId = facId;
    }

    public Integer getPurNumber() {
        return purNumber;
    }

    public void setPurNumber(Integer purNumber) {
        this.purNumber = purNumber;
    }

    public BigDecimal getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(BigDecimal purPrice) {
        this.purPrice = purPrice;
    }

    public BigDecimal getPurTotalPrice() {
        return purTotalPrice;
    }

    public void setPurTotalPrice(BigDecimal purTotalPrice) {
        this.purTotalPrice = purTotalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "FacPurchase{" +
        "id=" + id +
        ", facId=" + facId +
        ", purNumber=" + purNumber +
        ", purPrice=" + purPrice +
        ", purTotalPrice=" + purTotalPrice +
        ", remark=" + remark +
        ", createTime=" + createTime +
        "}";
    }
}
