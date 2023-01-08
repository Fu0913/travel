package cn.tedu.travel.dao.impl;

import cn.tedu.travel.dao.CategoryDao;
import cn.tedu.travel.domain.Category;
import cn.tedu.travel.util.JDBCUtils;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    //创建执行sql语句对象JdbcTemplate
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> finAll() {
        //1.编写sql语句
        String sql="select * from tab_category";
        //2.运行sql
        List<Category> list = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return list;
    }
}
