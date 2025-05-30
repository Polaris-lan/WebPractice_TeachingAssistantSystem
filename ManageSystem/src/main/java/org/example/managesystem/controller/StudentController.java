package org.example.managesystem.controller;

import org.example.managesystem.entity.Student;
import org.example.managesystem.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    StudentMapper studentMapper;

    //添加
    @RequestMapping("/addstu")
    public int addStu(@RequestBody Student student){

        return studentMapper.addStudent(student);
    }
    //删除
    @RequestMapping("/deletestu")
    public int deleteStu(String stunum){

        return studentMapper.deleteStudent(stunum);
    }
    //修改
    @RequestMapping("/updatestu")
    public int updateStu(@RequestBody Student student){

        return studentMapper.updateStudent(student);
    }

    //查询所有学生信息
//    @RequestMapping("/getall")
//    public List<Student> getAll(){
//        return studentMapper.getAll();
//    }
    //查询
    @RequestMapping("/getstu")
    public List<Student> getStudentsByCondition(Student student) {
        return studentMapper.findStudentsByCondition(student);
    }
}
