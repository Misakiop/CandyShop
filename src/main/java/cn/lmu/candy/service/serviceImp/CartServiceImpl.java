package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.*;
import cn.lmu.candy.security.JwtTokenUtil;
import cn.lmu.candy.service.CandyService;
import cn.lmu.candy.service.CartService;
import cn.lmu.candy.service.UserAuthService;
import cn.lmu.candy.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisUtils redisUtils;  //基于Redis实现购物车数据的存取，利用Redis工具类实现对Redis数据基于Hash存取
    @Autowired
    private CandyService candyService;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final String CART_PREKEY = "Cart-";
    private final String USER_PREKEY = "User-";

    // 从请求头获取JWT Token
    private String getTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // 去掉 "Bearer " 前缀
        }
        return null; // 如果没有找到JWT Token，返回null
    }
    // 根据请求获取用户信息
    public UserInfo getUser(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        if (token == null) {
            throw new IllegalStateException("JWT Token not found in request");
        }

        if (!jwtTokenUtil.validateToken(token)) {
            throw new IllegalStateException("Invalid JWT Token");
        }

        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserInfo userInfo = userAuthService.findByUsername(username);
        if (userInfo == null) {
            throw new IllegalStateException("User information not available for username: " + username);
        }

        return userInfo;
    }

    @Override
    public boolean add(CartItemDto cartItemDto, HttpServletRequest request) {
        //Redis获取用户信息：获取cookie里面的uuid-->读取该用户缓存于Redis中的购物车数据--》将该产品加入购物车--》将购物车更新缓存
        UserInfo userInfo = this.getUser(request);
        //先判定购物车中是否已存在该产品的购物记录
        Object obj = redisUtils.hget(CART_PREKEY + String.valueOf(userInfo.getId()), cartItemDto.getPid());
        try {
            if (obj != null) {//如果已存在，则更新修改购物车数量
                CartItemDto cartItemDtoOld = (CartItemDto) obj;
                cartItemDtoOld.setBuyNum(cartItemDtoOld.getBuyNum() + cartItemDto.getBuyNum());
                redisUtils.hset(CART_PREKEY + String.valueOf(userInfo.getId()), cartItemDtoOld.getPid(), cartItemDtoOld);
            } else {//如果购物车中不存在，则新增购买条目
                redisUtils.hset(CART_PREKEY + String.valueOf(userInfo.getId()), cartItemDto.getPid(), cartItemDto);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(CartItemDto cartItemDto, HttpServletRequest request) {
        try {
            UserInfo userInfo = this.getUser(request);
            this.redisUtils.hset(CART_PREKEY + String.valueOf(userInfo.getId()), cartItemDto.getPid(), cartItemDto);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean delete(String[] idArray, HttpServletRequest request) {
        try {
            UserInfo userInfo = this.getUser(request);
            this.redisUtils.hdel(CART_PREKEY + String.valueOf(userInfo.getId()),idArray);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean clear(HttpServletRequest request) {
        try {
            UserInfo userInfo = this.getUser(request);
            this.redisUtils.del(CART_PREKEY + String.valueOf(userInfo.getId()));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public PageInfo<CartItemVo> getPagedCart(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        try {
            UserInfo userInfo = this.getUser(request);
            // 从 Redis 中获取用户购物车所有数据
            Map<Object, Object> cartItems = redisUtils.hgetall(CART_PREKEY + String.valueOf(userInfo.getId()));
            List<CartItemVo> cartItemVoList = new ArrayList<>();

            // 将购物车条目转换为 CartItemVo
            for (Object value : cartItems.values()) {
                CartItemDto cartItemDto = (CartItemDto) value;
                Candys candys = candyService.findcandysByid(cartItemDto.getPid());

                CartItemVo cartItemVo = new CartItemVo();
                cartItemVo.setCandys(candys);
                cartItemVo.setBuyNum(cartItemDto.getBuyNum());
                cartItemVo.setBuyPrice(cartItemDto.getBuyPrice());
                cartItemVoList.add(cartItemVo);
            }
//------------------------------------------------------------------------------------------------//
//              方法1进行手动分页
//             谁家好人给购物车分页的？
//            // 手动进行分页
//            int start = (pageNum - 1) * pageSize;
//            int end = Math.min(start + pageSize, cartItemVoList.size());
//            List<CartItemVo> pageList = cartItemVoList.subList(start, end);
//
//            // 创建 PageInfo 并返回
//            PageInfo<CartItemVo> pageInfo = new PageInfo<>(pageList);
//            pageInfo.setTotal(cartItemVoList.size());  // 设置总数
//            return pageInfo;
//------------------------------------------------------------------------------------------------//

            //方法2不进行分页因为似乎获取购物车缓存的数据绕过了自动分页
            // 使用 PageHelper 分页，确保分页在获取的数据上生效
            PageHelper.startPage(pageNum, pageSize);
            // 返回分页结果
            return new PageInfo<>(cartItemVoList);
//------------------------------------------------------------------------------------------------//
        } catch (Exception ex) {
            ex.printStackTrace();
            return new PageInfo<>(new ArrayList<>());
        }
    }

    //普通获取购物车（前端无用到）
    @Override
    public Cart getCart(HttpServletRequest request) {
        try {
            UserInfo userInfo = this.getUser(request);
            CartItemDto cartItemDto;
            // 从Redis中获取购物车数据
            Map<Object, Object> cartItems = redisUtils.hgetall(CART_PREKEY + String.valueOf(userInfo.getId()));

            // 创建一个列表用于保存购物车项
            ArrayList<CartItemVo> cartItemVos = new ArrayList<>();

            // 遍历Redis获取到的购物车项
            for (Object value : cartItems.values()) {
                cartItemDto = (CartItemDto) value;
                Candys candys = candyService.findcandysByid(cartItemDto.getPid());

                // 将每个购物车项转换成CartItemVo并添加到列表中
                CartItemVo cartItemVo = new CartItemVo();
                cartItemVo.setCandys(candys);
                cartItemVo.setBuyNum(cartItemDto.getBuyNum());
                cartItemVo.setBuyPrice(cartItemDto.getBuyPrice());
                cartItemVos.add(cartItemVo);
            }

            // 如果购物车没有任何商品，返回null
            if (cartItemVos.isEmpty()) {
                return null;
            }

            // 创建一个Cart对象并设置相关属性
            Cart cart = new Cart();
            cart.setUserInfo(userInfo);
            cart.setCartItemVoList(cartItemVos);

            // 计算购物车的总金额和总数量
            cart.setTotalMoney(cart.getTotalMoney());
            cart.setTotalNum(cart.getTotalNum());

            return cart;
        } catch (Exception ex) {
            // 捕获异常并返回null
            return null;
        }
    }

}
