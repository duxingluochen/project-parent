package com.mapscience.core.auth.validator.impl;

import com.mapscience.core.auth.validator.IReqValidator;
import com.mapscience.core.auth.validator.dto.Credence;
import org.springframework.stereotype.Service;

/**
 * 直接验证账号密码是不是admin
 *
 */
@Service
public class SimpleValidator implements IReqValidator {
    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();
        if (userName.equals(userName) && password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
