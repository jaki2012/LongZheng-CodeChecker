package org.labsse.demo.validate.code;

import org.labsse.core.validate.code.ImageCode;
import org.labsse.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author lijiechu
 * @create on 2018/12/28
 * @description 开闭原则 增量开发
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码");
        return null;
    }
}
