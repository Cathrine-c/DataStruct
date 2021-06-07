package org.example.Dao;

import org.example.Util.DBUtil;
import org.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class studentDao {

    private Connection con ;
    private PreparedStatement ps;
    private ResultSet rs ;

    //添加学生信息
    public boolean addStudent(Student student){
        String sql = "insert into student(id,name,sex,birthday,major_id,class) values(?,?,?,?,?,?)";

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,student.getId());
            ps.setString(2,student.getName());
            ps.setString(3, student.getSex());
            ps.setString(4, student.getBirthday());
            ps.setString(5,student.getMajor());
            ps.setString(6,student.getClas());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            DBUtil.close(con,ps,rs);
        }

        return true;
    }


    //查看学生信息(所有)
    public List<Student> list(){

        List<Student> list = new ArrayList<>();
        String sql = "select * from student";


        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setBirthday(rs.getString("birthday"));
                student.setMajor(rs.getString("major_id"));
                student.setClas(rs.getString("class"));

                list.add(student);

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con,ps,rs);
        }

        return list;
    }


    //查看学生信息通过学生id
    public Student findStudentById(int id){
        Student student =null;
        String sql = "select * from student where id = ?";


        try {

            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1,id);
            rs = ps.executeQuery();

            while (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setBirthday(rs.getString("birthday"));
                student.setMajor(rs.getString("major_id"));
                student.setClas(rs.getString("class"));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            DBUtil.close(con,ps,rs);
        }

        return student;
    }


    //修改学生信息
    public boolean updateStudent(Student student){
        String sql = "update student set name=?,sex=?,birthday=?,major_id=?,class=? where id=?";

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,student.getName());
            ps.setString(2,student.getSex());
            ps.setString(3, student.getBirthday());
            ps.setString(4,student.getMajor());
            ps.setString(5,student.getClas());
            ps.setInt(6,student.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            DBUtil.close(con,ps,rs);
        }

        return true;

    }


    //删除学生信息
    public boolean deleteStudent(int id){
        String sql = "delete from student where id=?";


        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            DBUtil.close(con,ps,rs);
        }
        return true;

    }







}

