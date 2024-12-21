package cn.lmu.candy.service;

import cn.lmu.candy.domain.Candys;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CandyService {

    public PageInfo<Candys> findByWhere(Integer pageNum, Integer pageSize,Candys candys);
    public PageInfo<Candys> findByWhereUser(Integer pageNum, Integer pageSize,Candys candys);

    public PageInfo<Candys> findcandysbyname(Integer pageNum, Integer pageSize,String name);
    public PageInfo<Candys> Userfindcandysbyname(Integer pageNum, Integer pageSize,String name);

    public Candys findcandysByid(String id);

    public int insert(Candys candys);

    public int update(Candys candys);

    public int delete(String id);

    public void clearCandysCache();


    //    public PageInfo<Candys> findAllcandys(Integer pageNum, Integer pageSize);
    //    public PageInfo<Candys> UserfindAllcandys(Integer pageNum, Integer pageSize);



}
