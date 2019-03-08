package com.mapscience.modular.vehicle.service.impl;

import com.mapscience.modular.vehicle.model.VcleBelong;
import com.mapscience.modular.vehicle.mapper.VcleBelongMapper;
import com.mapscience.modular.vehicle.service.IVcleBelongService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆所属司机 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-01-02
 */
@Service
public class VcleBelongServiceImpl extends ServiceImpl<VcleBelongMapper, VcleBelong> implements IVcleBelongService {
    /**
     * 查出当前车辆司机列表
     *
     * @param vcleId
     * @return
     */
    @Override
    public List<Map<String, Object>> getVcleBelongListByVcleId(String vcleId) {
        List<Map<String, Object>> list = baseMapper.getDriverByVcleId(vcleId, null);
        return list;
    }

    /**
     * 判断司机是否更改true未更改false已更改
     *
     * @param vcleId
     * @param driId
     * @return
     */
    @Override
    public Boolean getDriverByVcleId(String vcleId, String driId) {
        if(vcleId==null){
            return true;
        }
        List<VcleBelong> list = baseMapper.getDriverByVcleIdOrDriId(vcleId, null);
        if (list.size() > 0) {
            VcleBelong vcleBelong=list.get(0);
            if (driId.equals(vcleBelong.getDriId())) {
                return true;
            } else {
                vcleBelong.setState(0);
                vcleBelong.setUpdateTime(new Date());
                Integer integer = baseMapper.updateById(vcleBelong);
                if (integer > 0) {
                    return false;
                }
            }
        }
        return false;
    }

}

