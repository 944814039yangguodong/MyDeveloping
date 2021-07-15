package cn.ncepu.mydeveloping.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，即学工号")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;

    @ApiModelProperty(value = "用户密码，初始默认为123456")
    private String userPassword;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "所在院系")
    private String department;

    @ApiModelProperty(value = "所在专业")
    private String major;

    @ApiModelProperty(value = "身份标识值：0学生，1普通（指导）老师，2审核老师，3系级负责人，4校级负责人")
    private Integer userType;

    @ApiModelProperty(value = "创建该行时的时间戳")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改该行时的时间戳")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "删除该行时的时间戳，非空即为已删除")
    private Date gmtDeleted;

    @ApiModelProperty(value = "创建该行的用户编号即学工号")
    @TableField(fill = FieldFill.INSERT)
    private String createUserCode;

    @ApiModelProperty(value = "修改该行的用户编号即学工号")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifiedUserCode;


}
