package cn.lmu.candy.controller;

import cn.lmu.candy.domain.*;
import cn.lmu.candy.service.CartService;
import cn.lmu.candy.service.OrderService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * 订单API接口
 */
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    /**
     * 分页获取订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public ResponseData<PageInfo<Order>> getAllList(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
            ) {

        ResponseData<PageInfo<Order>> responseData = new ResponseData<>();

        try {
            // 获取分页数据
            PageInfo<Order> pageInfo = this.orderService.getAll(pageNum, pageSize);

            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
                // 返回成功，包含分页数据
                responseData.setData(pageInfo);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                // 无数据时返回
                responseData.setSuccess(true);
                responseData.setCode(400); // No Content
                responseData.setMsg("无订单数据");
            }
        } catch (Exception e) {
            // 处理异常情况
            responseData.setSuccess(false);
            responseData.setMsg("服务器错误: " + e.getMessage());
            responseData.setCode(500); // 服务器错误
        }

        return responseData;
    }

    /**
     * 订单提交
     * @param request
     * @param order
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData Create(HttpServletRequest request, @RequestBody Order order) {
        // 获取当前提交订单的用户信息及购物内容
        Cart cart = (Cart) this.cartService.getCart(request);
        UserInfo userInfo = (UserInfo) this.cartService.getUser(request);

        // 检查购物车是否为空
        if (cart == null || cart.getCartItemVoList().isEmpty()) {
            return ResponseData.fail(403,"购物车空空如也，请先选购产品");
        }

        // 设置订单相关信息
        order.setMoney(cart.getTotalMoney());
        order.setPayState(0); // 未支付
        order.setOrderState(0); // 未发货
        order.setUserInfo(userInfo);
        order.setOrderTime(new Date());

        // 创建订单项列表
        order.setOrderItemList(new ArrayList<OrderItem>());
        for (CartItemVo item : cart.getCartItemVoList()) {
            OrderItem oItem = new OrderItem();
            oItem.setOrder(order);
            oItem.setCandys(item.getCandys());
            oItem.setBuyNum(item.getBuyNum());
            oItem.setBuyPrice(item.getBuyPrice());
            order.getOrderItemList().add(oItem); // 根据购物车中的产品信息，创建订单的明细列表
        }

        // 调用服务层创建订单，并进行库存检查
        try {
            boolean result = this.orderService.createOrder(order); // 调用服务层提交订单

            if (result) {
                // 订单创建成功，清空购物车
                this.cartService.clear(request);
                return ResponseData.ok("订单提交成功",200);
            } else {
                // 订单创建失败
                return ResponseData.fail(401,"提交订单失败，请稍后再试");
            }
        } catch (InsufficientStockException ex) {
            // 捕获库存不足的异常并返回相关信息
            return ResponseData.fail(402,ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            // Log exception
            throw new RuntimeException("Order creation failed", e);
        }
    }

    /**
     * 更新订单
     * @param
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData<Order> update(@RequestBody Order order) {
        ResponseData<Order> responseData = new ResponseData<>();

            int result = orderService.update(order);

            if (result > 0) {
                responseData.setData(order);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("修改成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("修改失败");
            }
        return responseData;
        }

    /**
     * 查找订单
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/select/{id}")
    public ResponseData<PageInfo<Order>> getByid(@PathVariable("id") Integer id,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
    {
        ResponseData<PageInfo<Order>> responseData = new ResponseData<>();

        try {
            PageInfo<Order> pageInfo = this.orderService.findByUserId(pageNum, pageSize,id);

            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
                responseData.setData(pageInfo);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("商品未找到");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
//            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseData<Void> deleteCandy(@PathVariable("id") String id) {
        ResponseData<Void> responseData = new ResponseData<>();

        try {
            int result = orderService.delete(id);

            if (result > 0) {
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("删除成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("删除失败");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
//            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }

//    /**
//     * 查找个人订单
//     * @param id
//     * @param pageNum
//     * @param pageSize
//     * @return
//     */
//    @GetMapping("/select/{id}")
//    public ResponseData<PageInfo<OrderItem>> getItemByid(
//            HttpServletRequest request,
//            @PathVariable("id") String id,
//            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
//    {
////        //获取当前提交订单的用户信息及购物内容
////        Cart cart = (Cart) this.cartService.getCart(request);
////        UserInfo userInfo = (UserInfo) this.cartService.getUser(request);
////        if (cart == null) {
////            return ResponseData.fail("购物车空空如也，请先选购产品");
////        }
//        ResponseData<PageInfo<OrderItem>> responseData = new ResponseData<>();
//
//        try {
//            PageInfo<OrderItem> pageInfo = this.orderService.findByOrderId(pageNum, pageSize,id);
//            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
//                responseData.setData(pageInfo);
//                responseData.setSuccess(true);
//                responseData.setCode(200);
//                responseData.setMsg("查询成功");
//            } else {
//                responseData.setSuccess(false);
//                responseData.setCode(400);
//                responseData.setMsg("商品未找到");
//            }
//        } catch (Exception e) {
//            responseData.setSuccess(false);
////            responseData.setCode(500);
//            responseData.setMsg("服务器错误: " + e.getMessage());
//            // 可以在这里添加日志记录
//        }
//
//        return responseData;
//    }

}