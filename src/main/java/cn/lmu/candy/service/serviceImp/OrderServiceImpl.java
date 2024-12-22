package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.InsufficientStockException;
import cn.lmu.candy.domain.Order;
import cn.lmu.candy.domain.OrderItem;
import cn.lmu.candy.mapper.CandyMapper;
import cn.lmu.candy.mapper.OrderItemMapper;
import cn.lmu.candy.mapper.OrderMapper;
import cn.lmu.candy.service.OrderService;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CandyMapper candyMapper;
    //创建订单
    @Override
    @Transactional
    @CachePut(cacheNames = "orderCache", key = "'orderCreate' ")
    public boolean createOrder(Order order) {
        String oid = NanoIdUtils.randomNanoId();
        order.setId(oid);

        // 遍历订单项进行库存检查
        for (OrderItem item : order.getOrderItemList()) {
            // 获取产品库存
            int num = this.candyMapper.findnum(item);

            // 检查库存是否足够
            if (item.getBuyNum() > num) {
                throw new InsufficientStockException("商品 " + item.getCandys().getName() + " 库存不足");
            }
        }

        if(this.orderMapper.add(order)>0){//向Order表添加订单
            //下一步向OrderItem表插入本次订单具体购买产品信息
            System.out.println("Inserting Order: " + order.getId());
            for(OrderItem item:order.getOrderItemList()){
                System.out.println("Inserting OrderItem: " + item);

                item.setOrder(order);
                //逐一向订单明细orderItem表插入订单明细
                int rows = this.orderItemMapper.add(item);
                System.out.println("Rows affected: " + rows);

                if (rows <= 0) {
                    throw new RuntimeException("Order item insertion failed.");
                }

                this.orderItemMapper.add(item);
                //更新该产品的库存或销量
                this.candyMapper.updateCnum(item);
            }
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "orderCache", key = "'orderByUserId_' + #id", unless = "#result == null")
    public PageInfo<Order> findByUserId(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> order = orderMapper.findByUserId(id);
        PageInfo<Order> pageInfo = new PageInfo<>(order);
        return pageInfo;
    }

    @Override
    @Cacheable(cacheNames = "orderCache", key = "'order_'+#pageNum+'_'+#pageSize")
    public PageInfo<Order> getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> order = orderMapper.findAll();
        PageInfo<Order> pageInfo = new PageInfo<>(order);
        return pageInfo;
    }

    @Override
    @CacheEvict(cacheNames = "orderCache", allEntries = true)
    public int update(Order order){
        return this.orderMapper.update(order);
    }

    @Override
    @CacheEvict(cacheNames = "orderCache", allEntries = true)
    public int delete(String id){
        return this.orderMapper.delete(id);
    }

    @Override
    @CacheEvict(cacheNames = "orderCache", allEntries = true)
    public void clearCandysCache() {
        // 清除所有缓存条目
    }





}
