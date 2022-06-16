package dao;

import Util.StringUtil;
import model.StudentClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentClassDao {
    //班级添加
    public int add(Connection con, StudentClass studentClass) throws Exception {
        String sql = "Insert into studentClass values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, studentClass.getStudentClass_name());
        pstmt.setString(2, studentClass.getStudentClass_desc());
        return pstmt.executeUpdate();
    }

    //查询班级
    public ResultSet list(Connection con, StudentClass studentClass) throws Exception {
        StringBuilder stub = new StringBuilder("select * from studentClass");
        if (StringUtil.isNotEmpty(studentClass.getStudentClass_name())) {
            stub.append(" and studentClass_name like '%" + studentClass.getStudentClass_name() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(stub.toString().replace("and", "where"));
        return pstmt.executeQuery();
    }

    //删除学院
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from studentclass where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    //修改班级
    public int update(Connection con, StudentClass studentClass) throws Exception {
        String sql = "update studentclass set studentclass_name=? ,studentclass_desc=? where id =?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, studentClass.getStudentClass_name());
        pstmt.setString(2, studentClass.getStudentClass_desc());
        pstmt.setInt(3, studentClass.getId());
        return pstmt.executeUpdate();
    }
}
