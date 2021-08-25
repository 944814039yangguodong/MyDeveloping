package cn.ncepu.mydeveloping.service.impl;

import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.mapper.UserMapper;
import cn.ncepu.mydeveloping.pojo.vo.UserAddRequestVO;
import cn.ncepu.mydeveloping.pojo.vo.UserRequestVO;
import cn.ncepu.mydeveloping.result.R;
import cn.ncepu.mydeveloping.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public boolean updateById(User user) {
        return SqlHelper.retBool(userMapper.updateById(user));
    }

    @Override
    public boolean save(User user) {
        return SqlHelper.retBool(userMapper.insert(user));
    }

    @Override
    public boolean removeById(Serializable id) {
        return SqlHelper.retBool(userMapper.deleteById(id));
    }

    @Override
    public User getById(Serializable id) {
        return userMapper.selectById(id);
    }

    @Override
    public Page<User> userPerPageByOrder(long current, long limit, String property, UserRequestVO userRequestVO) {
        Page< User > userPage = new Page<>(current,limit);
        QueryWrapper<User> userQueryWrapper =
                new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(userRequestVO.getUserId())) {
            userQueryWrapper.like("user_id",userRequestVO.getUserId());
        }
        if (!ObjectUtils.isEmpty(userRequestVO.getUserName())) {
            userQueryWrapper.like("user_name",userRequestVO.getUserName());
        }
        if (!ObjectUtils.isEmpty(userRequestVO.getDepartment())) {
            userQueryWrapper.like("department",userRequestVO.getDepartment());
        }
        if (!ObjectUtils.isEmpty(userRequestVO.getMajor())) {
            userQueryWrapper.like("major",userRequestVO.getMajor());
        }
        if (!ObjectUtils.isEmpty(userRequestVO.getUserType())) {
            userQueryWrapper.like("user_type",userRequestVO.getUserType());
        }
        userQueryWrapper.orderByDesc(property);
        userMapper.selectPage(userPage,userQueryWrapper);
        return userPage;
    }

}
