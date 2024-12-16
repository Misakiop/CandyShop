package cn.lmu.candy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private String pid;
    private Integer buyNum;
    private Float buyPrice;
}
