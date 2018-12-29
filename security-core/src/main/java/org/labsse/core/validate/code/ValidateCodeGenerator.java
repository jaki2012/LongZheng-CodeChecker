package org.labsse.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author lijiechu
 * @create on 2018/12/28
 * @description
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);
}
