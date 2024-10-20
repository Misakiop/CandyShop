package cn.lmu.candy.controller;

import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.domain.Role;
import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    //获取全部用户数据
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public List<UserInfo> getUserList() {
        List<UserInfo> userInfoListlist = this.userInfoService.findAll();
        return userInfoListlist;
    }

    //通过id获取数据
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public ResponseData<UserInfo> getUserInfoById(@PathVariable("id") Integer id) {
        ResponseData<UserInfo> responseData = new ResponseData();

        try {
            UserInfo userInfo = this.userInfoService.findById(id);
            if (userInfo != null) {
                responseData.setData(userInfo);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("用户id不存在");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }

    //通过name获取数据
    @RequestMapping(value = "/select/{username}", method = RequestMethod.GET)
    public ResponseData<UserInfo> getUserInfoByName(@PathVariable("username") String username) {
        ResponseData<UserInfo> responseData = new ResponseData();

        try {
            UserInfo userInfo = this.userInfoService.findByName(username);
            if (userInfo != null) {
                responseData.setData(userInfo);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("用户名不存在");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }

    //完善用户信息
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseData<UserInfo> updateUserInfo(@PathVariable("id") Integer id, @RequestBody UserInfo userInfo) {
        ResponseData<UserInfo> responseData = new ResponseData();

        try {
            int result = userInfoService.updateUser(userInfo);

            if (result > 0) {
                responseData.setData(userInfo); // 更新成功后返回更新的对象
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("修改成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("修改失败");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
//            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }

    //用户修改密码
    @RequestMapping(value = "/updatepassword/{id}", method = RequestMethod.PUT)
    public ResponseData<UserInfo> updateUserpass(@PathVariable("id") Integer id, @RequestBody UserInfo userInfo) {
        ResponseData<UserInfo> responseData = new ResponseData();

        try {
            int result = userInfoService.updatepassword(userInfo);

            if (result > 0) {
                responseData.setData(userInfo); // 更新成功后返回更新的对象
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("修改成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("修改失败");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
//            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }


    //删除数据
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseData<Void> deleteUserInfo(@PathVariable("id") Integer id) {
        ResponseData<Void> responseData = new ResponseData();

        try {
            int result = userInfoService.deleteUser(id);

            if (result > 0) {
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("删除成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("删除失败");

            }
        } catch (Exception e) {
            responseData.setSuccess(false);
//            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }


}
