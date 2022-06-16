package dao;

import Util.StringUtil;
import model.Secondary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SecondaryDao {

    //学院添加
    public int add(Connection con, Secondary secondary) throws Exception {
        String sql = "insert into secondary values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, secondary.getSecondary_name());
        pstmt.setString(2, secondary.getSecondary_desc());

        return pstmt.executeUpdate();
    }

    //查询学院
    public ResultSet list(Connection con, Secondary secondary) throws Exception {
        StringBuilder strb = new StringBuilder("select*from secondary");
        if (StringUtil.isNotEmpty(secondary.getSecondary_name())) {
            strb.append(" and secondary_name like '%" + secondary.getSecondary_name() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(strb.toString().replace("and", "where"));
        return pstmt.executeQuery();
    }

    //删除学院
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from secondary where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    //修改学院
    public int update(Connection con, Secondary secondary) throws Exception {
        String sql = "update secondary set secondary_name=? ,secondary_desc=? where id =?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, secondary.getSecondary_name());
        pstmt.setString(2, secondary.getSecondary_desc());
        pstmt.setInt(3, secondary.getId());
        return pstmt.executeUpdate();
    }
}
