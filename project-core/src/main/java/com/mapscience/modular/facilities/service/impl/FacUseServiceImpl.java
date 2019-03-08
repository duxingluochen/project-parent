package com.mapscience.modular.facilities.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.mapscience.modular.facilities.mapper.FacStockMapper;
import com.mapscience.modular.facilities.model.FacStock;
import com.mapscience.modular.facilities.model.FacUse;
import com.mapscience.modular.facilities.mapper.FacUseMapper;
import com.mapscience.modular.facilities.service.IFacUseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 设施使用 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-04
 */
@Service
public class FacUseServiceImpl extends ServiceImpl<FacUseMapper, FacUse> implements IFacUseService {
    @Resource
    private FacStockMapper facStockMapper;

    public List<Map<String, Object>> selectPageList(Page<FacUse> page, String facName) {
        return baseMapper.selectPageList(page, facName);
    }

    public Boolean addOrUpdate(FacUse facUse) {
        String id = facUse.getId();
        if (id == null || id.isEmpty()) {
            //id为空，新增设施使用信息
            id = UUID.randomUUID().toString().replace("-","");
            facUse.setId(id);
            if (baseMapper.insert(facUse) > 0) {
                try{
                    if (!updateFacStockNumber(facStockMapper.selectById(facUse.getFacstockId()),facUse.getUseNumber())) {
                        baseMapper.deleteById(id);
                        return false;
                    }
                } catch (Exception e){
                    baseMapper.deleteById(id);
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            //修改设施使用信息
            FacUse oleFacUse = baseMapper.selectById(id);
            if (baseMapper.updateById(facUse) > 0) {
                if(facUse.getFacstockId().equals(oleFacUse.getFacstockId())) {
                    //同一设施，仅修改该设施库存数量
                    int changeNum = facUse.getUseNumber() - oleFacUse.getUseNumber();
                    try{
                        if (!updateFacStockNumber(facStockMapper.selectById(facUse.getFacstockId()),changeNum)) {
                            baseMapper.updateById(oleFacUse);
                            return false;
                        }
                    } catch (Exception e){
                        baseMapper.updateById(oleFacUse);
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    //修改了使用的设施
                    try{
                        FacStock cancelUse = facStockMapper.selectById(oleFacUse.getFacstockId());
                        FacStock conductUse = facStockMapper.selectById(facUse.getFacstockId());
                        //修改使用设施库存
                        if(!updateFacStockNumber(conductUse,facUse.getUseNumber())){
                            baseMapper.updateById(oleFacUse);
                            return false;
                        } else {
                            updateFacStockNumber(cancelUse,0 - oleFacUse.getUseNumber());
                        }
                    } catch (Exception e){
                        baseMapper.updateById(oleFacUse);
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //修改库存
    private Boolean updateFacStockNumber(FacStock facStock, int changeNum) throws Exception {
        while (true) {
            int facNumer = facStock.getFacNumer() - changeNum;
            if (facNumer >= 0) {
                facStock.setFacNumer(facNumer);
                if(facStockMapper.updateFacNumer(facStock) > 0) {
                    break;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public Boolean deleteUse(String id) {
        FacUse facUse = baseMapper.selectById(id);
        if(baseMapper.deleteById(id) > 0) {
            try{
                updateFacStockNumber(facStockMapper.selectById(facUse.getFacstockId()), 0 - facUse.getUseNumber());
            } catch (Exception e){
                baseMapper.insert(facUse);
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


}
