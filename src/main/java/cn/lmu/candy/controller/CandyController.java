package cn.lmu.candy.controller;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.service.CandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candy")
public class CandyController {
    @Autowired
    private CandyService candyService;

    //获取全部数据
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Candys> getCandyList() {
        List<Candys> candyList = this.candyService.findAllcandys();
        return candyList;
    }

    //通过id获取数据
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public ResponseData<Candys> getById(@PathVariable("id") Integer id) {
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
                responseData.setCode(404);
                responseData.setMsg("文章未找到");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }

    //插入数据
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
                responseData.setCode(500);
                responseData.setMsg("插入失败");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }

    //修改数据
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseData<Candys> updateCandy(@PathVariable("id") Integer id, @RequestBody Candys candy) {
        ResponseData<Candys> responseData = new ResponseData<>();

        try {

            int result = candyService.update(candy);

            if (result > 0) {
                responseData.setData(candy); // 更新成功后返回更新的对象
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("更新成功");
            } else {
                responseData.setSuccess(false);
                responseData.setCode(404);
                responseData.setMsg("未找到要更新的记录");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }

    //删除数据
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
                responseData.setCode(404);
                responseData.setMsg("未找到要删除的糖果记录");
            }
        } catch (Exception e) {
            responseData.setSuccess(false);
            responseData.setCode(500);
            responseData.setMsg("服务器错误: " + e.getMessage());
            // 可以在这里添加日志记录
        }

        return responseData;
    }
}

