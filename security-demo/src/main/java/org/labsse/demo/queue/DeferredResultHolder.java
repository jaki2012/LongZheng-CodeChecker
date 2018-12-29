package org.labsse.demo.queue;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijiechu
 * @create on 2018/12/26
 * @description
 */
@Component
@Data
public class DeferredResultHolder {

    private Map<String,DeferredResult<String>> map = new HashMap<>();

}
