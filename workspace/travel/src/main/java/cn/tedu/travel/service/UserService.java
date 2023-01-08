package cn.tedu.travel.service;

import cn.tedu.travel.domain.User;

public interface UserService {
    /**
     * 1.注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 2.验证登录
     * @param user
     * @return
     */
    User login(User user);
}
