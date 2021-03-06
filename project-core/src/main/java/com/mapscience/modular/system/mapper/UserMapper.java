package com.mapscience.modular.system.mapper;

import com.mapscience.modular.system.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-07
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据账号查找管理员
     * @param account
     * @return
     */
    User getByAccount(String account);
}
