package cn.ncepu.mydeveloping.service;

import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.pojo.vo.UserAddRequestVO;
import cn.ncepu.mydeveloping.pojo.vo.UserRequestVO;
import cn.ncepu.mydeveloping.result.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
public interface UserService extends IService<User> {
    Page<User> userPerPageByOrder(long current, long limit, String property, UserRequestVO userRequestVO);

    R checkIntegrity(String userID);
}
