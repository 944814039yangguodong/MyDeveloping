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

    @ApiModelProperty(value = "成员二的学生编号")
    private String secondId;

    @ApiModelProperty(value = "成员二的姓名")
    private String secondName;

    @ApiModelProperty(value = "成员三的学生编号")
    private String thirdId;

    @ApiModelProperty(value = "成员三的姓名")
    private String thirdName;

    @ApiModelProperty(value = "成员四的学生编号")
    private String fourthId;

    @ApiModelProperty(value = "成员四的姓名")
    private String fourthName;

    @ApiModelProperty(value = "成员五的学生编号")
    private String fifthId;

    @ApiModelProperty(value = "成员五的姓名")
    private String fifthName;

    @ApiModelProperty(value = "0等待审核、1立项成功、2立项驳回")
    private Integer startStatus;

    @ApiModelProperty(value = "0未中期审核、1中期审核、2中期审核通过、3中期审核未通过")
    private Integer midtermStatus;

    @ApiModelProperty(value = "0未结项审核、1结项审核、2结项审核通过、3延期结项、4延期项目审核、5结项审核未通过")
    private Integer endStatus;

    @ApiModelProperty(value = "项目日志提交数，初始为0，每提交一次加一")
    private Integer logSubmitCount;

    @ApiModelProperty(value = "项目日志审阅数，初始为0，不可为负，每提交一次加一，每查阅一次减一")
    private Integer logNotReadCount;

    @ApiModelProperty(value = "中期评分")
    private Integer midtermGrade;

    @ApiModelProperty(value = "项目报销表单文件存储路径")
    private String reimbursementTable;

}
