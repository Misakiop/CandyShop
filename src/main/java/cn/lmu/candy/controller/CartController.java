package cn.lmu.candy.controller;

import cn.lmu.candy.domain.Cart;
import cn.lmu.candy.domain.CartItemDto;
import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    // 添加商品到购物车
    @PostMapping("/add")
    @ResponseBody
    public ResponseData<?> addProductToCart(@RequestBody CartItemDto cartItemDto, HttpServletRequest request) {
        ResponseData<?> responseData = new ResponseData<>();

        try {
            if (cartService.add(cartItemDto, request)) {
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("商品已成功加入购物车");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("商品加入购物车失败");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }

    // 更新购物车中商品数量
    @PostMapping("/update")
    @ResponseBody
    public ResponseData<?> updateCart(@RequestBody CartItemDto cartItemDto, HttpServletRequest request) {
        ResponseData<?> responseData = new ResponseData<>();

        try {
            if (cartService.update(cartItemDto, request)) {
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("购物车更新成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("购物车更新失败");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }

    // 删除购物车中指定商品
    @PostMapping("/delete")
    @ResponseBody
    public ResponseData<?> deleteCart(@RequestBody String[] idArr, HttpServletRequest request) {
        ResponseData<?> responseData = new ResponseData<>();

        try {
            if (cartService.delete(idArr, request)) {
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("购物车商品删除成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("购物车商品删除失败");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }

    // 清空购物车
    @PostMapping("/clear")
    @ResponseBody
    public ResponseData<?> clearCart(HttpServletRequest request) {
        ResponseData<?> responseData = new ResponseData<>();

        try {
            if (cartService.clear(request)) {
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("购物车已清空");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("清空购物车失败");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }

    // 获取购物车列表
    @GetMapping("/list")
    @ResponseBody
    public ResponseData<Cart> getCartList(HttpServletRequest request) {
        ResponseData<Cart> responseData = new ResponseData<>();

        try {
            Cart cart = cartService.getCart(request);

            if (cart != null) {
                responseData.setData(cart);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("购物车获取成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("购物车为空");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }
}
