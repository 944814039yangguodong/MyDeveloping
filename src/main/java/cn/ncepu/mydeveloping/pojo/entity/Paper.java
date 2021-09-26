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
 * @since 2021-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Paper对象", description="")
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "论文id")
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

    @ApiModelProperty(value = "所属用户id")
    private String userId;

    @ApiModelProperty(value = "论文名")
    private String paperTitle;

    @ApiModelProperty(value = "发表刊物名称")
    private String publicationName;

    @ApiModelProperty(value = "检索类型")
    private String retrievalType;

    @ApiModelProperty(value = "检索号")
    private String retrievalNumber;

    @ApiModelProperty(value = "论文第一作者")
    private String paperFirstAuthor;

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
