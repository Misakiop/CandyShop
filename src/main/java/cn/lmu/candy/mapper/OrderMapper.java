package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from `order` where id=#{id}")
    @Results(id = "OrderMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "money", column = "money"),
            @Result(property = "receiverAddress", column = "receiverAddress"),
            @Result(property = "receiverName", column = "receiverName"),
            @Result(property = "receiverPhone", column = "receiverPhone"),
            @Result(property = "payState", column = "payState"),
            @Result(property = "orderState", column = "orderState"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "cn.lmu.candy.mapper.findById", fetchType =
                            FetchType.EAGER)),
            @Result(property = "orderItemList", column = "id",
                    many = @Many(select = "cn.lmu.bookstoreweb.dao.OrderItemMapper.findByOrderId", fetchType =
                            FetchType.EAGER))
    })
    public Order findById(String id);

    @Select("select * from `order`")
//    @ResultMap("OrderMap")
    public List<Order> findAll();

    @Select("select * from `order` where user_id=#{id}")
    public List<Order> findByUserId(Integer id);

    @Insert("insert into `order`(id,money,receiverAddress,receiverName,receiverPhone,payState,orderState,user_id,orderTime) " +
            "values(#{id},#{money},#{receiverAddress},#{receiverName},#{receiverPhone},#{payState},#{orderState},#{userInfo.id},#{orderTime})")
    public int add(Order order);

    @Update("<script>" +
            "UPDATE db_candy.`order`" +
            "<set>" +
            "<if test='id != null'>id = #{id},</if>" +
            "<if test='money != null'>money = #{money},</if>" +
            "<if test='receiverAddress != null'>receiverAddress = #{receiverAddress},</if>" +
            "<if test='receiverName != null'>receiverName = #{receiverName},</if>" +
            "<if test='receiverPhone != null'>receiverPhone = #{receiverPhone},</if>" +
            "<if test='payState != null'>payState = #{payState},</if>" +
            "<if test='orderTime != null'>orderTime = #{orderTime},</if>" +
            "<if test='orderState != null'>orderState = #{orderState},</if>" +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    public int update(Order order);

    @Delete("delete from `order` where id=#{id}")
    public int delete(String id);


}

