package dao;

import Util.StringUtil;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {
    //学生添加
    public int add(Connection con, Student student) throws Exception {
        String sql = "Insert into student values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getStudentId());
        pstmt.setString(2, student.getName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, student.getYear());
        pstmt.setString(5, student.getMonth());
        pstmt.setString(6, student.getDay());
        pstmt.setString(7, student.getPolitics_status());
        pstmt.setString(8, student.getAddress());
        pstmt.setString(9, student.getPhone_num());
        pstmt.setString(10, student.getDormitory_num());
        pstmt.setInt(11, student.getSecondaryId());
        pstmt.setInt(12, student.getClassId());
        return pstmt.executeUpdate();
    }

    //学生查询
    public ResultSet list(Connection con, Student student) throws Exception {
        StringBuilder strb = new StringBuilder("select *from student stu,secondary scd,studentclass stc where stu.secondaryId=scd.id AND stu.classId=stc.id");
        if (StringUtil.isNotEmpty(student.getName())) {
            strb.append(" and stu.name like '%" + student.getName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStudentId())) {
            strb.append(" and stu.studentId like '%" + student.getStudentId() + "%'");
        }
        if (student.getSecondaryId() != null && student.getSecondaryId() != -1) {
            strb.append(" and stu.secondaryId =" + student.getSecondaryId());
        }
        if (student.getClassId() != null && student.getClassId() != -1) {
            strb.append(" and stu.classId =" + student.getClassId());
        }

        PreparedStatement pstmt = con.prepareStatement(strb.toString());
        return pstmt.executeQuery();
    }

    //学生修改
    public int update(Connection con, Student student) throws Exception {
        String sql = "update student set studentId=? ,name=? ,sex=? ,year=? ,month=? ,day=? ,politics_status=? ,address=? ,phone_num=? ,dormitory_num=? ,secondaryId=? ,classId=? where id =?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getStudentId());
        pstmt.setString(2, student.getName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, student.getYear());
        pstmt.setString(5, student.getMonth());
        pstmt.setString(6, student.getDay());
        pstmt.setString(7, student.getPolitics_status());
        pstmt.setString(8, student.getAddress());
        pstmt.setString(9, student.getPhone_num());
        pstmt.setString(10, student.getDormitory_num());
        pstmt.setInt(11, student.getSecondaryId());
        pstmt.setInt(12, student.getClassId());
        pstmt.setInt(13, student.getId());
        return pstmt.executeUpdate();
    }

    //删除学生
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from student where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }
}
