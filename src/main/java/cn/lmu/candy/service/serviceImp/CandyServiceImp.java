package cn.lmu.candy.service.serviceImp;

import cn.lmu.candy.domain.Candys;
import cn.lmu.candy.mapper.CandyMapper;
import cn.lmu.candy.service.CandyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CandyServiceImp implements CandyService {
    @Autowired
    private CandyMapper candysMapper;
    @Autowired
    private CacheManager cacheManager;

    @Override
    @Cacheable(cacheNames = "candysCache", key = "'candys_'+#pageNum+'_'+#pageSize")
    public PageInfo<Candys> findByWhere(Integer pageNum, Integer pageSize,Candys candys){
        PageHelper.startPage(pageNum, pageSize);
        List<Candys> candysList = candysMapper.findByWhere(candys);
        for (Candys candy : candysList){
            if (candy.getState() != 0 &&candy.getNum() <= 0){
                //如果库存小于等于0，更新商品状态为缺货
                candy.setState(2);
                candysMapper.update(candy);
                // 手动更新缓存中的数据
                clearCandysCache();
            }
            if (candy.getState() != 0 &&candy.getNum() > 0){
                //如果库存小于等于0，更新商品状态为缺货
                candy.setState(1);
                candysMapper.update(candy);
                // 手动更新缓存中的数据
                clearCandysCache();
            }
        }
        PageInfo<Candys> pageInfo = new PageInfo<Candys>(candysList);
        return pageInfo;
    }
    @Override
    @Cacheable(cacheNames = "candysCache", key = "'candysUser_'+#pageNum+'_'+#pageSize")
    public PageInfo<Candys> findByWhereUser(Integer pageNum, Integer pageSize,Candys candys){
        PageHelper.startPage(pageNum, pageSize);
        List<Candys> candysList = candysMapper.findByWhereUser(candys);
        for (Candys candy : candysList) {
            if (candy.getState() != 0 && candy.getNum() <= 0) {
                // 如果库存小于等于0，更新商品状态为缺货
                candy.setState(2);
                candysMapper.update(candy);
                // 手动更新缓存中的数据
                clearCandysCache();
            }
            if (candy.getState() != 0 && candy.getNum() > 0) {
                // 如果库存大于0，更新商品状态为在售
                candy.setState(1);
                candysMapper.update(candy);
                // 手动更新缓存中的数据
                clearCandysCache();
            }
        }
        PageInfo<Candys> pageInfo = new PageInfo<Candys>(candysList);
        return pageInfo;
    }

    @Override
    @CacheEvict(cacheNames = "candysCache", allEntries = true)
    public void clearCandysCache() {
        // 清除所有缓存条目
    }

    @Override
    @Cacheable(
            cacheNames = "candysCache",
            key = "'candys:' + #id",
            condition = "#id != null && #id.length() > 0",
            unless = "#result == null"
    )
    public Candys findcandysByid(String id) {
        return this.candysMapper.findcandysByid(id);
    }

    @Override
    @Cacheable(cacheNames = "candysCache", key = "'candysByName:' + #name + '_' + #pageNum + '_' + #pageSize",
            condition = "#name != null && !#name.isEmpty()")
    public PageInfo<Candys> findcandysbyname(Integer pageNum, Integer pageSize,String name) {
        PageHelper.startPage(pageNum,pageSize);
        List<Candys> candys = candysMapper.findcandysbyname(name);

        for (Candys candy : candys){
            if (candy.getState() != 0 &&candy.getNum() <= 0){
                //如果库存小于等于0，更新商品状态为缺货
                candy.setState(2);
                candysMapper.update(candy);

                // 手动更新缓存中的数据
                clearCandysCache();
            }
            if (candy.getState() != 0 &&candy.getNum() > 0){
                //如果库存小于等于0，更新商品状态为缺货
                candy.setState(1);
                candysMapper.update(candy);

                // 手动更新缓存中的数据
                clearCandysCache();
            }
        }

        PageInfo<Candys> pageInfo = new PageInfo<>(candys);
        return pageInfo;
    }
    //用户访问
    @Override
    @Cacheable(cacheNames = "candysCache", key = "'candysByNameUser:' + #name + '_' + #pageNum + '_' + #pageSize",
            condition = "#name != null && !#name.isEmpty()")
    public PageInfo<Candys> Userfindcandysbyname(Integer pageNum, Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<Candys> candys = candysMapper.Userfindcandysbyname(name);

        for (Candys candy : candys){
            if (candy.getState() != 0 &&candy.getNum() <= 0){
                //如果库存小于等于0，更新商品状态为缺货
                candy.setState(2);
                candysMapper.update(candy);

                // 手动更新缓存中的数据
                clearCandysCache();
            }
            if (candy.getState() != 0 &&candy.getNum() > 0){
                //如果库存小于等于0，更新商品状态为缺货
                candy.setState(1);
                candysMapper.update(candy);

                // 手动更新缓存中的数据
                clearCandysCache();
            }
        }

        PageInfo<Candys> pageInfo = new PageInfo<>(candys);
        return pageInfo;
    }

    @Override
    @CachePut(cacheNames = "candysCache", key = "'candysInsert' ")
    public int insert(Candys candys){
        // 生成唯一的 UUID 作为商品 ID
        String id = UUID.randomUUID().toString();
        candys.setId(id);

        // 设置当前时间为 addtime
        candys.setAddtime(new Date());
        return this.candysMapper.insert(candys);
    }

    @Override
    @CacheEvict(cacheNames = "candysCache", allEntries = true)
    public int update(Candys candys){
        return this.candysMapper.update(candys);
    }

    @Override
    @CacheEvict(cacheNames = "candysCache", allEntries = true)
    public int delete(String id){
        return this.candysMapper.delete(id);
    }

//    public PageInfo<Candys> findAllcandys(Integer pageNum, Integer pageSize) {
//        //开启分页
//        PageHelper.startPage(pageNum,pageSize);
//        List<Candys> candys = candysMapper.findAllcandys();
//
//        for (Candys candy : candys){
//            if (candy.getState() != 0 &&candy.getNum() <= 0){
//                //如果库存小于等于0，更新商品状态为缺货
//                candy.setState(2);
//                candysMapper.update(candy);
//
//                // 手动更新缓存中的数据
//                clearCandysCache();
//            }
//            if (candy.getState() != 0 &&candy.getNum() > 0){
//                //如果库存小于等于0，更新商品状态为缺货
//                candy.setState(1);
//                candysMapper.update(candy);
//
//                // 手动更新缓存中的数据
//                clearCandysCache();
//            }
//        }
//
//        PageInfo<Candys> pageInfo = new PageInfo<>(candys);
//        return pageInfo;
//    }
    //用户访问
//    @Override
//    @Cacheable(cacheNames = "candysCache", key = "'candysUser_'+#pageNum+'_'+#pageSize")
//    public PageInfo<Candys> UserfindAllcandys(Integer pageNum, Integer pageSize){
//        //开启分页
//        PageHelper.startPage(pageNum,pageSize);
//        List<Candys> candys = candysMapper.UserfindAllcandys();
//
//        for (Candys candy : candys){
//            if (candy.getState() != 0 &&candy.getNum() <= 0){
//                //如果库存小于等于0，更新商品状态为缺货
//                candy.setState(2);
//                candysMapper.update(candy);
//
//                // 手动更新缓存中的数据
//                clearCandysCache();
//            }
//            if (candy.getState() != 0 &&candy.getNum() > 0){
//                //如果库存小于等于0，更新商品状态为缺货
//                candy.setState(1);
//                candysMapper.update(candy);
//
//                // 手动更新缓存中的数据
//                clearCandysCache();
//            }
//        }
//
//        PageInfo<Candys> pageInfo = new PageInfo<>(candys);
//        return pageInfo;
//    }

}
