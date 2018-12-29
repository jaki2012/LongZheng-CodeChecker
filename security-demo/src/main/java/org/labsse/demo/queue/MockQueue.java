package org.labsse.demo.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lijiechu
 * @create on 2018/12/26
 * @description
 */
@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String placeOrder;

    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {

        new Thread(() -> {
            try {
                logger.info("接到下单请求：" + placeOrder);
                this.placeOrder = placeOrder;
                // 模拟应用2处理下单
                Thread.sleep(1000);
                this.completeOrder = placeOrder;
                logger.info("下单请求处理完毕：" + placeOrder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
