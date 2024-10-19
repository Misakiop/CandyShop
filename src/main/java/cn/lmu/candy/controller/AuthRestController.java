package cn.lmu.candy.controller;

import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.domain.Role;
import cn.lmu.candy.domain.Token;
import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.service.UserAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthRestController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private UserAuthService authService;


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = { RequestMethod.POST,RequestMethod.GET})
    public ResponseData loginForToken(@RequestBody Map<String,Object> params)
            throws AuthenticationException {
        ResponseData<UserInfo> responseData = new ResponseData<UserInfo>();
        String username=params.get("username").toString();
        String password=params.get("password").toString();
        UserInfo userInfo = authService.login(username, password);
        if(userInfo!=null && userInfo.getToken().length()>0)
        {responseData.setData(userInfo);
            responseData.setMsg("登录成功");
            responseData.setSuccess(true);
            responseData.setCode(200);
        }else{
            responseData.setMsg("用户名或密码错误");
            responseData.setSuccess(false);
            responseData.setCode(404);
        }
        return responseData;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request)
            throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new Token(refreshedToken));
        }
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData register(@RequestBody Map<String,Object> params) throws AuthenticationException {
        String username=params.get("username").toString();
        String password=params.get("password").toString();
        String code=params.get("code").toString();
        UserInfo user=new UserInfo();
        user.setUserName(username);
        user.setPassword(password);
        Role role=new Role();
        role.setId(2);
        role.setRoleName("USER");
        role.setRoleDesc("普通用户");
        List<Role> roles=new ArrayList<Role>();
        roles.add(role);
        user.setRoleList(roles);//默认普通用户角色
        ResponseData<UserInfo> responseData = new ResponseData<UserInfo>();
        UserInfo addedUser=authService.register(user);
        if (addedUser != null) {
            responseData.setSuccess(true);
            responseData.setMsg("注册用户成功！");
            responseData.setData(addedUser);
        } else {
            responseData.setSuccess(false);
            responseData.setMsg("注册用户失败！");
        }
        return responseData;
    }
}
