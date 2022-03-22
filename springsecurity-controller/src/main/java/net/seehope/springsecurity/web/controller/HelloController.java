package net.seehope.springsecurity.web.controller;

import net.seehope.springsecurity.util.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoinYang
 * @date 2022/2/10 10:55
 */
@RequestMapping("/hello")
@RestController
public class HelloController {
    @GetMapping
    public JSONResult REhello(){
        return JSONResult.ok("success");
    }
}
