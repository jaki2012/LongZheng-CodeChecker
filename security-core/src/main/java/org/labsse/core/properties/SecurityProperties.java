package org.labsse.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lijiechu
 * @create on 2018/12/27
 * @description
 */
@ConfigurationProperties(prefix = "labsse.security")
@Data
public class SecurityProperties {

    @Autowired
    private BrowserProperties browser;

    @Autowired
    private ValidateCodeProperties validateCode;
}
