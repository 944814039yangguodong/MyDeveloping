package cn.ncepu.mydeveloping.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
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
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static cn.ncepu.mydeveloping.consts.Constant.*;
import static cn.ncepu.mydeveloping.consts.FieldNameConstants.*;
import static cn.ncepu.mydeveloping.utils.StrUtil.isChineseIncluded;

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
    public R userLogin(@RequestBody LoginRequestVo loginUser){
        if(ObjectUtils.isEmpty(loginUser.getUserId())){
            return R.error().message("账号不得为空，请输入账号！");
        }
        if(ObjectUtils.isEmpty(loginUser.getUserPassword())){
            return R.error().message("密码不得为空，请输入密码！");
        }
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
                return R.error().message("密码错误，请重新输入！");
            }
        }else {
            return R.error().message("该用户名不存在！");
        }
    }

    @ApiOperation(value = "系级负责人新增用户")
    @PostMapping("userInsert")
    @SaCheckPermission("department-operation")
    R userInsert(@RequestBody UserAddRequestVO userAddRequestVO){
        if (ObjectUtils.isEmpty(userAddRequestVO.getUserId())) {
            return R.error().message("学工号不得为空！");
        }
        if (!ObjectUtils.isEmpty(userService.getById(userAddRequestVO.getUserId()))) {
            return R.error().message("该学工号已经注册！");
        }else if(isChineseIncluded(userAddRequestVO.getUserId())){
            return R.error().message("学工号不能包含汉字！");
        }
        if (ObjectUtils.isEmpty(userAddRequestVO.getUserName())) {
            return R.error().message("姓名不得为空！");
        }
        if (ObjectUtils.isEmpty(userAddRequestVO.getUserPassword())) {
            //初始密码为123456
            userAddRequestVO.setUserPassword("123456");
        }else if(isChineseIncluded(userAddRequestVO.getUserPassword())){
            return R.error().message("密码不能包含汉字！");
        }
        if (ObjectUtils.isEmpty(userAddRequestVO.getUserType())) {
            return R.error().message("用户类别不得为空！");
        }
        if (ObjectUtils.isEmpty(userAddRequestVO.getDepartment())) {
            return R.error().message("所在院系不得为空！");
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequestVO,user);
        String md5Password = SaSecureUtil.md5(userAddRequestVO.getUserPassword());
        user.setUserPassword(md5Password);
        boolean res = userService.save(user);
        if (res){
            return R.ok().message("新增用户成功！");
        }
        return R.error().message("新增用户失败！");
    }

    @ApiOperation(value = "校级负责人(逻辑)删除用户信息")
    @DeleteMapping("userDelete")
    @SaCheckPermission("department-operation")
    R userDelete(String userId){
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
    @PostMapping("userModify")
    @SaCheckPermission("department-operation")
    R userModify(@RequestBody UserAlterRequestVO userAlterRequestVO){
        if (ObjectUtils.isEmpty(userAlterRequestVO.getUserId())) {
            return R.error().message("用户ID不得为空！");
        }
        if (ObjectUtils.isEmpty(userAlterRequestVO.getUserType())) {
            return R.error().message("用户类别不得为空！");
        }else if (userAlterRequestVO.getUserType()<STUDENT||userAlterRequestVO.getUserType()>SCHOOL){
            return R.error().message("用户类别不存在！");
        }
        if (ObjectUtils.isEmpty(userAlterRequestVO.getDepartment())) {
            return R.error().message("所在院系不得为空！");
        }
        User user = userService.getById(userAlterRequestVO.getUserId());
        if (ObjectUtils.isEmpty(user)) {
            return R.error().message("未找到该用户信息，无法修改！");
        }
        if(!ObjectUtils.isEmpty(userAlterRequestVO.getUserPassword())){
            String md5Password = SaSecureUtil.md5(userAlterRequestVO.getUserPassword());
            userAlterRequestVO.setUserPassword(md5Password);
        }else {
            userAlterRequestVO.setUserPassword(user.getUserPassword());
        }
        BeanUtils.copyProperties(userAlterRequestVO,user);
        boolean res = userService.updateById(user);
        if (res){
            return R.ok().message("修改用户信息成功！");
        }
        return R.error().message("修改用户信息失败！");
    }

    @ApiOperation(value = "管理员分页模糊查询用户信息")
    @GetMapping("userSelectPage")
    @SaCheckPermission("department-operation")
    R userSelectPage(long current, long limit, String property, UserRequestVO userRequestVO){
        if(property.equals(GMT_CREATE)||property.equals(USER_ID)||property.equals(USER_NAME)||property.equals(USER_TYPE)||property.equals(DEPARTMENT_FIELD_NAME)||property.equals(MAJOR)){
            Page< User > userPage =
                    userService.userPerPageByOrder(current,limit,property,userRequestVO);
            //总记录数
            long total = userPage.getTotal();
            //数据list集合
            List<User> records = userPage.getRecords();
            return R.ok().data("total",total).data("rows",records);
        }
        return R.error().message("不允许以属性:"+property+"排序，仅允许以属性‘gmt_create’,'user_id','user_name','user_type','department','major'排序");
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("getUserInfo")
    public R getUserInfo(){
        User user = userService.getById((String) StpUtil.getLoginId());
        if(ObjectUtils.isEmpty(user)){
            return R.error().message("用户不存在！");
        }
        UserInfoResponseVo userInfo = new UserInfoResponseVo();
        BeanUtils.copyProperties(user,userInfo);
        return R.ok().data("userInfo",userInfo);
    }

    @ApiOperation("获取某Id的用户信息")
    @GetMapping("getUserInfoById")
    public R getUserInfoById(String userId){
        User user = userService.getById(userId);
        if(ObjectUtils.isEmpty(user)){
            return R.error().message("用户不存在！");
        }
        UserInfoResponseVo userInfo = new UserInfoResponseVo();
        BeanUtils.copyProperties(user,userInfo);
        return R.ok().data("userInfo",userInfo);
    }

    @ApiOperation(value = "用户修改个人密码")
    @PostMapping("passwordModify")
    R passwordModify(String userId, String userPassword, String userOldPassword){
        if(!userId.equals(StpUtil.getLoginId())) {
            return R.error().message("输入账号与当前登录账号不一致！");
        }
        if(ObjectUtils.isEmpty(userOldPassword)) {
            return R.error().message("原密码不能为空！");
        }else if(isChineseIncluded(userOldPassword)){
            return R.error().message("原密码不能包含汉字！");
        }
        if(ObjectUtils.isEmpty(userPassword)) {
            return R.error().message("新密码不能为空！");
        }else if(isChineseIncluded(userPassword)){
            return R.error().message("新密码不能包含汉字！");
        }
        User user = userService.getById(userId);
        if (user!=null){
            boolean equals = SaSecureUtil.md5(userOldPassword).equals(user.getUserPassword());
            if(equals){
                String md5Password = SaSecureUtil.md5(userPassword);
                user.setUserPassword(md5Password);
                boolean res = userService.updateById(user);
                if (res){
                    return R.ok().message("修改密码成功！");
                }
                return R.error().message("修改密码失败！");
            }else {
                return R.error().message("原密码错误，请重新输入！");
            }
        }else {
            return R.error().message("该用户名不存在！");
        }

    }
}

