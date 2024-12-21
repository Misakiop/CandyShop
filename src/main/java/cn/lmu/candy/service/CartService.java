package cn.lmu.candy.service;

import cn.lmu.candy.domain.Cart;
import cn.lmu.candy.domain.CartItemDto;
import cn.lmu.candy.domain.CartItemVo;
import cn.lmu.candy.domain.UserInfo;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;

public interface CartService {
    public  boolean add(CartItemDto cartItemDto, HttpServletRequest request);

    public  boolean update(CartItemDto cartItemDto, HttpServletRequest request);

    public boolean delete(String[] idArray, HttpServletRequest request);

    public boolean clear(HttpServletRequest request);

    public Cart getCart(HttpServletRequest request);

    public UserInfo getUser(HttpServletRequest request);

    PageInfo<CartItemVo> getPagedCart(HttpServletRequest request, Integer pageNum, Integer pageSize);

}
