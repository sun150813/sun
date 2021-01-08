package sun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dao.StudentMapper;
import sun.entity.Student;
import sun.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService  {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getList() {
        return studentMapper.selectList(null);
    }
}
