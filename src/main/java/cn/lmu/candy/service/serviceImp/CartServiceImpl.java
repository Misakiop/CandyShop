package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.*;
import cn.lmu.candy.security.JwtTokenUtil;
import cn.lmu.candy.service.CandyService;
import cn.lmu.candy.service.CartService;
import cn.lmu.candy.service.UserAuthService;
import cn.lmu.candy.utils.RedisUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            if (obj != null) {//如果已存在，曾更新修改购物车数量
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
    public Cart getCart(HttpServletRequest request) {
        try {
            UserInfo userInfo = this.getUser(request);
            // 创建⼀个CartItemDto的空对象 ，⽬的是⽤来接从redis取到的数据
            CartItemDto cartItemDto;
            // 从redis中根据⽤户Id获取到购物车⾥的数据
            Map<Object, Object> cartItems = redisUtils.hgetall(CART_PREKEY + String.valueOf(userInfo.getId()));
            // 创建⼀个数组将所有的Vo对象都接出来返回到前端
            ArrayList<CartItemVo> cartItemVos = new ArrayList<>();
            //遍历map中的值 每⼀个value都是⼀个CarItemDto对象
            for (Object value : cartItems.values()) {
                // 为了获取到cartItem中的get⽅法，我们必须要将它强转成为⼀个CartItemDto来接住数据
                cartItemDto = (CartItemDto) value;
                Candys candys = candyService.findcandysByid(cartItemDto.getPid());
                // 这⾥vo对象接住Dto中的所有数据并补充Product属性数据，然后返回给前端
                CartItemVo cartItemVo = new CartItemVo();
                cartItemVo.setCandys(candys);
                cartItemVo.setBuyNum(cartItemDto.getBuyNum());
                cartItemVo.setBuyPrice(cartItemDto.getBuyPrice());
                cartItemVos.add(cartItemVo);
            }
            Cart cart = new Cart();
            cart.setUserInfo(userInfo);
            cart.setCartItemVoList(cartItemVos);
            cart.setTotalMoney(cart.getTotalMoney());
            cart.setTotalNum(cart.getTotalNum());
            return cart;
        } catch (Exception ex) {
            return null;
        }
    }
}