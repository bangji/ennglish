package cn.news.entity;

import java.util.Date;

/**
 * @Author: Clb
 * @Date: 2018/8/23 14:37
 * @Description:
 */
public class User {
    private String id;
    private String name;
    private char gender;
    private Date birthday;
    private int grade;

    public User(String id, String name, char gender, Date birthday, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public char getGender() {
        return gender;
    }

    public User setGender(char gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public int getGrade() {
        return grade;
    }

    public User setGrade(int grade) {
        this.grade = grade;
        return this;
    }
}
