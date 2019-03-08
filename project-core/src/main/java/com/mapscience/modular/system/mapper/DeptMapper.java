package com.mapscience.modular.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.core.node.ZTreeNode;
import com.mapscience.modular.system.model.Dept;
import com.mapscience.modular.system.model.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有部门列表
     */
    List<Map<String, Object>> list(Page<Dept> page,@Param("condition") String condition);

    List<Dept> getDeptList();

    /**
     * 新增部门
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 通过id获取部门信息
     * @param id
     * @return
     */
    Dept getDeptById(@Param("id") String id);

    /**
     * 删除部门
     * @param id
     * @return
     */
    int delDept(@Param("id") String id);

    /**
     * 更新部门
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

}