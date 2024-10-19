package cn.lmu.candy.security;

import cn.lmu.candy.domain.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

//定义未认证（未登录）时错误处理器
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseData responseData=new ResponseData();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("code", "401");
        objectNode.put("msg", "未登录或token无效");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(objectNode);
    }
}
