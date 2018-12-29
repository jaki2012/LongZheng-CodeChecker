package org.labsse.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author lijiechu
 * @create on 2018/12/28
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlWithHttpMethods {

    private String url;

    private Set<String> httpMethods;
}
