package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProjectListResponseVO {

    @ApiModelProperty(value = "主键")
    private String projectId;

    @ApiModelProperty(value = "项目全称")
    private String projectName;

    @ApiModelProperty(value = "项目所属院系")
    private String department;

    @ApiModelProperty(value = "创新训练、创业训练、创业实践")
    private String projectType;

    @ApiModelProperty(value = "校级、省级、国家级，初始为校级，中期后根据评分排名升级")
    private String projectClass;

    @ApiModelProperty(value = "立项申请、中期检查、结项审核、延期结项、已结项、已取消")
    private String projectPhase;

    @ApiModelProperty(value = "项目起始时间")
    private Date startTime;

    @ApiModelProperty(value = "项目结束时间")
    private Date endTime;

    @ApiModelProperty(value = "项目报销总金额，初始为0")
    private BigDecimal reimbursementAmount;

    @ApiModelProperty(value = "项目报销提交时间")
    private Date reimbursementDate;

    @ApiModelProperty(value = "指导老师的职工号")
    private String teacherId;

    @ApiModelProperty(value = "负责人的学生编号")
    private String headId;

}
