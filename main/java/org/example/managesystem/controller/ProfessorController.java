package org.example.managesystem.controller;

import org.example.managesystem.entity.Professor;
import org.example.managesystem.mapper.ProfessorMapper;
import org.example.managesystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@CrossOrigin
public class ProfessorController {
    @Autowired
    ProfessorMapper professorMapper;

    @Autowired
    UserMapper userMapper;

    // 获取当前教师信息
    @RequestMapping("/info")
    public ResponseEntity<Professor> getProfessorInfo(String token) {
        try {
            // 从token中获取用户名
            //String username = userMapper.getUserNameAsToken(token);
            String username=token;
            // 查询教师信息
            Professor professor = professorMapper.findByUsername(username);
            if (professor == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(professor);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }



    // 更新教师信息
    @RequestMapping("/update")
    public ResponseEntity<String> updateProfessor(@RequestBody Professor professor) {
        try {
            // 验证当前用户是否有权限修改
            String username = userMapper.getUserNameAsToken(professor.getName());
            Professor current = professorMapper.findByUsername(username);

            if (current == null || !current.getNum().equals(professor.getNum())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("无权限修改");
            }
            int result = professorMapper.updateProfessor(professor);
            return result > 0
                    ? ResponseEntity.ok("更新成功")
                    : ResponseEntity.badRequest().body("更新失败");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("服务器错误");
        }
    }

    // 上传头像
    @RequestMapping("/uploadphoto")
    public ResponseEntity<Map<String, Object>> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String token) {
        try {
            // 验证用户
            String username = userMapper.getUserNameAsToken(token);
            Professor professor = professorMapper.findByUsername(username);
            if (professor == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "code", 401,
                        "message", "用户未找到或未授权"
                ));
            }
            // 检查文件是否为空
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                        "code", 400,
                        "message", "文件不能为空"
                ));
            }

//
//            // 文件存储逻辑 (示例: 存储到本地)
//            String fileName = professor.getNum() + "_" + System.currentTimeMillis() +
//                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            String filePath = "/uploads/professors/" + fileName;
//
//            // 创建目录
//            File dest = new File(filePath);
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();
//            }

            // 使用绝对路径
            //String fullUploadDir = "D:/ta-system/";

            //File dir = new File(fullUploadDir);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
            String fileName = professor.getNum() + "_" + System.currentTimeMillis() +
                   file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String filePath = "D:\\ta-system\\" + fileName;
            File dest = new File(filePath);
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();
//            }
            // 保存文件
            file.transferTo(dest);
            // 更新数据库中的照片路径
            String photoUrl = "/ta-system/" + fileName;
            professorMapper.updatePhoto(professor.getNum(), photoUrl);

            return ResponseEntity.ok(Map.of(
                    "code", 200,
                    "message", "上传成功",
                    "data", Map.of("url", photoUrl)
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "code", 500,
                    "message", "上传失败: " + e.getMessage()
            ));
        }
    }

}
