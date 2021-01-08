package sun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.entity.Student;
import sun.service.StudentService;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/test")
    public String test(){
        List<Student> list = studentService.getList();
        for (Student student : list) {
            System.out.println(student);
        }
        return "你好";
    };
}
