package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Guodong
 */
@Data
@ApiModel(value="新增用户请求信息")
public class UserAddRequestVO {
    @ApiModelProperty(value = "用户学工号")
    private String userId;

    @ApiModelProperty(value = "用户密码，初始默认为123456")
    private String userPassword;

    @ApiModelProperty(value = "用户真实姓名")
    private String userName;

    @ApiModelProperty(value = "所在院系")
    private String department;

    @ApiModelProperty(value = "所在专业")
    private String major;

    @ApiModelProperty(value = "身份标识值")
    private Integer userType;
}
