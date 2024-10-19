package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.Candys;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CandyMapper {
    @Select("SELECT * FROM db_candy.candys")
    @ResultMap("CandyMap")
    public List<Candys> findAllcandys();

    @Select("select * from db_candy.candys where id=#{id}")
    @Results(id = "CandyMap",value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "category", column = "category"),
            @Result(property = "price", column = "price"),
            @Result(property = "addtime", column = "addtime"),
            @Result(property = "imguid", column = "imguid"),
//          @Result(property = "candyList",column = "id",
//                    many = @Many(select = "com.example.article_api.dao.CommentMapper.getCommentsByAid"
//                            ,fetchType = FetchType.EAGER))
    })
    public Candys findcandysByid(Integer id);

    @Insert("insert into db_candy.candys(id,name,comment,category,price,addtime,imguid) " +
            "values(#{id},#{name},#{comment},#{category},#{price},#{addtime},#{imguid})")
    public int insert(Candys candys);

    @Update("update db_candy.candys set name=#{name},comment=#{comment},category=#{category},price=#{price},addtime=#{addtime},imguid=#{imguid} where id=#{id}")
    public int update(Candys candys);

    @Delete("delete from db_candy.candys where id=#{id}")
    public int delete(Integer id);
}
