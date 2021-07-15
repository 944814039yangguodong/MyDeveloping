package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户登录请求信息", description="用户登录请求信息")
public class LoginRequestVo {

    @ApiModelProperty(value = "账号/学工号")
    private String userId;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;
}
