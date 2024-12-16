package cn.lmu.candy.service;

import cn.lmu.candy.domain.Candys;
import com.github.pagehelper.PageInfo;

public interface CandyService {

    public PageInfo<Candys> findAllcandys(Integer pageNum, Integer pageSize);

    public PageInfo<Candys> findcandysbyname(Integer pageNum, Integer pageSize,String name);

    public Candys findcandysByid(String id);

    public int insert(Candys candys);

    public int update(Candys candys);

    public int delete(String id);


}
