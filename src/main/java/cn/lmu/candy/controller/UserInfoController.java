package cn.lmu.candy.controller;

import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

/**
 * 用户API接口
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取全部用户数据
     * @return
     */
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ResponseData<PageInfo<UserInfo>> getUserList(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
     {
        ResponseData<PageInfo<UserInfo>> responseData = new ResponseData<>();

        try {
            PageInfo<UserInfo> pageInfo = this.userInfoService.findAlluser(pageNum, pageSize);
//            List<UserInfo> userInfoList = this.userInfoService.findAll();
            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
                responseData.setData(pageInfo);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                // 如果没有数据，也可以视为一种"成功"，只是没有数据而已
                responseData.setSuccess(true);
                responseData.setCode(204); // 204 No Content 也可以用来表示没有内容的情况
                responseData.setMsg("无用户数据");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 根据实际异常类型可以设置不同的错误码，这里简单设置为500
            responseData.setCode(500);
        }

        return responseData;
    }

    /**
     * 通过id获取数据
     * @param id
     * @return
     */
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

    /**
     * 通过name获取数据
     * @param username
     * @return
     */
    @RequestMapping(value = "/selectbyname/{username}", method = RequestMethod.GET)
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

    /**
     * 修改用户
     * @param id
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseData<UserInfo> updateUserInfo(@RequestParam("id") Integer id, @RequestBody UserInfo userInfo) {
        ResponseData<UserInfo> responseData = new ResponseData<>();

        try {
            // 确保 userInfo 对象包含 id
            userInfo.setId(id);
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
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
        }

        return responseData;
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
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

    /**
     * 清除所有缓存条目
     * @return ResponseData
     */
    @DeleteMapping("/clearCache")
    @CacheEvict(cacheNames = "userCache", allEntries = true)
    public ResponseData<String> clearuserCache() {
        ResponseData<String> responseData = new ResponseData<>();

        try {
            // 清除缓存的操作由 @CacheEvict 注解自动处理
            responseData.ok("刷新成功",200);
        } catch (Exception e) {
            responseData.fail(500,"服务异常");
        }

        return responseData;
    }
}
