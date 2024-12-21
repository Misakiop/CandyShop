package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category where id=#{id}")
    @Results(id = "CategoryMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name")})
    public Category findById(Integer id);

    @Select("select * from category")
    @ResultMap("CategoryMap")
    public List<Category> findAll();

    @Insert("insert into category(name) values(#{name})" )
    public int add(Category category);
    @Update("update category set name=#{name} where id=#{id}")
    public int update(Category category);

    @Delete("delete from category where id=#{id}")
    public int delete(Integer id);
}
