package org.labsse.core.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author lijiechu
 * @create on 2018/12/28
 * @description
 */
@Component
@Data
public class ImageCodeProperties {

    private int width = 60;

    private int height = 23;

    private int expireSeconds = 60;

    private int length = 4;

    private String url;
}
