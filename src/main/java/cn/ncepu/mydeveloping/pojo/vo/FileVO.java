package cn.ncepu.mydeveloping.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Guodong
 */
@Data
public class FileVO {

    @ApiModelProperty(value = "文件id")
    private String fileId;

    @ApiModelProperty(value = "公告或项目名称")
    private String ownerName;

    @ApiModelProperty(value = "项目类型，0公告，1项目")
    private String fileType;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件存储路径")
    private String filePath;

    @ApiModelProperty(value = "创建该行时的时间戳")
    private Date gmtCreate;

    @ApiModelProperty(value = "创建该行的用户编号即学工号")
    private String createUserCode;

}
