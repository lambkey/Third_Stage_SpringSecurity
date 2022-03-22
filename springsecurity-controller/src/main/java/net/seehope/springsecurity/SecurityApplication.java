package net.seehope.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author JoinYang
 * @date 2022/2/10 10:52
 */
@SpringBootApplication
@MapperScan("net.seehope.**.mapper")
public class SecurityApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
