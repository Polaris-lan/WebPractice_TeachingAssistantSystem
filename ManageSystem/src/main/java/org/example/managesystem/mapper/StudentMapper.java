package org.example.managesystem.mapper;

import org.apache.ibatis.annotations.*;
import org.example.managesystem.entity.Student;
import org.example.managesystem.entity.User;

import java.util.List;

@Mapper
public interface StudentMapper {
    //增
    @Insert("insert into student(id,stuname, classnum, stunum, path, homeworkstate) values (#{id},#{stuname},#{classnum},#{stunum},#{path},#{homeworkstate})")
    int addStudent(Student student);
    //删
    @Delete("delete from student where stunum=#{stunum}")
    int deleteStudent(String stunum);
    //改,这里需要注意，因为id为自增主键，不需要初始化为空，否则会匹配错误
    @Update("update student set stuname=#{stuname},classnum=#{classnum},stunum=#{stunum},path=#{path},homeworkstate=#{homeworkstate}")
    int updateStudent(Student student);
    //查(模糊查找)
    @Select("select * from student")
    List<Student> getAll();

    @Select("<script>" +
            "SELECT * FROM student " +
            "<where>" +
            "   <if test='stuname != null and stuname != \"\"'>" +
            "       AND stuname LIKE CONCAT('%', #{stuname}, '%')" +
            "   </if>" +
            "   <if test='classnum != null and classnum != \"\"'>" +
            "       AND classnum LIKE CONCAT('%', #{classnum}, '%')" +
            "   </if>" +
            "   <if test='stunum != null and stunum != \"\"'>" +
            "       AND stunum LIKE CONCAT('%', #{stunum}, '%')" +
            "   </if>" +
            "</where>" +
            "ORDER BY id ASC" +
            "</script>")
    List<Student> findStudentsByCondition(Student student);
}
