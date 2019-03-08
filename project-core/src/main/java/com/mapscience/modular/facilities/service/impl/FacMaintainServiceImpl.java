package com.mapscience.modular.facilities.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.model.FacMaintain;
import com.mapscience.modular.facilities.mapper.FacMaintainMapper;
import com.mapscience.modular.facilities.service.IFacMaintainService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.modular.other.mapper.PortflowMapper;
import com.mapscience.modular.other.service.IPortflowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 设施维护 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@Service
public class FacMaintainServiceImpl extends ServiceImpl<FacMaintainMapper, FacMaintain> implements IFacMaintainService {

    @Resource
    private PortflowMapper portflowMapper;

    @Resource
    private IPortflowService portflowService;

    public List<Map<String, Object>> selectPageList(Page<FacMaintain> page, String facName, String selection, String userId) {
        return baseMapper.selectPageList(page, facName, selection, userId);
    }

    public int addOrUpdate(FacMaintain facMaintain, String userId) {
        int state = 0;
        String id = facMaintain.getId();
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString().replace("-","");
            facMaintain.setId(id);
            //发起设施维护流程
            if(portflowService.startFlow("100101", id, userId)) {
                //保存业务数据
                if (baseMapper.insert(facMaintain) > 0) {
                    state = 200;//执行成功
                }
            }
        } else {
            //判断是否可编辑（申领和审批后不能编辑）
            state = portflowService.getSecondNodeState(id);
            if (state == 0) {
                baseMapper.updateById(facMaintain);
                state = 200;
            }
        }
        return state;
    }



}
