package cn.tedu.travel.dao;

import cn.tedu.travel.domain.Seller;

/**
 * ClassName: SellerDao
 * Package: cn.tedu.travel.dao
 * Description:
 *
 * @Author
 * @Create 2023/1/6 9:46
 * @Version 1.0
 */
public interface SellerDao {
    /**
     * 根据id查询
     * @param sid
     * @return
     */
    public Seller findById(int sid);

}
