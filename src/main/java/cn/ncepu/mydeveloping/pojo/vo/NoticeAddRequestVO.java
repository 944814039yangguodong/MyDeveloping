package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel(value="新增公告请求信息")
public class NoticeAddRequestVO {

    @ApiModelProperty(value = "公告名称")
    private String noticeName;

    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

//    @ApiModelProperty(value = "附件一")
//    private MultipartFile fileOne;
//
//    @ApiModelProperty(value = "附件二")
//    private MultipartFile fileTwo;
//
//    @ApiModelProperty(value = "附件三")
//    private MultipartFile fileThree;
}
