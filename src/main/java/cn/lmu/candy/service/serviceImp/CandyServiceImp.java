package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.mapper.CandyMapper;
import cn.lmu.candy.service.CandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandyServiceImp implements CandyService {
    @Autowired
    private CandyMapper candysMapper;

    @Override
    public List<Candys> findAllcandys() {
        return this.candysMapper.findAllcandys();
    }

    @Override
    public int findcandysByid(int id) {
        return this.candysMapper.findcandysByid(id);
    }
}
