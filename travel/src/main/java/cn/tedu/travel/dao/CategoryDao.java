package cn.tedu.travel.dao;

import cn.tedu.travel.domain.Category;

import java.util.List;

public interface CategoryDao {


    /**
     * 查询所有
     *
     * @return
     */
  List<Category> findAll();
}
