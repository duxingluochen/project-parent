package com.mapscience.modular.vehicle.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 车辆所属司机
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
@TableName("vcle_belong")
public class VcleBelong extends Model<VcleBelong> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 车辆id
     */
    @TableField("vcle_id")
    private String vcleId;
    /**
     * 司机id
     */
    @TableField("dri_id")
    private String driId;
    /**
     * 创建事件
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
    /**
     * 状态(0:司机已经被更改，1:当前司机)
     */
    private Integer state;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVcleId() {
        return vcleId;
    }

    public void setVcleId(String vcleId) {
        this.vcleId = vcleId;
    }

    public String getDriId() {
        return driId;
    }

    public void setDriId(String driId) {
        this.driId = driId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "VcleBelong{" +
        "id=" + id +
        ", vcleId=" + vcleId +
        ", driId=" + driId +
        ", createTime=" + createTime +
        ", state=" + state +
        "}";
    }
}
