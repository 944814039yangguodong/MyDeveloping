package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Guodong
 */
@Data
@ApiModel(value="查询公告请求信息")
public class NoticeListResponseVO {

    @ApiModelProperty(value = "公告编号")
    private Integer noticeId;

    @ApiModelProperty(value = "公告名称")
    private String noticeName;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
}
