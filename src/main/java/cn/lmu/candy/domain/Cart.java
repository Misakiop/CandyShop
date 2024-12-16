package cn.lmu.candy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private UserInfo userInfo; //某个用户
    private List<CartItemVo> cartItemVoList; //购物（产品、数量、价格）列表

    public Integer getTotalNum() {
        if (this.cartItemVoList == null || this.cartItemVoList.size() == 0) {
            return 0;
        }
        Integer total = 0;
        for (CartItemVo item : this.cartItemVoList) {
            total = total + item.getBuyNum();
        }
        return total;
    }

    public Float getTotalMoney() {
        if (this.cartItemVoList == null || this.cartItemVoList.size() == 0) {
            return 0F;
        }
        Float totalMoney = 0F;
        for (CartItemVo item : this.cartItemVoList) {
            totalMoney = totalMoney + item.getBuyNum()*item.getBuyPrice();
        }
        return totalMoney;
    }


    private Integer totalNum;
    private Float totalMoney;
}
