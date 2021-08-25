package cn.ncepu.mydeveloping.service;

import cn.ncepu.mydeveloping.pojo.entity.Project;
import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.pojo.vo.UserAddRequestVO;
import cn.ncepu.mydeveloping.pojo.vo.UserRequestVO;
import cn.ncepu.mydeveloping.result.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
public interface UserService extends IService<User> {

    /**
     * 根据id更新记录
     * @param user 新记录，忽略null值的修改
     * @return 是否成功
     */
    @Override
    boolean updateById(User user);

    /**
     * 新增纪录
     * @param user 新纪录
     * @return 是否成功
     */
    @Override
    boolean save(User user);

    /**
     * 逻辑删除纪录
     * @param id id
     * @return 是否成功
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 查询纪录
     * @param id id
     * @return 查询结果
     */
    @Override
    User getById(Serializable id);

    /**
     * 按property属性顺序，多条件，分页查询
     * @param current 当前页
     * @param limit 页面大小
     * @param property 排序属性
     * @param userRequestVO 条件
     * @return 查询结果用户列表
     */
    Page<User> userPerPageByOrder(long current, long limit, String property, UserRequestVO userRequestVO);
}
