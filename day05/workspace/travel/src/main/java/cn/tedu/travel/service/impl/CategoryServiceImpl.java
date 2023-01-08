package cn.tedu.travel.service.impl;

import cn.tedu.travel.dao.CategoryDao;
import cn.tedu.travel.dao.impl.CategoryDaoImpl;
import cn.tedu.travel.domain.Category;
import cn.tedu.travel.service.CategoryService;
import jdk.nashorn.internal.ir.CallNode;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    public CategoryDao dao=new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        List<Category> list=dao.finAll();
        return list;
    }
}
