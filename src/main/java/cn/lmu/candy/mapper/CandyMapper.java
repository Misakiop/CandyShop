package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.domain.OrderItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CandyMapper {
    @Select("SELECT * FROM db_candy.candys")
    @ResultMap("CandyMap")
    public List<Candys> findAllcandys();
    //用户访问
    @Select("SELECT * FROM db_candy.candys WHERE (state = 1 OR state = 2)")
    public List<Candys> UserfindAllcandys();

    @Select("select * from db_candy.candys where id=#{id}")
    @Results(id = "CandyMap",value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "price", column = "price"),
            @Result(property = "num", column = "num"),
            @Result(property = "kgs", column = "kgs"),
            @Result(property = "size", column = "size"),
            @Result(property = "creationdate", column = "creationdate"),
            @Result(property = "expirationdate", column = "expirationdate"),
            @Result(property = "storagemethod", column = "storagemethod"),
            @Result(property = "addtime", column = "addtime"),
            @Result(property = "state", column = "state"),
            @Result(property = "imguid", column = "imguid"),
            @Result(property = "category",column = "categoryid",
                    one = @One(select = "cn.lmu.candy.mapper.CategoryMapper.findById",fetchType = FetchType.EAGER))
    })
    public Candys findcandysByid(String id);

    @Select("SELECT * FROM db_candy.candys WHERE name LIKE CONCAT('%', #{name}, '%')")
    public List<Candys> findcandysbyname(String name);
    //用户访问
    @Select("SELECT * FROM db_candy.candys WHERE name LIKE CONCAT('%', #{name}, '%') AND (state = 1 OR state = 2)")
    public List<Candys> Userfindcandysbyname(String name);

    @Insert("insert into db_candy.candys(id,name,comment,categoryid,price,num,kgs,size,creationdate,expirationdate,storagemethod,addtime,state,imguid) " +
            "values(#{id},#{name},#{comment},#{category.id},#{price},#{num},#{kgs},#{size},#{creationdate},#{expirationdate},#{storagemethod},#{addtime},#{state},#{imguid})")
    public int insert(Candys candys);

    @Update("<script>" +
            "UPDATE db_candy.candys" +
            "<set>" +
            "<if test='id != null'>id = #{id},</if>" +
            "<if test='name != null'>name = #{name},</if>" +
            "<if test='comment != null'>comment = #{comment},</if>" +
            "<if test=\"category != null and category.id != null\">categoryid = #{category.id},</if>\n" +
//            "<if test='categoryid == null'>category.id = 9,</if>" +
            "<if test='price != null'>price = #{price},</if>" +
            "<if test='num != null'>num = #{num},</if>" +
            "<if test='kgs != null'>kgs = #{kgs},</if>" +
            "<if test='size != null'>size = #{size},</if>" +
            "<if test='creationdate != null'>creationdate = #{creationdate},</if>" +
            "<if test='expirationdate != null'>expirationdate = #{expirationdate},</if>" +
            "<if test='storagemethod != null'>storagemethod = #{storagemethod},</if>" +
            "<if test='state != null'>state = #{state},</if>" +
            "<if test='state == null'>state = 0,</if>" +
            "<if test='addtime != null'>addtime = #{addtime},</if>" +
            "<if test='imguid != null'>imguid = #{imguid},</if>" +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    public int update(Candys candys);

    @Delete("delete from db_candy.candys where id=#{id}")
    public int delete(String id);

//    @Update("update db_candy.candys set state=#{state} where num<0")

    @Update("update candys set num=num-#{buyNum} where id=#{candys.id}")
    int updateCnum(OrderItem item);

    @Select("select num from db_candy.candys where id=#{candys.id}")
    int findnum(OrderItem item);

    @org.apache.ibatis.annotations.SelectProvider(type = cn.lmu.candy.mapper.SelectProvider.class,method = "findCandysWhere")
    @ResultMap("CandyMap")
    List<Candys> findByWhere(Candys candys);
    @org.apache.ibatis.annotations.SelectProvider(type = cn.lmu.candy.mapper.SelectProvider.class,method = "findCandysWhereUser")
    @ResultMap("CandyMap")
    List<Candys> findByWhereUser(Candys candys);

}
