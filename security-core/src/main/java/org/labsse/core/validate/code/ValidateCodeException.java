package org.labsse.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author lijiechu
 * @create on 2018/12/28
 * @description 验证码验证异常
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
