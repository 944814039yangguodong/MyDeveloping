package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author Guodong
 */
@Data
@ApiModel(value="查询公告请求信息")
public class NoticeRequestVO {

    @ApiModelProperty(value = "公告编号")
    private Integer noticeId;

    @ApiModelProperty(value = "作者学工号")
    private String userId;

    @ApiModelProperty(value = "公告名称")
    private String noticeName;

//    @ApiModelProperty(value = "公告内容")
//    private String noticeContent;

//    @ApiModelProperty(value = "创建时间")
//    private Date gmtCreate;
}
