package cn.lmu.candy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value ={"handler"})
public class Order {
    private String id;
    private float money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private Integer payState;
    private Integer orderState;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    private UserInfo userInfo; //一个订单对应一个下单用户，1对1关系
    private List<OrderItem> orderItemList; //一个订单对应多个订单明细，1对多关系

}
