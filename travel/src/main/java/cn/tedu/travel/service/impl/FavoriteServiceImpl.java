package cn.tedu.travel.service.impl;


import cn.tedu.travel.dao.FavoriteDao;
import cn.tedu.travel.domain.Favorite;
import cn.tedu.travel.domain.FavoriteId;
import cn.tedu.travel.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteDao favoritemapper;
    @Override
    public boolean isFavorite(FavoriteId favoriteId) {
        Favorite favorite = favoritemapper.findByRidAndUid(favoriteId);
        return favorite!=null;//如果对象有值则为true;
    }

    @Override
    public void add(FavoriteId favoriteId) {
        favoritemapper.add(favoriteId);
    }
}
