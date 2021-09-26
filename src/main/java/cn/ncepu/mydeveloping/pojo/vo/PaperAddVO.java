package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author Guodong
 */
@Data
public class PaperAddVO {

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
}
