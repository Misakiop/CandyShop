package cn.lmu.candy.controller;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.domain.Category;
import cn.lmu.candy.domain.ResponseData;
import cn.lmu.candy.service.CandyService;
import cn.lmu.candy.service.CategoryService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品API接口
 */
@RestController
@RequestMapping("/api/candy")
public class CandyController {
    @Autowired
    private CandyService candyService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页获取全部数据
     * @return
     */
    @RequestMapping(value = "/listwhere", method = RequestMethod.GET)
    public ResponseData<Map<String, Object>> getCandyByWhere(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "12") Integer pageSize,
            Candys candys, HttpServletRequest request) {

//        System.out.println("Candys object: " + candys);
//        System.out.println("Candys name: " + candys.getName());
//        System.out.println("Candys category: " + candys.getCategory());

        // 调用 service 层获取分页数据
        PageInfo<Candys> pageInfo = this.candyService.findByWhere(pageNum, pageSize, candys);
        List<Category> categoryList = categoryService.getCategoryList();

        // 构建响应数据
        Map<String, Object> responseDataMap = new HashMap<>();
        responseDataMap.put("pageInfo", pageInfo);
        responseDataMap.put("categoryList", categoryList);

        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
        if (pageInfo != null && !pageInfo.getList().isEmpty()) {
            responseData.setData(responseDataMap);
            responseData.setSuccess(true);
            responseData.setCode(200);
            responseData.setMsg("查询成功");
        } else {
            responseData.setSuccess(true);
            responseData.setCode(400); // No Content
            responseData.setMsg("无商品数据");
        }

        return responseData;
    }

    /**
     * 用户分页获取全部数据
     * @return
     */
    @RequestMapping(value = "/listwhereuser", method = RequestMethod.GET)
    public ResponseData<Map<String, Object>> getCandyByWhereUser(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "12") Integer pageSize,
            Candys candys, HttpServletRequest request) {
        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
        try {
            //调用 service 层获取分页数据
            PageInfo<Candys> pageInfo = this.candyService.findByWhereUser(pageNum, pageSize,candys);
            // 获取商品类别列表
            List<Category> categoryList = this.categoryService.getCategoryList();
            // 将分页数据和类别数据一起放入响应数据
            Map<String, Object> responseDataMap = new HashMap<>();
            responseDataMap.put("pageInfo", pageInfo);
            responseDataMap.put("categoryList", categoryList);

            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
                responseData.setData(responseDataMap);
                responseData.setSuccess(true);
                responseData.setCode(200);
                responseData.setMsg("查询成功");
            } else {
                responseData.setSuccess(true);
                responseData.setCode(400); // No Content
                responseData.setMsg("无商品数据");
            }
        } catch (Exception e) {
            // 打印异常信息到日志
            e.printStackTrace(); // 输出堆栈信息
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
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
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
     * 用户分页名称查找商品
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/userselectbyname/{name}", method = RequestMethod.GET)
    public ResponseData<PageInfo<Candys>> UsergetByName(
            @PathVariable("name") String name,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize)
    {
        ResponseData<PageInfo<Candys>> responseData = new ResponseData<>();

        try {
            PageInfo<Candys> pageInfo = this.candyService.Userfindcandysbyname(pageNum, pageSize,name);
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

    /**
     * 清除所有缓存条目
     * @return ResponseData
     */
    @DeleteMapping("/clearCache")
    @CacheEvict(cacheNames = "candysCache", allEntries = true)
    public ResponseData<String> clearCandysCache() {
        ResponseData<String> responseData = new ResponseData<>();

        try {
            // 清除缓存的操作由 @CacheEvict 注解自动处理
            responseData.ok("刷新成功",200);
        } catch (Exception e) {
            responseData.fail(500,"服务异常");
        }

        return responseData;
    }}

//    /**
//     * 分页获取全部数据
//     * @return
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseData<Map<String, Object>> getCandyList(
//            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "12") Integer pageSize) {
//
//        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
//
//        try {
//            // 调用 service 层获取分页数据
//            PageInfo<Candys> pageInfo = this.candyService.findAllcandys(pageNum, pageSize);
//
//            // 获取商品类别列表
//            List<Category> categoryList = this.categoryService.getCategoryList();
//
//            // 将分页数据和类别数据一起放入响应数据
//            Map<String, Object> responseDataMap = new HashMap<>();
//            responseDataMap.put("pageInfo", pageInfo);
//            responseDataMap.put("categoryList", categoryList);
//
//            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
//                responseData.setData(responseDataMap);
//                responseData.setSuccess(true);
//                responseData.setCode(200);
//                responseData.setMsg("查询成功");
//            } else {
//                responseData.setSuccess(true);
//                responseData.setCode(400); // No Content
//                responseData.setMsg("无商品数据");
//            }
//        } catch (Exception e) {
//            // 打印异常信息到日志
//            e.printStackTrace(); // 输出堆栈信息
//            responseData.setSuccess(false);
//            responseData.setMsg("服务器错误: " + e.getMessage());
//            responseData.setCode(500); // 服务器错误
//        }
//
//        return responseData;
//    }

//
//    /**
//     * 用户分页获取全部数据
//     * @return
//     */
//    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
//    public ResponseData<PageInfo<Candys>> getUserCandyList(
//            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "12") Integer pageSize)
//    {
//        ResponseData<PageInfo<Candys>> responseData = new ResponseData<>();
//
//        try {
//            // 调用 service 层获取分页数据
//            PageInfo<Candys> pageInfo = this.candyService.UserfindAllcandys(pageNum, pageSize);
//
//            if (pageInfo != null && !pageInfo.getList().isEmpty()) {
//                responseData.setData(pageInfo);
//                responseData.setSuccess(true);
//                responseData.setCode(200);
//                responseData.setMsg("查询成功");
//            } else {
//                responseData.setSuccess(true);
//                responseData.setCode(400); // No Content
//                responseData.setMsg("无商品数据");
//            }
//        } catch (Exception e) {
//            responseData.setSuccess(false);
//            responseData.setMsg("服务器错误: " + e.getMessage());
//            responseData.setCode(500); // 服务器错误
//        }
//
//        return responseData;
//    }
    //


