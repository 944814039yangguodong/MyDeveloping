package cn.ncepu.mydeveloping.service;


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
    Time selectNow();
}
