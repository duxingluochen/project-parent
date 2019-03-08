package com.mapscience.core.auth.validator.impl;

import com.mapscience.core.auth.validator.IReqValidator;
import com.mapscience.core.auth.validator.dto.Credence;
import org.springframework.stereotype.Service;

/**
 * 账号密码验证
 *
 */
@Service
public class DbValidator implements IReqValidator {

    /*@Autowired
    UserMapper userMapper;*/

    @Override
    public boolean validate(Credence credence) {
        /*List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("userName", credence.getCredenceName()));
        if (users != null && users.size() > 0) {
            return true;
        } else {
            return false;
        }*/
        return true;
    }
}
