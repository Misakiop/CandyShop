package cn.lmu.candy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemVo {
    private Candys candys;
    private Integer buyNum;
    private Float buyPrice;
}
