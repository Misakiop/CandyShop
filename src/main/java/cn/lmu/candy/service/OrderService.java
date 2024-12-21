package cn.lmu.candy.service;

import cn.lmu.candy.domain.Order;
import com.github.pagehelper.PageInfo;

public interface OrderService {
    public boolean createOrder(Order order);

    public PageInfo<Order> getAll(Integer pageNum, Integer pageSize);

    public PageInfo<Order> findByUserId(Integer pageNum, Integer pageSize,Integer id);

    public int update(Order order);

    public int delete(String id);

}
