package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.Candys;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CandyMapper {
    @Select("SELECT * FROM db_candy.candys")
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
    })
    public Candys findcandysByid(Integer id);

    @Select("SELECT * FROM db_candy.candys WHERE name LIKE CONCAT('%', #{name}, '%')")
    public List<Candys> findcandysbyname(String name);


    @Insert("insert into db_candy.candys(name,comment,category,price,num,addtime,state,imguid) " +
            "values(#{name},#{comment},#{category},#{price},#{num},#{addtime},#{state},#{imguid})")
    public int insert(Candys candys);

    @Update("<script>" +
            "UPDATE db_candy.candys" +
            "<set>" +
            "<if test='name != null'>name = #{name},</if>" +
            "<if test='comment != null'>comment = #{comment},</if>" +
            "<if test='category != null'>category = #{category},</if>" +
            "<if test='price != null'>price = #{price},</if>" +
            "<if test='num != null'>num = #{num},</if>" +
            "<if test='state != null'>state = #{state},</if>" +
            "<if test='state == null'>state = 0,</if>" +
            "<if test='addtime != null'>addtime = #{addtime},</if>" +
            "<if test='imguid != null'>imguid = #{imguid},</if>" +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    public int update(Candys candys);

    @Update("update db_candy.candys set state=#{state} where id=#{id}")
    public int updatestate(Candys candys);

    @Delete("delete from db_candy.candys where id=#{id}")
    public int delete(Integer id);
}
