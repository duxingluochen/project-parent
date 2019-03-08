package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.core.node.ZTreeNode;
import com.mapscience.modular.system.mapper.DeptMapper;
import com.mapscience.modular.system.model.Dept;
import com.mapscience.modular.system.model.Dict;
import com.mapscience.modular.system.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public void deleteDept(Integer deptId) {
        Dept dept = deptMapper.selectById(deptId);

        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + dept.getId() + "]%");
        List<Dept> subDepts = deptMapper.selectList(wrapper);
        for (Dept temp : subDepts) {
            temp.deleteById();
        }

        dept.deleteById();
    }

    @Override
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    @Override
    public List<Map<String, Object>> list(Page<Dept> page,String condition) {
        return this.baseMapper.list(page,condition);
    }

    @Override
    public Model getDeptAddModel(String id, Model model) {
        if(id != null && !id.isEmpty()) {
            model.addAttribute("deptInfo", baseMapper.getDeptById(id));
        }
        List<Dept> list = baseMapper.getDeptList();
        if (id != null && !id.isEmpty()) {
            //删除自身
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getId().equals(id)){
                    list.remove(i);
                    break;
                }
            }
        }
        model.addAttribute("dept", list);
        return model;
    }

    @Override
    public int addDept(Dept dept) {
        String pids = "";
        if (dept.getPid().equals("0")) {
            //无父节点
            pids = "[0],";
        } else {
            //获取上级部门pids拼接自己的pids
            Dept pdept = baseMapper.getDeptById(dept.getPid());
            pids = pdept.getPids() + "[" + dept.getPid() + "],";
        }

        dept.setPids(pids);
        if(dept.getId().isEmpty()) {
            //新增
            dept.setId(UUID.randomUUID().toString().replace("-",""));
            return baseMapper.addDept(dept);
        } else {
            //更新
            return baseMapper.updateDept(dept);
        }
    }

    @Override
    public int delDept(String id) { return baseMapper.delDept(id); }
}
