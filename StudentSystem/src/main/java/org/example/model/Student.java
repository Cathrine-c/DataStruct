package org.example.model;


public class Student {

    public int id;
    public String name;
    public String sex;
    public String birthday;
    public String major;
    public String clas;


    public Student(int id, String name, String sex,String birthday, String major,String clas) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.major = major;
        this.clas = clas;
    }


    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", major='" + major + '\'' +
                ", clas='" + clas + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }
}
