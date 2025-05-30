package org.example.managesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.managesystem.entity.Professor;

@Mapper
public interface ProfessorMapper {
    // 根据姓名获取教师信息
    @Select("SELECT * FROM professor WHERE name = #{name}")
    Professor findByUsername(String name);

    // 更新教师信息
    @Update("UPDATE professor SET " +
            "name = #{name}, " +
            "subject = #{subject}, " +
            "classnum = #{classnum}, " +
            "phone = #{phone}, " +
            "email = #{email} " +
            "WHERE num = #{num}")
    int updateProfessor(Professor professor);

    // 更新头像
    @Update("UPDATE professor SET photo = #{photoUrl} WHERE num = #{professorNum}")
    int updatePhoto(@Param("professorNum") String num, @Param("photoUrl") String photoUrl);
}
