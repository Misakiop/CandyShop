package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.Role;
import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.mapper.UserInfoMapper;
import cn.lmu.candy.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImp implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public PageInfo<UserInfo> findAlluser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfoList = userInfoMapper.findAll();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
        return pageInfo;
    }

    @Override
    public UserInfo findByName(String username){
        return userInfoMapper.findByName(username);
    }

    @Override
    public UserInfo findById(Integer id){
        return userInfoMapper.findById(id);
    }

    @Override
    public int add(UserInfo user){
        // 设置RegistTime为当前时间
        user.setRegistTime(new Date());
        return userInfoMapper.add(user);
    }

    @Override
    public int updateUser(UserInfo user){
        // 判断 password 是否有新值
        if (user.getPassword() != null) {
            // 设置 lastPasswordResetDate 为当前时间
            user.setLastPasswordResetDate(new Date());
        }
        return userInfoMapper.updateUser(user);
    }

    @Override
    public int addUserRole(@Param("user") UserInfo user){
        return userInfoMapper.addUserRole(user);
    }

    @Override
    public List<Role> findRolesByUserId(int userId){
        return userInfoMapper.findRolesByUserId(userId);
    }

    @Override
    public int deleteUser(Integer id){
        return userInfoMapper.deleteUser(id);
    }
}
