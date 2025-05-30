package org.example.managesystem.entity;

public class Student {
    Integer id;
    String stuname;
    String classnum;
    String stunum;
    String path;
    String homeworkstate;

    public Student() {
    }

    public Student(Integer id, String stuname, String classnum, String stunum, String path, String homeworkstate) {
        this.id = id;
        this.stuname = stuname;
        this.classnum = classnum;
        this.stunum = stunum;
        this.path = path;
        this.homeworkstate = homeworkstate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }

    public String getStunum() {
        return stunum;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHomeworkstate() {
        return homeworkstate;
    }

    public void setHomeworkstate(String homeworkstate) {
        this.homeworkstate = homeworkstate;
    }
}
