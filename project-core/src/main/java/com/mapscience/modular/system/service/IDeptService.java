package com.mapscience.modular.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.mapscience.core.node.ZTreeNode;
import com.mapscience.modular.system.model.Dept;
import com.mapscience.modular.system.model.Dict;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * 部门服务
 *
 */
public interface IDeptService extends IService<Dept> {

    /**
     * 删除部门
     */
    void deleteDept(Integer deptId);

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有部门列表
     */
    List<Map<String, Object>> list(Page<Dept> page,@Param("condition") String condition);

    /**
     * 获取新增部门页面初始数据
     * @param id
     * @param model
     * @return
     */
    Model getDeptAddModel(String id, Model model);

    /**
     * 新增部门
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 删除部门
     * @param id
     * @return
     */
    int delDept(String id);
}
