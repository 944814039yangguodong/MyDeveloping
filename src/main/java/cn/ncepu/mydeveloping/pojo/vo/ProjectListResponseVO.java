package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Guodong
 */
@Data
public class ProjectListResponseVO {

    @ApiModelProperty(value = "主键")
    private String projectId;

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

    @ApiModelProperty(value = "指导老师的姓名")
    private String teacherName;

    @ApiModelProperty(value = "负责人的姓名")
    private String headName;
}
