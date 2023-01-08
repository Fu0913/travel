package cn.tedu.travel.test;

import cn.tedu.travel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试数据库连接是否成功
 */
public class TestDemo {
    public static void main(String[] args) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        System.out.println(con);
    }

}
