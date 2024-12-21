package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.OrderItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    @Insert("insert into orderItem(order_id, candys_id,buyNum,buyPrice) " +
            "values(#{order.id},#{candys.id},#{buyNum},#{buyPrice})" )
    public int add(OrderItem orderItem);

    @Select("select * from orderItem where order_id=#{id}")
    @Results(id = "OrderItemMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "candysId", column = "candys_id"),
            @Result(property = "buyNum", column = "buy_num"),
            @Result(property = "buyPrice", column = "buy_price"),
            @Result(property = "candys", column = "candys_id",
                    one = @One(select = "cn.lmu.candy.dao.CandysMapper.findCandysById", fetchType = FetchType.EAGER))
    })
    public List<OrderItem> findByOrderId(String id);


}
