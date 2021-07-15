package cn.ncepu.mydeveloping.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.pojo.vo.*;
import cn.ncepu.mydeveloping.result.R;
import cn.ncepu.mydeveloping.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
@Api(value = "用户控制",tags={"用户控制"})
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Resource
    UserService userService;

    @ApiOperation("用户登录接口")
    @PostMapping("login")
    public R userLogin(LoginRequestVo loginUser){
        //获取用户Id
        String userId = loginUser.getUserId();
        //获取用户封装信息
        User user = userService.getById(userId);
        if (user!=null){
            boolean equals = SaSecureUtil.md5(loginUser.getUserPassword()).equals(user.getUserPassword());
            if(equals){
                StpUtil.setLoginId(user.getUserId());
                UserInfoResponseVo userInfo = new UserInfoResponseVo();
                BeanUtils.copyProperties(user,userInfo);
                return R.ok().data(StpUtil.getTokenName(),StpUtil.getTokenValue()).data("userInfo",userInfo);
            }else {
                return R.error().message("密码错误 请重新输入");
            }
        }else {
            return R.error().message("没有该用户");
        }
    }

    @ApiOperation(value = "校级负责人新增用户")
    @PostMapping("userInsert")
    @SaCheckPermission("department-operation")
    R userInsert(UserAddRequestVO userAddRequestVO){
        if (ObjectUtils.isEmpty(userAddRequestVO.getUserId()))
            return R.error().message("学工号不得为空！");
        if (!ObjectUtils.isEmpty(userService.getById(userAddRequestVO.getUserId())))
            return R.error().message("该教工号已经注册！");
        if (ObjectUtils.isEmpty(userAddRequestVO.getUserPassword()))
            userAddRequestVO.setUserPassword("123456");//初始密码为123456
        User user = new User();
        BeanUtils.copyProperties(userAddRequestVO,user);
        String MD5Password = SaSecureUtil.md5(userAddRequestVO.getUserPassword());
        user.setUserPassword(MD5Password);
        boolean res = userService.save(user);
        if (res){
            return R.ok().message("新增用户成功！");
        }
        return R.error().message("新增用户失败！");
    }

    @ApiOperation(value = "校级负责人(逻辑)删除用户信息")
    @DeleteMapping("userDelete/{userId}")
    @SaCheckPermission("department-operation")
    R userDelete(@PathVariable String userId){
        if (ObjectUtils.isEmpty(userService.getById(userId))) {
            return R.error().message("未找到该用户信息，无法删除！");
        }
        boolean res = userService.removeById(userId);
        if (res){
            return R.ok().message("删除用户信息成功！");
        }
        return R.error().message("删除用户信息失败！");
    }

    @ApiOperation(value = "系级负责人修改用户信息（包含修改密码）")
    @PostMapping("userModify/{userId}")
    @SaCheckPermission("department-operation")
    R userModify(@PathVariable String userId, UserAlterRequestVO userAlterRequestVO){
        User user = userService.getById(userId);
        if (ObjectUtils.isEmpty(user)){
            return R.error().message("未找到该用户信息，无法修改！");
        }
        if(!ObjectUtils.isEmpty(userAlterRequestVO.getUserPassword())){
            String MD5Password = SaSecureUtil.md5(userAlterRequestVO.getUserPassword());
            userAlterRequestVO.setUserPassword(MD5Password);
        }
        BeanUtils.copyProperties(userAlterRequestVO,user);
        boolean res = userService.updateById(user);
        if (res){
            return R.ok().message("修改用户信息成功！");
        }
        return R.error().message("修改用户信息失败！");
    }

    @ApiOperation(value = "管理员分页模糊查询用户信息")
    @GetMapping("userSelectPage/{current}/{limit}/{property}")
    @SaCheckPermission("department-operation")
    R userSelectPage(@PathVariable long current, @PathVariable long limit,@PathVariable String property, UserRequestVO userRequestVO){
        if(property.equals("gmt_create")||property.equals("user_id")||property.equals("user_name")||property.equals("user_type")||property.equals("department")||property.equals("major")){
            Page< User > userPage =
                    userService.userPerPageByOrder(current,limit,property,userRequestVO);
            long total = userPage.getTotal();//总记录数
            List<User> records = userPage.getRecords();//数据list集合
            return R.ok().data("total",total).data("rows",records);
        }
        return R.error().message("不允许以属性:"+property+"排序，仅允许以属性‘gmt_create’,'user_id','user_name','user_type','department','major'排序");
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("getUserInfo")
    public R getUserInfo(){
        User user = userService.getById((String) StpUtil.getLoginId());
        UserInfoResponseVo userInfo = new UserInfoResponseVo();
        BeanUtils.copyProperties(user,userInfo);
        return R.ok().data("userInfo",userInfo);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("PasswordModify/{userPassword}")
    R PasswordModify(@PathVariable String userPassword){
        User user = userService.getById((String) StpUtil.getLoginId());
        String MD5Password = SaSecureUtil.md5(userPassword);
        user.setUserPassword(MD5Password);
        boolean res = userService.updateById(user);
        if (res){
            return R.ok().message("重置密码成功！");
        }
        return R.error().message("重置密码失败！");
    }

    @ApiOperation(value = "检验用户信息完整性")
    @PostMapping("checkIntegrity")
    R checkIntegrity(String userId) {
        if (StringUtils.isEmpty(userId)) {//学号/教工号为空
            return R.error().message("学号或教工号为空，无法校验！");
        }
        return userService.checkIntegrity(userId);
    }
}
