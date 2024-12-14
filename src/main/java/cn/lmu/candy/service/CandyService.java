package cn.lmu.candy.service;

import cn.lmu.candy.domain.Candys;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CandyService {
//    List<Candys> findAllcandys();

    public PageInfo<Candys> findAllcandys(Integer pageNum, Integer pageSize);

    public PageInfo<Candys> findcandysbyname(Integer pageNum, Integer pageSize,String name);

    public Candys findcandysByid(Integer id);

    public int insert(Candys candys);

    public int update(Candys candys);

    public int delete(Integer id);

    public int updatestate(Candys candys);

//    public List<Candys> findcandysbyname(String name);

}
