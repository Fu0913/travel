package cn.tedu.travel.dao;

import cn.tedu.travel.domain.RouteImg;

import java.util.List;

/**
 * ClassName: RouteImgeDao
 * Package: cn.tedu.travel.dao
 * Description:
 *
 * @Author
 * @Create 2023/1/6 9:51
 * @Version 1.0
 */
public interface RouteImgeDao {
    /**
     * 根据route的id查询图片
     * @param rid
     * @return
     */
    public List<RouteImg> findById(int rid);
}
