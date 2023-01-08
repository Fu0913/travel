package cn.tedu.travel.dao;

import cn.tedu.travel.domain.Favorite;
import cn.tedu.travel.domain.FavoriteId;

/**
 * ClassName: FavoriteDao
 * Package: cn.tedu.travel.dao
 * Description:
 *
 * @Author
 * @Create 2023/1/6 9:52
 * @Version 1.0
 */
public interface FavoriteDao {

    /**
     * 根据rid和uid查询收藏信息
     * @param favoriteId
     * @return
     */
    public Favorite findByRidAndUid(FavoriteId favoriteId);

    /**
     * 根据rid查询收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    void add(FavoriteId favoriteId);
}
