package cn.tedu.travel.dao.impl;

import cn.tedu.travel.dao.UserDao;
import cn.tedu.travel.domain.User;
import cn.tedu.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据用户名查询数据
     * @param username
     * @return
     */
    @Override
    public User findUserName(String username) {
        User user=null;
        try{
            //1.定义sql
            String sql="select * from tab_user where username=?";
            //2.执行sql
             user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){

        }
        //3.返回结果
        return  user;//两种结果：1.查到了，有数据2：没查到，就是Null;
    }

    /**
     * 保存用户信息
     * @param user
     */
    @Override
    public void save(User user) {
        //1.定义sql
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";
        //2.执行sql
        template.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail()
        );
    }
}
