package cn.tedu.travel.dao;

import cn.tedu.travel.domain.PageBean;
import cn.tedu.travel.domain.Route;

import java.util.List;

/**
 * ClassName: RouteDao
 * Package: cn.tedu.travel.dao
 * Description:
 *
 * @Author
 * @Create 2023/1/6 9:45
 * @Version 1.0
 */
public interface RouteDao {

    /**
     * 根据cid查询总记录数
     */
    public int findTotalCount(PageBean pageBean);

    /**
     * 根据cid，start，pageSize查询当前页的数据集合
     */
    public List<Route> findByPage(PageBean pageBean);

    public Route findOne(int id);
}
