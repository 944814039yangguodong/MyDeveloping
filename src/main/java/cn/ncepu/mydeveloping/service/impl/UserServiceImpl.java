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
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
    public Page<User> userPerPageByOrder(long current, long limit, String property, UserRequestVO userRequestVO) {
        Page< User > userPage = new Page<>(current,limit);
        QueryWrapper<User> userQueryWrapper =
                new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(userRequestVO.getUserId()))
            userQueryWrapper.like("user_id",userRequestVO.getUserId());
        if (!ObjectUtils.isEmpty(userRequestVO.getUserName()))
            userQueryWrapper.like("user_name",userRequestVO.getUserName());
        if (!ObjectUtils.isEmpty(userRequestVO.getDepartment()))
            userQueryWrapper.like("department",userRequestVO.getDepartment());
        if (!ObjectUtils.isEmpty(userRequestVO.getMajor()))
            userQueryWrapper.like("major",userRequestVO.getMajor());
        if (!ObjectUtils.isEmpty(userRequestVO.getUserType()))
            userQueryWrapper.like("user_type",userRequestVO.getUserType());
        userQueryWrapper.orderByDesc(property);
        userMapper.selectPage(userPage,userQueryWrapper);
        return userPage;
    }

    @Override
    public R checkIntegrity(String userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).isNull("gmt_deleted");
        User user = userMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user.getDepartment()) || ObjectUtils.isEmpty(user.getMajor()) || ObjectUtils.isEmpty(user.getUserName())){
            return R.error().message("需完善您的基础信息！避免给您造成不必要的麻烦！");
        }
        return R.ok().message("个人信息已完善");
    }
}
