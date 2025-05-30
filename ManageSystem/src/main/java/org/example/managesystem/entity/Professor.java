package org.example.managesystem.entity;

public class Professor {
    Integer id;
    String num;
    String name;
    String subject;

    public Professor() {
    }

    public Professor(Integer id, String num, String name, String subject) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.subject = subject;
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
}
