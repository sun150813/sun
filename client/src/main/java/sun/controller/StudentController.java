package sun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.service.StudentService;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/test")
    public String test(){

        return studentService.test();
    }

}
