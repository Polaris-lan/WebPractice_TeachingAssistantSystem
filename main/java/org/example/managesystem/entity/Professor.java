package org.example.managesystem.entity;

public class Professor {
    Integer id;
    String num;
    String name;
    String subject;
    String classnum;  // 授课班级
    String photo;     // 照片路径
    String phone;     // 联系电话
    String email;     // 电子邮箱

    public Professor() {
    }

    public Professor(Integer id, String num, String name, String subject, String classnum, String photo, String phone, String email) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.subject = subject;
        this.classnum = classnum;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
