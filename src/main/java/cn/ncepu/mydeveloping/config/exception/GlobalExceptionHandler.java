package cn.ncepu.mydeveloping.config.exception;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.ncepu.mydeveloping.result.R;
import cn.ncepu.mydeveloping.result.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 * @author Guodong
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {

        if (e instanceof NotLoginException) {
            NotLoginException ee = (NotLoginException) e;
            return R.error().code(ResultCode.CODE_NOT_LOGIN).message("该用户没有登录: " + ee.getMessage());
        } else if (e instanceof NotRoleException) {
            // 如果是角色异常
            return R.error().code(ResultCode.CODE_NOT_JUR).message("当前登录用户没有角色权限");
        } else if (e instanceof NotPermissionException) {
            // 如果是权限异常
            NotPermissionException ee = (NotPermissionException) e;
            return R.error().code(ResultCode.CODE_NOT_JUR).message("当前登录用户没有操作权限：" + ee.getCode());
        } else if (e instanceof DisableLoginException) {
            // 如果是被封禁异常
            DisableLoginException ee = (DisableLoginException) e;
            return R.error().code(ResultCode.CODE_NOT_JUR).message("账号被封禁：" + ee.getDisableTime() + "秒后解封");
        } else {
            // 普通异常, 输出：500 + 异常信息
            e.printStackTrace();
            return R.error().code(ResultCode.ERROR).message("执行了统一异常处理: "+e.getMessage());
        }

    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R error(MyException e) {
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }

}
