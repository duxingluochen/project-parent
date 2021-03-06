package com.mapscience.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 公司业务类型表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-07
 */
@TableName("t_company_type")
public class CompanyType extends Model<CompanyType> {

    private static final long serialVersionUID = 1L;

    @TableId("company_type_id")
    private String companyTypeId;
    /**
     * 公司类型名
     */
    @TableField("company_type_name")
    private String companyTypeName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 描述
     */
    private String remark;
    /**
     * 创建时间
     */
    @TableField("crate_time")
    private Date crateTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public String getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(String companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public String getCompanyTypeName() {
        return companyTypeName;
    }

    public void setCompanyTypeName(String companyTypeName) {
        this.companyTypeName = companyTypeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.companyTypeId;
    }

    @Override
    public String toString() {
        return "CompanyType{" +
        "companyTypeId=" + companyTypeId +
        ", companyTypeName=" + companyTypeName +
        ", status=" + status +
        ", remark=" + remark +
        ", crateTime=" + crateTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
