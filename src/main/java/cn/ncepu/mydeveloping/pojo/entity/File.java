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
 * @since 2021-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="File对象", description="File对象")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件id")
    @TableId(value = "file_id", type = IdType.ASSIGN_ID)
    private String fileId;

    @ApiModelProperty(value = "公告或项目id")
    private String ownerId;

    @ApiModelProperty(value = "公告或项目名称")
    private String ownerName;

    @ApiModelProperty(value = "项目类型，0公告，1项目")
    private String fileType;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件存储路径")
    private String filePath;

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
