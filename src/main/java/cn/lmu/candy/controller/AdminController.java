package cn.lmu.candy.controller;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.service.CandyService;
import cn.lmu.candy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品控制层
 */
@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    @Autowired
    private CandyService candyService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     *添加商品
     * @param candy
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseData<Candys> insertCandy(@RequestBody Candys candy) {
        ResponseData<Candys> responseData = new ResponseData<>();

        try {
            // 调用 service 层的 insert 方法
            int result = candyService.insert(candy);

            if (result > 0) {
                responseData.setData(candy); // 插入成功后返回插入的对象
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("插入成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("插入失败");
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
     * 修改用户名和密码
     * @param id
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/updatenamepass/{id}", method = RequestMethod.PUT)
    public ResponseData<UserInfo> updatenamepass(@PathVariable("id") Integer id, @RequestBody UserInfo userInfo) {
        ResponseData<UserInfo> responseData = new ResponseData();

        try {
            int result = userInfoService.updatenamepass(userInfo);

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


    //删除商品数据
    @RequestMapping(value = "/deleteshop/{id}", method = RequestMethod.DELETE)
    public ResponseData<Void> deleteCandy(@PathVariable("id") Integer id) {
        ResponseData<Void> responseData = new ResponseData<>();

        try {
            int result = candyService.delete(id);

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


    //删除用户数据
    @RequestMapping(value = "/deleteuser/{id}",method = RequestMethod.DELETE)
    public ResponseData<Void> deleteUserInfo(@PathVariable("id") Integer id){
        ResponseData<Void> responseData = new ResponseData();

        try{
            int result = userInfoService.deleteUser(id);

            if(result > 0){
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("删除成功");
            }else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("删除失败");

            }
        }catch (Exception e){
            responseData.setSuccess(false);
//            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }
}
