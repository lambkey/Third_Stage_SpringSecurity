package net.seehope.springsecurity.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/2/13 23:48
 * 模拟队列
 */
@Slf4j
@Component
public class MockQueue {
    /*
    * 接收到订单之后，存储到改字段
    * */
    private String placeOrder;

    /*
    * 订单处理完毕，结果存储到该字段
    * */
    private String completeOrder;

    public  String getPlaceOrder(){return placeOrder;}

    public void setPlaceOrder(String placeOrder){
        //模拟应用2，监听消息队列
        new Thread(()->{
            log.info("get order request"+placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder=placeOrder;
            log.info("order complete");
        }).start();
    }
    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
