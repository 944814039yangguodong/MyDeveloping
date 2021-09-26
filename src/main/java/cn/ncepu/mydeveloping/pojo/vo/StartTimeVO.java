package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Guodong
 */
@Data
public class StartTimeVO {

    @ApiModelProperty(value = "立项起始时间")
    private Date startBegin;

    @ApiModelProperty(value = "立项结束时间")
    private Date startOver;
}
