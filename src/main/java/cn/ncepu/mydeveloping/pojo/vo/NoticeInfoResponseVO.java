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
public class NoticeInfoResponseVO {

    @ApiModelProperty(value = "公告编号")
    private Integer noticeId;

    @ApiModelProperty(value = "公告名称")
    private String noticeName;

    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    @ApiModelProperty(value = "公告栏每点击一次增加一，初始为0")
    private Long noticeReadCount;

    @ApiModelProperty(value = "公告附件1文件路径，0代表已删除")
    private String noticeAttachmentOne;

    @ApiModelProperty(value = "公告附件2文件路径，0代表已删除")
    private String noticeAttachmentTwo;

    @ApiModelProperty(value = "公告附件3文件路径，0代表已删除")
    private String noticeAttachmentThree;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
}
