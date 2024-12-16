package cn.lmu.candy.controller;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.domain.UserInfo;
import cn.lmu.candy.service.CandyService;
import cn.lmu.candy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员API接口
 */
@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    @Autowired
    private CandyService candyService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 添加商品
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
     * 修改商品数据
     * @param id
     * @param candy
     * @return
     */
    @RequestMapping(value = "/updateshop", method = RequestMethod.PUT)
    public ResponseData<Candys> updateCandy(@RequestParam("id") String id, @RequestBody Candys candy) {
        ResponseData<Candys> responseData = new ResponseData<>();

        try {
            // 设置ID，确保传入的candys对象包含正确的ID
            candy.setId(id);

            // 调用服务层更新数据
            int result = candyService.update(candy);

            if (result > 0) {
                // 你可以考虑通过ID重新查询最新的数据并返回
                responseData.setData(candy); // 更新成功后返回更新的对象
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("修改成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("修改失败");
            }
        } catch (Exception e) {
            // 异常处理，返回500错误
            responseData.setSuccess(false);
//            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }

    /**
     * 删除商品数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteshop/{id}", method = RequestMethod.DELETE)
    public ResponseData<Void> deleteCandy(@PathVariable("id") String id) {
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

//    用户部分--------------------------------------------------------------------------------

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

}
