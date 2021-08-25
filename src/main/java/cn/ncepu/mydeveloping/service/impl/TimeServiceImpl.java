package cn.ncepu.mydeveloping.service.impl;

import cn.ncepu.mydeveloping.pojo.entity.Time;
import cn.ncepu.mydeveloping.mapper.TimeMapper;
import cn.ncepu.mydeveloping.service.TimeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, Time> implements TimeService {

    @Resource
    TimeMapper timeMapper;

    @Override
    public boolean updateById(Time time) {
        return SqlHelper.retBool(timeMapper.updateById(time));
    }

    @Override
    public Time selectNow() {
        QueryWrapper<Time> timeQueryWrapper =
                new QueryWrapper<>();
        return timeMapper.selectOne(timeQueryWrapper);
    }


}
