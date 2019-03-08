package com.mapscience.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单表

 * </p>
 *
 * @author tyj
 * @since 2018-09-24
 */
@TableName("biz_order")
public class BizOrder extends Model<BizOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 下单地点
     */
    private String place;
    /**
     * 下单时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 下单用户名称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 下单用户电话
     */
    @TableField("user_phone")
    private String userPhone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BizOrder{" +
        "id=" + id +
        ", goodsName=" + goodsName +
        ", place=" + place +
        ", createTime=" + createTime +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        "}";
    }
}
