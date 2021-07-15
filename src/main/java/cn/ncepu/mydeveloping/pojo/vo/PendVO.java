package cn.ncepu.mydeveloping.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PendVO {

    @ApiModelProperty(value = "论文名称")
    private String paperTitle;

    @ApiModelProperty(value = "发表刊物名称")
    private String publicationName;

    @ApiModelProperty(value = "检索类型")
    private String retrievalType;

    @ApiModelProperty(value = "检索号")
    private String retrievalNumber;

    @ApiModelProperty(value = "论文第一作者")
    private String paperFirstAuthor;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "专利授权时间")
    private String authorizedTime;

    @ApiModelProperty(value = "专利号")
    private String patentNumber;

    @ApiModelProperty(value = "实物作品名称")
    private String physicalWorksName;

    @ApiModelProperty(value = "作品第一作者")
    private String worksFirstAuthor;
}
