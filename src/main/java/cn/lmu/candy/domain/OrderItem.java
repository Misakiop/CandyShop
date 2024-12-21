package cn.lmu.candy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public class OrderItem {
    private int id;
    @JsonIgnore
    private Order order;
    private Candys candys;
    private int buyNum;
    private float buyPrice;

}
