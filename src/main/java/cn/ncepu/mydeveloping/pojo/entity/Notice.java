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
@ApiModel(value="Notice对象", description="")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    @ApiModelProperty(value = "外键，公告发布作者的教师编号")
    private String userId;

    @ApiModelProperty(value = "公告栏显示的公告名称")
    private String noticeName;

    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    @ApiModelProperty(value = "公告栏每点击一次增加一，初始为0")
    private Long noticeReadCount;

    @ApiModelProperty(value = "公告附件1文件路径，0代表已删除")
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String noticeAttachmentOne;

    @ApiModelProperty(value = "公告附件2文件路径，0代表已删除")
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String noticeAttachmentTwo;

    @ApiModelProperty(value = "公告附件3文件路径，0代表已删除")
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String noticeAttachmentThree;

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
