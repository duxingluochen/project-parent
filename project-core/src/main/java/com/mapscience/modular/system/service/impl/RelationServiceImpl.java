package com.mapscience.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mapscience.modular.system.mapper.RelationMapper;
import com.mapscience.modular.system.model.Relation;
import com.mapscience.modular.system.service.IRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 */
@Service
@Transactional
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

}
