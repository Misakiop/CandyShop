package cn.lmu.candy.controller;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.domain.JsonData;
import cn.lmu.candy.service.CandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/candy")
public class CandyController {
    @Autowired
    private CandyService candyService;

//    @RequestMapping(value = "/list",method = RequestMethod.GET)
//    public List<Candys> getCandyList(){
//        List<Candys> candyList = this.candyService.findAllcandys();
//        return candyList;
//    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonData getCandyList(){
        List<Candys> candysList = this.candyService.findAllcandys();
        if(candysList != null){
            return new JsonData(1,candysList,"查询成功");
        }else {
            return new JsonData(0,"查询失败");
        }
    }
}
