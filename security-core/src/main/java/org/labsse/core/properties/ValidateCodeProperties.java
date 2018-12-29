package org.labsse.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lijiechu
 * @create on 2018/12/28
 * @description
 */
@Data
@Component
public class ValidateCodeProperties {

    @Autowired
    private ImageCodeProperties image;
}
