package net.seehope.springsecurity.web.async;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.seehope.springsecurity.util.JSONResult;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author JoinYang
 * @date 2022/2/13 22:37
 */
@Slf4j
@RestController
@RequestMapping("/async")
@AllArgsConstructor
public class AsyncController {
    private MockQueue mockQueue;
    private DeferredResultHolder deferredResultHolder;
    /*@GetMapping("/order/{id}")
    public Callable<JSONResult> getOrder(@PathVariable String id){
        log.info("------- main start-------");
        Callable<JSONResult> callable =new Callable<JSONResult>() {
            @Override
            public JSONResult call() throws Exception {
                log.info("-------son start-------");
                Thread.sleep(2000);
                log.info("-------son end-------");
                return JSONResult.ok("success");
            }
        };
        log.info("------- main end-------");
        return callable;
    }*/

    @GetMapping("/order/{id}")
    public DeferredResult<JSONResult> getOrder(@PathVariable String id) throws InterruptedException {

        log.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);

        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<JSONResult> deferredResult =new DeferredResult<>();

        deferredResultHolder.getMap().put(orderNumber, deferredResult);

        log.info("主线程结束");

        return deferredResult;
    }
}
