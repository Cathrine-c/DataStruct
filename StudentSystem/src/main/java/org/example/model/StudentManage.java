package org.example.model;

import org.example.Dao.studentDao;

import java.util.List;
import java.util.Scanner;

public class StudentManage {


    public void menu(){

        int chose;
        do{
            System.out.println("********************************");
            System.out.println("=========学生成绩管理系统==========");
            System.out.println("1.新增学生信息");
            System.out.println("2.修改学生信息");
            System.out.println("3.删除学生信息");
            System.out.println("4.查询学生信息");
            System.out.println("5.退出该系统");
            System.out.println("请输入您要办理的业务对应的序号：");

            Scanner sc = new Scanner(System.in);
            chose = sc.nextInt();
            switch (chose){
                case 1:
                    Add();
                    break;
                case 2:
                    Update();
                    break;
                case 3:
                    Delete();
                    break;
                case 4:
                    Show();
                    break;
                case 5:
                    System.out.println("您确定要退出系统？？(y/n)");
                    Scanner scan = new Scanner(System.in);
                    String scanExit = scan.next();
                    if (scanExit.equals("y")){
                        System.exit(-1);
                        System.out.println("您已经成功退出系统！");

                    }
                    break;
                default:
                    break;
            }

        }while (chose!=5);

    }

    private void Show() {
        System.out.println("************************");
        System.out.println("====查询学生====");
        System.out.println("学生的信息如下：");
        System.out.println("学号  \t姓名\t性别 \t生日 \t专业 \t年级");
        studentDao stuDao=new studentDao();
        List<Student> list=stuDao.list();
        for (Student stuList:list) { //循环打印出查询结果
            System.out.println(stuList.getId()+"\t"+stuList.getName()+"\t"+stuList.getSex()+"\t"+stuList.getBirthday()+"\t"+stuList.getMajor()+"\t"+stuList.getClas());
        }

        System.out.println("************************");
    }



    private void Delete() {
        Scanner sc = new Scanner(System.in);
        int id;
        System.out.println("===删除学生信息===");
        System.out.println("请输入要删除的学生学号：");
        id = sc.nextInt();
        System.out.println("该学生的信息详情如下：");

        studentDao studentDao = new studentDao();
        System.out.println("学生学号："+studentDao.findStudentById(id).getId());
        System.out.println("学生姓名："+studentDao.findStudentById(id).getName());
        System.out.println("学生性别："+studentDao.findStudentById(id).getSex());
        System.out.println("学生生日："+studentDao.findStudentById(id).getBirthday());
        System.out.println("学生专业："+studentDao.findStudentById(id).getMajor());
        System.out.println("学生年级："+studentDao.findStudentById(id).getClas());

        System.out.println("确定要删除该学生信息？(y/n)：");
        Scanner sc1 = new Scanner(System.in);
        String s = sc1.next();

        if (s.equals("y")){
            Student student = new Student(id,null,null, (String) null,null,null);
            studentDao studentDao1 = new studentDao();
            boolean meg = studentDao.deleteStudent(id);
            if (meg) {
                System.out.println("删除学生信息成功！");

            }else {
                System.out.println("删除失败！");
            }
        }
    }



    private void Update() {

        Scanner s=new Scanner(System.in);
        int id;
        System.out.println("====修改学生信息====");
        System.out.println("请输入要修改的学生学号：");
        id=s.nextInt();
        System.out.println("学生的信息如下：");
        studentDao stuDao=new studentDao();
        System.out.println("学生学号："+stuDao.findStudentById(id).getId());
        System.out.println("学生姓名："+stuDao.findStudentById(id).getName());
        System.out.println("学生性别："+stuDao.findStudentById(id).getSex());
        System.out.println("学生生日："+stuDao.findStudentById(id).getBirthday());
        System.out.println("学生专业："+stuDao.findStudentById(id).getMajor());
        System.out.println("学生年级："+stuDao.findStudentById(id).getClas());


        System.out.println("请输入新的学生信息：");
        Scanner stuUp=new Scanner(System.in);
        String name,sex,birthday,major,clas;
        System.out.println("请输入要修改的学生学号：");
        id=s.nextInt();
        System.out.println("学生姓名：");
        name=stuUp.next();
        System.out.println("学生性别：");
        sex=stuUp.next();
        System.out.println("学生生日：");
        birthday = stuUp.next();
        System.out.println("学生专业：");
        major = stuUp.next();
        System.out.println("学生年级：");
        clas = stuUp.next();

        Student stu=new Student(id,name,sex,birthday,major,clas);
        studentDao dao=new studentDao();
        boolean ok=dao.updateStudent(stu);
        if (ok) {
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }

    }


    private void Add() {

        String continute;
        do{
            Scanner s = new Scanner(System.in);
            int id;
            String name,birthday,sex,major,clas;

            System.out.println("===新增学生信息===");
            System.out.println("学号：");
            id = s.nextInt();
            System.out.println("姓名：");
            name = s.next();
            System.out.println("性别：");
            sex = s.next();
            System.out.println("生日：");
            birthday = s.next();
            System.out.println("专业：");
            major = s.next();
            System.out.println("年级：");
            clas = s.next();

            Student student = new Student(id,name,sex,birthday,major,clas);
            studentDao studentDao = new studentDao();
            boolean mes = studentDao.addStudent(student);
            if (mes) {
                System.out.println("新增成功！");

            }else {
                System.out.println("操作失败！");
            }
            System.out.println("是否继续新增？(y/n)");
            Scanner scan2 = new Scanner(System.in);
            continute = scan2.next();


        }while (continute.equals("y"));


    }




}
