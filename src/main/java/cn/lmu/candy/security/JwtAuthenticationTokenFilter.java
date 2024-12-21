package cn.lmu.candy.security;

import cn.lmu.candy.domain.Role;
import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.service.UserAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//jwtAuthenticationTokenFilter过滤器，实现对前端请求拦截与Token令牌检查
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    //用于对用户请求的Token，如果请求的Header头部包含有有效的Token令牌，则放行，否则则要求进行登陆验证
    @Value("${jwt.header}") //读取配置文件中的jwt.header项的值并赋给tokenHeader变量
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private MyPasswordEncoder myPasswordEncoder;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);//读取请求中的Header头部名称为"Authorization”的值，即Token令牌
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            if (authToken.length()>0 && jwtTokenUtil.validateToken(authToken)){
                String userName = jwtTokenUtil.getUsernameFromToken(authToken);
                int userId = jwtTokenUtil.getIdFromToken(authToken);  // 提取用户ID（int 类型）
//                logger.info("checking authentication " + userName);
//                logger.info("User ID: " + userId);
                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //如果令牌有效，且未设置当前用户认证凭证，则为用户构造认证凭证（发一个访问通行证）
                    UserInfo userInfo =  this.userAuthService.findByUsername(userName);
                    org.springframework.security.core.userdetails.User user = null;
                    if (userInfo != null) {
                        user = new  org.springframework.security.core.userdetails.User(userInfo.getUsername(), myPasswordEncoder.encode(userInfo.getPassword()),
                                getAuthority(userInfo.getRoleList()));
                    }
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list =  new ArrayList<>();
        for (Role role : roles) {
            list.add(new  SimpleGrantedAuthority("ROLE_" +  role.getRoleName().toUpperCase()));
        }
        return list;
    }

}
