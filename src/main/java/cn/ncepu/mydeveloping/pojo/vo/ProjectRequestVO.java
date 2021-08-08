package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProjectRequestVO {
    @ApiModelProperty(value = "项目全称")
    private String projectName;

    @ApiModelProperty(value = "项目所属院系")
    private String department;

    @ApiModelProperty(value = "0创新训练、1创业训练、2创业实践")
    private Integer projectType;

    @ApiModelProperty(value = "0校级、1省级、2国家级")
    private Integer projectClass;

    @ApiModelProperty(value = "0立项申请、1中期检查、2结项审核、3延期结项、4已结项、5已取消")
    private Integer projectPhase;

    @ApiModelProperty(value = "0等待审核、1立项成功、2立项驳回")
    private Integer startStatus;

    @ApiModelProperty(value = "0未中期审核、1中期审核、2中期审核通过、3中期审核未通过")
    private Integer midtermStatus;

    @ApiModelProperty(value = "0未结项审核、1结项审核、2结项审核通过、3延期结项、4延期项目审核、5结项审核未通过")
    private Integer endStatus;

}
