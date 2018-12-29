package org.labsse.demo.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.labsse.demo.queue.DeferredResultHolder;
import org.labsse.demo.queue.MockQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @author lijiechu
 * @create on 2018/12/26
 * @description
 */
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        logger.info("主线程开始");


        String orderNumber = RandomStringUtils.randomNumeric(8);
//        Callable<String> result = () -> {
//            logger.info("副线程开始");
//            Thread.sleep(1000);
//            logger.info("副线程返回");
//
//            return "successful";
//        };
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);

        logger.info("主线程返回");
        return result;
    }
}
