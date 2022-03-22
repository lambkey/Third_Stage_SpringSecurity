package net.seehope.springsecurity.web.async;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.seehope.springsecurity.util.JSONResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/2/14 0:41
 */
@Component
@Slf4j
@AllArgsConstructor
//此服务启动自动带动自身方法的运行
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    private MockQueue mockQueue;
    private DeferredResultHolder deferredResultHolder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(()->{

            //模拟应用1，监听消息队列
            while (true) {

                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
                    log.info("get order complete and orderNumber is {} ", mockQueue.getCompleteOrder());

                    deferredResultHolder.getMap().get(mockQueue.getCompleteOrder()).setResult(JSONResult.ok(mockQueue.getCompleteOrder()));

                    mockQueue.setCompleteOrder(null);

                    log.info("order done , clear queue");
                } else {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
