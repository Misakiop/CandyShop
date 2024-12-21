package cn.lmu.candy.service;

import cn.lmu.candy.domain.Role;
import cn.lmu.candy.domain.UserInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoService {
    public PageInfo<UserInfo> findAlluser(Integer pageNum, Integer pageSize);

    UserInfo findByName(String username);

    UserInfo findById(Integer id);

    public int add(UserInfo user);

    public int updateUser(UserInfo user);

    public int addUserRole(@Param("user") UserInfo user);

    public List<Role> findRolesByUserId(int userId);

    public int deleteUser(Integer id);

    public void clearuserCache();


}
