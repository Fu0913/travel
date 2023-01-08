package cn.tedu.travel.dao;

import cn.tedu.travel.domain.User;

public interface UserDao {
    /**
     * 1.根据用户名查询用户信息
     * @param username
     * @return
     */
    User findUserName(String username);

    /**
     * 2.保存用户信息
     * @param user
     */
    void save(User user);

    /**
     *3.登录方法
     * @param username
     * @param password
     * @return
     */
    User findByUserNameAndPassWord(String username, String password);
}
