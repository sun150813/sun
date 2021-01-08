package sun.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "server")
public interface StudentService {
    @GetMapping("/test")
    String test();
}
