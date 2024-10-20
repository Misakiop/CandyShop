package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.Role;
import cn.lmu.candy.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    // 获取符合条件的用户列表
    @Select("select * from db_candy.user")
    public List<UserInfo> findAll();

    @Select("select * from user where username=#{username}")
    @Results(id = "UserInfoMap",value = {
            @Result(property = "roleList",column = "id",
                    many = @Many(select = "cn.lmu.candy.mapper.UserInfoMapper.findRolesByUserId"
                            ,fetchType = FetchType.EAGER))})
    UserInfo findByName(String username);

    @Select("select * from user where id=#{id}")
    @ResultMap("UserInfoMap")
    UserInfo findById(Integer id);

    //注册用户
    @Insert("insert into `user`(username,password) " +
            "values(#{username},#{password})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int add(UserInfo user);

    //保存用户信息
//    @Insert("insert into db_candy.user(username, password, gender, email, telephone, introduce, state, registTime,lastPasswordResetDate)" +
//            "values (#{username},#{password},#{gender},#{email},#{telephone},#{introduce},#{state},#{registTime},#{lastPasswordResetDate})")
    @Update("update db_candy.user set username=#{username},gender=#{gender},email=#{email},telephone=#{telephone},introduce=#{introduce},registTime=#{registTime},lastPasswordResetDate=#{lastPasswordResetDate} where id=#{id}")
    public int updateUser(UserInfo user);

    //修改账号密码(admin)
    @Update("update db_candy.user set username=#{username},password=#{password} where id=#{id}")
    public int updatenamepass(UserInfo user);

    //修改密码(user)
    @Update("update db_candy.user set password=#{password} where id=#{id}")
    public int updatepassword(UserInfo user);

    //保存用户角色
    @Insert("<script>" +
            "INSERT INTO user_role" +
            "        (user_id,role_id)" +
            "        VALUES" +
            "        <foreach collection ='user.roleList' item='role' separator =','>" +
            "            (#{user.id}, #{role.id})" +
            "        </foreach >" +
            "</script>")
    public int addUserRole(@Param("user") UserInfo user);


    @Select("select * from role where id in(select  role_id from user_role where  user_id=#{userId})")
    @Results(id = "RoleMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc")})
    public List<Role> findRolesByUserId(int userId);

    @Delete("delete from db_candy.user where id=#{id}")
    public int deleteUser(Integer id);
}
