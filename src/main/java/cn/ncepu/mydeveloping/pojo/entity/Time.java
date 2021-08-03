package cn.ncepu.mydeveloping.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
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
@ApiModel(value="Time对象", description="")
public class Time implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "时间表id")
    @TableId(value = "time_id", type = IdType.AUTO)
    private Integer timeId;

    @ApiModelProperty(value = "立项起始时间")
    private Date startBegin;

    @ApiModelProperty(value = "立项结束时间")
    private Date startOver;

    @ApiModelProperty(value = "中期起始时间")
    private Date midtermBegin;

    @ApiModelProperty(value = "中期结束时间")
    private Date midtermOver;

    @ApiModelProperty(value = "结项起始时间")
    private Date endBegin;

    @ApiModelProperty(value = "结项结束时间")
    private Date endOver;

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
