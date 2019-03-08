package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-07
 */
public interface MenuMapper extends BaseMapper<Menu> {


    List<String> getResUrlsByRoleId(String roleId);
}
