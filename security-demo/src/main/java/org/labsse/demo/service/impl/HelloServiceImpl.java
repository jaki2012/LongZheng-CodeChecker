package org.labsse.demo.service.impl;

import org.labsse.demo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author lijiechu
 * @create on 2018/12/24
 * @description
 */
@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public String greeting(String man) {
        return man;
    }
}
