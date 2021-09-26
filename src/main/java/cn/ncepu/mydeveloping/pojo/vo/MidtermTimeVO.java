package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Guodong
 */
@Data
public class MidtermTimeVO {

    @ApiModelProperty(value = "中期起始时间")
    private Date midtermBegin;

    @ApiModelProperty(value = "中期结束时间")
    private Date midtermOver;

}
