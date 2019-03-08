package com.mapscience.modular.other.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 流程类型表
 * </p>
 *
 * @author ${author}
 * @since 2019-01-09
 */
@TableName("t_flowtype")
public class Flowtype extends Model<Flowtype> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 类型名
     */
    private String name;
    /**
     * 类型编号
     */
    private String no;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Flowtype{" +
        "id=" + id +
        ", name=" + name +
        ", no=" + no +
        "}";
    }
}
