package cn.ncepu.mydeveloping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Guodong
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"cn.ncepu"})
@EnableTransactionManagement
public class MydevelopingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MydevelopingApplication.class, args);
    }

}
//@MapperScan("cn.ncepu.mydeveloping.mapper")//在MyBatisPlusConfig里面注释一次即可
