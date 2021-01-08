package sun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class NacosClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class, args);
    }

    @GetMapping("/helloNacos")
    public String helloNacos(){
        return "你好，nacos！";
    }
}
