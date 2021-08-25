package cn.ncepu.mydeveloping.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.service.UserService;
import cn.ncepu.mydeveloping.consts.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 * @author Guodong
 */
@Component    // 保证此类被SpringBoot扫描，完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserService userService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        List<String> list = new ArrayList<String>();
        User user = userService.getById((String) loginId);
        if(user.getUserType().equals(Constant.STUDENT)){
            list.add("student-operation");
        }
        if(user.getUserType().equals(Constant.TEACHER)){
            list.add("student-operation");
            list.add("teacher-operation");
        }
        if(user.getUserType().equals(Constant.REVIEWER)){
            list.add("student-operation");
            list.add("teacher-operation");
            list.add("reviewer-operation");
        }
        if(user.getUserType().equals(Constant.DEPARTMENT)){
            list.add("student-operation");
            list.add("teacher-operation");
            list.add("reviewer-operation");
            list.add("department-operation");
        }
        if(user.getUserType().equals(Constant.SCHOOL)){
            list.add("student-operation");
            list.add("teacher-operation");
            list.add("reviewer-operation");
            list.add("department-operation");
            list.add("school-operation");
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        List<String> list = new ArrayList<String>();
        User user = userService.getById((String) loginId);
        if(user.getUserType().equals(Constant.SCHOOL)){
            list.add("SCHOOL");
        }
        if(user.getUserType().equals(Constant.DEPARTMENT)){
            list.add("DEPARTMENT");
        }
        if(user.getUserType().equals(Constant.REVIEWER)){
            list.add("REVIEWER");
        }
        if(user.getUserType().equals(Constant.TEACHER)){
            list.add("TEACHER");
        }
        if(user.getUserType().equals(Constant.STUDENT)){
            list.add("STUDENT");
        }
        return list;
    }

}
