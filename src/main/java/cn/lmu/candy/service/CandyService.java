package cn.lmu.candy.service;

import cn.lmu.candy.domain.Candys;

import java.util.List;

public interface CandyService {
    List<Candys> findAllcandys();

    public int findcandysByid(int id);

}
