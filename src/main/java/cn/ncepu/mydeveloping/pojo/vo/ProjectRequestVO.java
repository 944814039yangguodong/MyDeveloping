package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProjectRequestVO {
    @ApiModelProperty(value = "项目全称")
    private String projectName;

    @ApiModelProperty(value = "项目所属院系")
    private String department;

    @ApiModelProperty(value = "创新训练、创业训练、创业实践")
    private String projectType;

    @ApiModelProperty(value = "校级、省级、国家级，初始为校级，中期后根据评分排名升级")
    private String projectClass;

    @ApiModelProperty(value = "0立项申请、1中期检查、2结项审核、3延期结项、4已结项、5已取消")
    private String projectPhase;

}
