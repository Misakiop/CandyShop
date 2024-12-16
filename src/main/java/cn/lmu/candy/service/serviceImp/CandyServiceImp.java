package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.mapper.CandyMapper;
import cn.lmu.candy.service.CandyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CandyServiceImp implements CandyService {
    @Autowired
    private CandyMapper candysMapper;

    @Override
    public PageInfo<Candys> findAllcandys(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        List<Candys> candys = candysMapper.findAllcandys();
        PageInfo<Candys> pageInfo = new PageInfo<>(candys);
        return pageInfo;
    }

    @Override
    public Candys findcandysByid(String id) {
        return this.candysMapper.findcandysByid(id);
    }

    @Override
    public PageInfo<Candys> findcandysbyname(Integer pageNum, Integer pageSize,String name) {
        PageHelper.startPage(pageNum,pageSize);
        List<Candys> candys = candysMapper.findcandysbyname(name);
        PageInfo<Candys> pageInfo = new PageInfo<>(candys);
        return pageInfo;
    }

    @Override
    public int insert(Candys candys){
        // 生成唯一的 UUID 作为商品 ID
        String id = UUID.randomUUID().toString();
        candys.setId(id);

        // 设置当前时间为 addtime
        candys.setAddtime(new Date());
        return this.candysMapper.insert(candys);
    }

    @Override
    public int update(Candys candys){
        return this.candysMapper.update(candys);
    }

    @Override
    public int delete(String id){
        return this.candysMapper.delete(id);
    }



}
