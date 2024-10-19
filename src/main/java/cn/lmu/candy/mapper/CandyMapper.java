package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.Candys;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CandyMapper {
    @Select("SELECT * FROM db_candy.candys")
    List<Candys> findAllcandys();

    @Select("SELECT * FROM db_candy.candys WHERE id = #{id}")
    public int findcandysByid(int id);
}
