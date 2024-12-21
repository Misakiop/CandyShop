package cn.lmu.candy.service;

import cn.lmu.candy.domain.UserInfo;

public interface UserAuthService {
    UserInfo register(UserInfo userToAdd);

    UserInfo login(String username, String  password);

    String refresh(String oldToken);

    UserInfo findByUsername(String username);
}
