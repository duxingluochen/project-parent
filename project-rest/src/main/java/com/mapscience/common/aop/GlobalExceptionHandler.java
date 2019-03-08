package com.mapscience.common.aop;

import com.mapscience.core.aop.BaseControllerExceptionHandler;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.common.status.ProjectStatusEnum;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截jwt相关异常
     */
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseVal jwtException(JwtException e) {
        return new ResponseVal(ProjectStatusEnum.TOKEN_ERROR.getCode(), ProjectStatusEnum.TOKEN_ERROR.getMsg());
    }
}
