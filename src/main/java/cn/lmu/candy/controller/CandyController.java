package cn.lmu.candy.controller;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.service.CandyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品API接口
 */
@RestController
@RequestMapping("/api/candy")
public class CandyController {
    @Autowired
    private CandyService candyService;

    /**
     * 分页获取全部数据
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData<PageInfo<Candys>> getCandyList(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "12") Integer pageSize)
    {
        ResponseData<PageInfo<Candys>> responseData = new ResponseData<>();

        try {
            // 调用 service 层获取分页数据
            PageInfo<Candys> pageInfo = this.candyService.findAllcandys(pageNum, pageSize);

            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
                responseData.setData(pageInfo);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                responseData.setSuccess(true);
                responseData.setCode(400); // No Content
                responseData.setMsg("无商品数据");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setMsg("服务器错误: " + e.getMessage());
            responseData.setCode(500); // 服务器错误
        }

        return responseData;
    }

    /**
     * 分页名称查找商品
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/selectbyname/{name}", method = RequestMethod.GET)
    public ResponseData<PageInfo<Candys>> getByName(
            @PathVariable("name") String name,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
    {
        ResponseData<PageInfo<Candys>> responseData = new ResponseData<>();

        try {
            PageInfo<Candys> pageInfo = this.candyService.findcandysbyname(pageNum, pageSize,name);
            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
                responseData.setData(pageInfo);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("商品未找到");
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
     * 通过id获取数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectbyid/{id}", method = RequestMethod.GET)
    public ResponseData<Candys> getById(@PathVariable("id") String id) {
        ResponseData<Candys> responseData = new ResponseData<>();

        try {
            Candys candys = this.candyService.findcandysByid(id);
            if (candys != null) {
                responseData.setData(candys);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(400);
                responseData.setMsg("商品未找到");
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

