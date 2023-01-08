package cn.tedu.travel.service.impl;

import cn.tedu.travel.dao.UserDao;
import cn.tedu.travel.dao.impl.UserDaoImpl;
import cn.tedu.travel.domain.User;
import cn.tedu.travel.service.UserService;

public class UserServiceImpl implements UserService {
    /**
     * 1.注册用户方法
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        UserDao userDao = new UserDaoImpl();
        //1.根据用户名查询用户信息
        User u=userDao.findUserName(user.getUsername());
        //2.判断u是否为空
        if(u!=null){
            //用户名已经存在,注册失败
            return false;
        }
        //2.没有这个用户，保存用户信息
        userDao.save(user);
        return true;
    }
}
