package cn.tedu.travel.service.impl;


import cn.tedu.travel.dao.FavoriteDao;
import cn.tedu.travel.dao.RouteDao;
import cn.tedu.travel.dao.RouteImgeDao;
import cn.tedu.travel.dao.SellerDao;
import cn.tedu.travel.domain.PageBean;
import cn.tedu.travel.domain.Route;
import cn.tedu.travel.domain.RouteImg;
import cn.tedu.travel.domain.Seller;
import cn.tedu.travel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routemapper;
    @Autowired
    private RouteImgeDao routeImgemapper;
    @Autowired
    private SellerDao sellermapper;
    @Autowired
    private FavoriteDao favoritemapper;
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装PageBean
        PageBean<Route> pb=new PageBean<>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        pb.setCid(cid);
        pb.setRname(rname);
        //设置总记录数
        int totalCount=routemapper.findTotalCount(pb);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        pb.setStart(start);
        List<Route> list=routemapper.findByPage(pb);
        pb.setList(list);

        //设置search线路
        //设置总页数=总记录数/每页显示条数
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        //1.根据id去route表中查询route对象
        Route route=routemapper.findOne(Integer.parseInt(rid));

        //2.根据route的id查询图片集合信息
        List<RouteImg> routeImgList = routeImgemapper.findById(route.getRid());
        //2.2将集合设置到route对象
        route.setRouteImgList(routeImgList);
        //3.根据route的sid(商家id)查询商家的对象
        Seller seller = sellermapper.findById(route.getSid());
        route.setSeller(seller);

        //4.查询收藏次数
        int count=favoritemapper.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
