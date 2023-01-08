package cn.tedu.travel.service.impl;

import cn.tedu.travel.dao.CategoryDao;

import cn.tedu.travel.domain.Category;
import cn.tedu.travel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao mapper;
    @Override
    public List<Category> findAll() {
        return mapper.findAll();
    }
}
