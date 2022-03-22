package net.seehope.springsecurity.web.async;

import lombok.Data;
import net.seehope.springsecurity.util.JSONResult;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LEGION
 * @date 2022/02/11 16:47
 **/

@Component
@Data
public class DeferredResultHolder {
    private Map<String, DeferredResult<JSONResult>> map = new HashMap<>();
}
