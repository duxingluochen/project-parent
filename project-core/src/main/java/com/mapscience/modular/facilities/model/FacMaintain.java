package com.mapscience.modular.facilities.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设施维护
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@TableName("fac_maintain")
public class FacMaintain extends Model<FacMaintain> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 设施id
     */
    @TableField("fac_id")
    private String facId;
    /**
     * 维护内容
     */
    @TableField("tain_content")
    private String tainContent;
    /**
     * 维护价格
     */
    @TableField("tain_price")
    private BigDecimal tainPrice;
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

    public String getTainContent() {
        return tainContent;
    }

    public void setTainContent(String tainContent) {
        this.tainContent = tainContent;
    }

    public BigDecimal getTainPrice() {
        return tainPrice;
    }

    public void setTainPrice(BigDecimal tainPrice) {
        this.tainPrice = tainPrice;
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
        return "FacMaintain{" +
        "id=" + id +
        ", facId=" + facId +
        ", tainContent=" + tainContent +
        ", tainPrice=" + tainPrice +
        ", beforePhotos=" + beforePhotos +
        ", afterPhotos=" + afterPhotos +
        ", createTime=" + createTime +
        "}";
    }
}
