package cn.ncepu.mydeveloping.service;


import cn.ncepu.mydeveloping.pojo.entity.Project;
import cn.ncepu.mydeveloping.pojo.entity.Time;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
public interface TimeService extends IService<Time> {

    /**
     * 根据id更新记录
     * @param time 新记录，忽略null值的修改
     * @return 是否成功
     */
    @Override
    boolean updateById(Time time);

    /**
     * 查询当前时间安排
     * @return 查询结果时间类
     */
    Time selectNow();
}
