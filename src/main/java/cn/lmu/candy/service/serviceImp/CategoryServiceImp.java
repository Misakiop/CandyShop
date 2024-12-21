package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.Category;
import cn.lmu.candy.mapper.CategoryMapper;
import cn.lmu.candy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Cacheable(cacheNames = "categoryCache", key = "'candys_'+#pageNum+'_'+#pageSize")
    public List<Category> getCategoryList() {
        return this.categoryMapper.findAll();
    }

    @Override
    @Cacheable(cacheNames = "categoryCache", key = "'category_' + #userId", unless = "#result == null")
    public Category getCategoryById(Integer id) {
        return this.categoryMapper.findById(id);
    }

    @Override
    public int createCategory(Category category) {
        return this.categoryMapper.add(category);
    }

    @Override
    public int editCategory(Category category) {
        return this.categoryMapper.update(category);
    }

    @Override
    public int deleteCategoryById(Integer id) {
        return this.categoryMapper.delete(id);
    }

}
