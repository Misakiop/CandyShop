package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.Role;
import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.mapper.UserInfoMapper;
import cn.lmu.candy.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImp implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Cacheable(cacheNames = "userCache", key = "'userList_' + #pageNum + '_' + #pageSize", unless = "#result == null")
    public PageInfo<UserInfo> findAlluser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfoList = userInfoMapper.findAll();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
        return pageInfo;
    }

    @Override
    @Cacheable(cacheNames = "userCache", key = "'userByName_' + #username", unless = "#result == null")
    public UserInfo findByName(String username){
        return userInfoMapper.findByName(username);
    }

    @Override
    @Cacheable(cacheNames = "userCache", key = "'userById_' + #id", unless = "#result == null")
    public UserInfo findById(Integer id){
        return userInfoMapper.findById(id);
    }

    @Override
    @CacheEvict(cacheNames = "userCache", allEntries = true) // 清除所有缓存
    public int add(UserInfo user){
        // 设置RegistTime为当前时间
        user.setRegistTime(new Date());
        return userInfoMapper.add(user);
    }

    @Override
    @CachePut(cacheNames = "userCache", key = "'userById_' + #user.id")
    public int updateUser(UserInfo user){
        // 判断 password 是否有新值
        if (user.getPassword() != null) {
            // 设置 lastPasswordResetDate 为当前时间
            user.setLastPasswordResetDate(new Date());
        }
        return userInfoMapper.updateUser(user);
    }

    @Override
    @CacheEvict(cacheNames = "userCache", allEntries = true) // 清除所有缓存
    public int addUserRole(@Param("user") UserInfo user){
        return userInfoMapper.addUserRole(user);
    }

    @Override
    @Cacheable(cacheNames = "userCache", key = "'userRoles_' + #userId", unless = "#result == null")
    public List<Role> findRolesByUserId(int userId){
        return userInfoMapper.findRolesByUserId(userId);
    }

    @Override
    @CacheEvict(cacheNames = "userCache", key = "'userById_' + #id") // 清除单个用户缓存
    public int deleteUser(Integer id){
        return userInfoMapper.deleteUser(id);
    }
}
