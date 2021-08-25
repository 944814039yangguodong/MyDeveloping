package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Guodong
 */
@Data
@ApiModel(value="用户信息返回类（不含密码、用户类型）", description="当前登录的用户信息")
public class UserInfoResponseVo {

    @ApiModelProperty(value = "学工号")
    private String userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "所在院系")
    private String department;

    @ApiModelProperty(value = "所在专业")
    private String major;

    @ApiModelProperty(value = "身份标识值：0学生，1普通（指导）老师，2审核老师，3系级负责人，4校级负责人")
    private Integer userType;

}
