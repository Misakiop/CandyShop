package cn.lmu.candy.service;

import cn.lmu.candy.domain.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategoryList();

    public Category getCategoryById(Integer id);

    public int createCategory(Category category);

    public int editCategory(Category category);

    public int deleteCategoryById(Integer id);
}
