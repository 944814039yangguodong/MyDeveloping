package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TimeVO {

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
}
