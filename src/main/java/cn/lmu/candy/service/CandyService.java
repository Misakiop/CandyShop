package cn.lmu.candy.service;

import cn.lmu.candy.domain.Candys;

import java.util.List;

public interface CandyService {
    List<Candys> findAllcandys();

    public Candys findcandysByid(Integer id);

    public int insert(Candys candys);

    public int update(Candys candys);

    public int delete(Integer id);
}
