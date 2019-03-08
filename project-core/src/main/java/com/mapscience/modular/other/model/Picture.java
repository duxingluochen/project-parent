package com.mapscience.modular.other.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@TableName("t_picture")
public class Picture extends Model<Picture> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 图片名
     */
    @TableField("pic_name")
    private String picName;
    /**
     * 图片存储名
     */
    @TableField("pic_newname")
    private String picNewname;
    /**
     * 图片存储路径
     */
    @TableField("pic_path")
    private String picPath;
    /**
     * 图片大小
     */
    @TableField("pic_size")
    private BigDecimal picSize;
    /**
     * 图片后缀
     */
    @TableField("pic_suffix")
    private String picSuffix;
    /**
     * 文件类型（1：头像，2：车辆照片，3：巡查图片）
     */
    @TableField("pic_type")
    private Integer picType;
    /**
     * 上传人
     */
    private String uploader;
    /**
     * 上传时间
     */
    @TableField("create_time")
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicNewname() {
        return picNewname;
    }

    public void setPicNewname(String picNewname) {
        this.picNewname = picNewname;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public BigDecimal getPicSize() {
        return picSize;
    }

    public void setPicSize(BigDecimal picSize) {
        this.picSize = picSize;
    }

    public String getPicSuffix() {
        return picSuffix;
    }

    public void setPicSuffix(String picSuffix) {
        this.picSuffix = picSuffix;
    }

    public Integer getPicType() {
        return picType;
    }

    public void setPicType(Integer picType) {
        this.picType = picType;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
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
        return "Picture{" +
        "id=" + id +
        ", picName=" + picName +
        ", picNewname=" + picNewname +
        ", picPath=" + picPath +
        ", picSize=" + picSize +
        ", picSuffix=" + picSuffix +
        ", picType=" + picType +
        ", uploader=" + uploader +
        ", createTime=" + createTime +
        "}";
    }
}
