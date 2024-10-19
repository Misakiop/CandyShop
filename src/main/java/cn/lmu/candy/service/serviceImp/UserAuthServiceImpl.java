package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.mapper.UserInfoMapper;
import cn.lmu.candy.security.JwtTokenUtil;
import cn.lmu.candy.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Value("Bearer")
    private String tokenHead;
    @Override
    public UserInfo register(UserInfo userToAdd) {
        //如果注册时对密码进行加密保存，则后续登录验证时要使用同样的加密规则
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        //对密码进行加密，如不处理则表示明文保存密码
        //userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        int result=this.userInfoMapper.add(userToAdd);
        if(result>0){
            //保存用户角色，此处仅创建普通用户角色
            result=this.userInfoMapper.addUserRole(userToAdd);
        }
        if (result>0) {
            return userToAdd;
        } else {
            return null;
        }
    }

    @Override
    public UserInfo login(String username, String password) {
        final UserInfo userInfo = this.userInfoMapper.findByName(username);

        //如果数据库中用户密码做了加密处理，则需要对登录密码进行同样加密处理后比较
        //  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //  调用encoder.matches(password, userInfo.getPassword())进行密码比对
        if (userInfo != null&& password.equals(userInfo.getPassword())) {
            final String token = jwtTokenUtil.generateToken(userInfo);//
            userInfo.setToken(token);
            return userInfo;
        } else {
            return null;
        }
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserInfo user = this.userInfoMapper.findByName(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public UserInfo findByUsername(String username) {
        return this.userInfoMapper.findByName(username);
    }

}
