package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Guodong
 */
@Data
public class EndTimeVO {

    @ApiModelProperty(value = "结项起始时间")
    private Date endBegin;

    @ApiModelProperty(value = "结项结束时间")
    private Date endOver;
}
