package com.mapscience.modular.other.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 视频表
 * </p>
 *
 * @author ${author}
 * @since 2018-12-29
 */
@TableName("t_video")
public class Video extends Model<Video> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 所上传文件名
     */
    @TableField("vid_name")
    private String vidName;
    /**
     * 储存文件名
     */
    @TableField("vid_newname")
    private String vidNewname;
    /**
     * 文件全路径
     */
    @TableField("vid_path")
    private String vidPath;
    /**
     * 文件大小
     */
    @TableField("vid_size")
    private BigDecimal vidSize;
    /**
     * 文件后缀
     */
    @TableField("vid_suffix")
    private String vidSuffix;
    /**
     * 文件类型（1：车载视频，2：巡查视频，3：监控视频）
     */
    @TableField("vid_type")
    private Integer vidType;
    /**
     * 上传人
     */
    private String uploader;
    @TableField("create_time")
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVidName() {
        return vidName;
    }

    public void setVidName(String vidName) {
        this.vidName = vidName;
    }

    public String getVidNewname() {
        return vidNewname;
    }

    public void setVidNewname(String vidNewname) {
        this.vidNewname = vidNewname;
    }

    public String getVidPath() {
        return vidPath;
    }

    public void setVidPath(String vidPath) {
        this.vidPath = vidPath;
    }

    public BigDecimal getVidSize() {
        return vidSize;
    }

    public void setVidSize(BigDecimal vidSize) {
        this.vidSize = vidSize;
    }

    public String getVidSuffix() {
        return vidSuffix;
    }

    public void setVidSuffix(String vidSuffix) {
        this.vidSuffix = vidSuffix;
    }

    public Integer getVidType() {
        return vidType;
    }

    public void setVidType(Integer vidType) {
        this.vidType = vidType;
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
        return "Video{" +
        "id=" + id +
        ", vidName=" + vidName +
        ", vidNewname=" + vidNewname +
        ", vidPath=" + vidPath +
        ", vidSize=" + vidSize +
        ", vidSuffix=" + vidSuffix +
        ", vidType=" + vidType +
        ", uploader=" + uploader +
        ", createTime=" + createTime +
        "}";
    }
}
