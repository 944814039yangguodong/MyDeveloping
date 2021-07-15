package cn.ncepu.mydeveloping.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Guodong
 * @since 2021-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Project对象", description="")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "project_id", type = IdType.ASSIGN_ID)
    private String projectId;

    @ApiModelProperty(value = "项目全称")
    private String projectName;

    @ApiModelProperty(value = "创新训练、创业训练、创业实践")
    private String projectType;

    @ApiModelProperty(value = "校级、省级、国家级，初始为校级，中期后根据评分排名升级")
    private String projectClass;

    @ApiModelProperty(value = "项目起始时间")
    private Date startTime;

    @ApiModelProperty(value = "项目结束时间")
    private Date endTime;

    @ApiModelProperty(value = "项目简介，不超过200字")
    private String projectIntroduction;

    @ApiModelProperty(value = "立项申请、中期检查、结项审核、延期结项、已结项、已取消")
    private String projectPhase;

    @ApiModelProperty(value = "项目所属院系")
    private String department;

    @ApiModelProperty(value = "指导老师的职工号")
    private String teacherId;

    @ApiModelProperty(value = "指导老师的分工")
    private String teacherJob;

    @ApiModelProperty(value = "指导老师的联系电话")
    private String teacherPhone;

    @ApiModelProperty(value = "负责人的学生编号")
    private String headId;

    @ApiModelProperty(value = "负责人的分工")
    private String headJob;

    @ApiModelProperty(value = "负责人的联系电话")
    private String headPhone;

    @ApiModelProperty(value = "成员二的学生编号")
    private String secondId;

    @ApiModelProperty(value = "成员二的分工")
    private String secondJob;

    @ApiModelProperty(value = "成员二的联系电话")
    private String secondPhone;

    @ApiModelProperty(value = "成员三的学生编号")
    private String thirdId;

    @ApiModelProperty(value = "成员三的分工")
    private String thirdJob;

    @ApiModelProperty(value = "成员三的联系电话")
    private String thirdPhone;

    @ApiModelProperty(value = "成员四的学生编号")
    private String fourthId;

    @ApiModelProperty(value = "成员四的分工")
    private String fourthJob;

    @ApiModelProperty(value = "成员四的联系电话")
    private String fourthPhone;

    @ApiModelProperty(value = "成员五的学生编号")
    private String fifthId;

    @ApiModelProperty(value = "成员五的分工")
    private String fifthJob;

    @ApiModelProperty(value = "成员五的联系电话")
    private String fifthPhone;

    @ApiModelProperty(value = "企业导师姓名")
    private String enterpriseTutorName;

    @ApiModelProperty(value = "企业导师单位")
    private String enterpriseTutorPlace;

    @ApiModelProperty(value = "企业导师专业")
    private String enterpriseTutorMajor;

    @ApiModelProperty(value = "企业导师职称")
    private String enterpriseTutorTitle;

    @ApiModelProperty(value = "企业导师指导内容")
    private String enterpriseTutorWork;

    @ApiModelProperty(value = "企业导师联系电话")
    private String enterpriseTutorPhone;

    @ApiModelProperty(value = "项目报销表单文件存储路径")
    private String reimbursementTable;

    @ApiModelProperty(value = "项目报销总金额，初始为0")
    private BigDecimal reimbursementAmount;

    @ApiModelProperty(value = "项目报销提交时间")
    private Date reimbursementDate;

    @ApiModelProperty(value = "项目日志提交数，初始为0，每提交一次加一")
    private Integer logSubmitCount;

    @ApiModelProperty(value = "项目日志审阅数，初始为0，不可为负，每提交一次加一，每查阅一次减一")
    private Integer logNotReadCount;

    @ApiModelProperty(value = "等待审核、立项成功、立项驳回")
    private String startStatus;

    @ApiModelProperty(value = "立项申请表文件存储路径")
    private String startApplication;

    @ApiModelProperty(value = "立项答辩PPT文件存储路径")
    private String startPpt;

    @ApiModelProperty(value = "立项额外文件存储路径")
    private String startAdditionalFile;

    @ApiModelProperty(value = "立项评分")
    private Integer startGrade;

    @ApiModelProperty(value = "立项驳回的原因")
    private String startFailureDetails;

    @ApiModelProperty(value = "立项审核教师职工号")
    private String startReviewerId;

    @ApiModelProperty(value = "未中期审核、中期审核、中期审核通过、中期审核未通过")
    private String midtermStatus;

    @ApiModelProperty(value = "中期报告文件存储路径")
    private String midtermReport;

    @ApiModelProperty(value = "成员变动申请表文件存储路径，0代表已删除")
    private String midtermChange;

    @ApiModelProperty(value = "中期答辩PPT文件存储路径")
    private String midtermPpt;

    @ApiModelProperty(value = "中期额外文件存储路径")
    private String midtermAdditionalFile;

    @ApiModelProperty(value = "中期评分")
    private Integer midtermGrade;

    @ApiModelProperty(value = "中期审核失败的原因")
    private String midtermFailureDetails;

    @ApiModelProperty(value = "中期审核教师职工号")
    private String midtermReviewerId;

    @ApiModelProperty(value = "未结项审核、结项审核、结项审核通过、延期结项、延期项目审核、结项审核未通过")
    private String endStatus;

    @ApiModelProperty(value = "论文名称")
    private String paperTitle;

    @ApiModelProperty(value = "发表刊物名称")
    private String publicationName;

    @ApiModelProperty(value = "检索类型")
    private String retrievalType;

    @ApiModelProperty(value = "检索号")
    private String retrievalNumber;

    @ApiModelProperty(value = "论文第一作者")
    private String paperFirstAuthor;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "专利授权时间")
    private String authorizedTime;

    @ApiModelProperty(value = "专利号")
    private String patentNumber;

    @ApiModelProperty(value = "实物作品名称")
    private String physicalWorksName;

    @ApiModelProperty(value = "作品第一作者")
    private String worksFirstAuthor;

    @ApiModelProperty(value = "华北电力大学大学生创新创业训练计划结题报告书文件存储路径")
    private String endReport;

    @ApiModelProperty(value = "大学生创新性实验计划项目研究总结报告文件存储路径")
    private String conclusionReport;

    @ApiModelProperty(value = "成果信息表文件存储路径")
    private String outcomeTable;

    @ApiModelProperty(value = "成果展示文件存储路径")
    private String outcomeFile;

    @ApiModelProperty(value = "个人总结文件存储路径")
    private String personalSummary;

    @ApiModelProperty(value = "结项答辩PPT文件存储路径")
    private String endPpt;

    @ApiModelProperty(value = "延期申请表文件存储路径")
    private String extensionApplication;

    @ApiModelProperty(value = "结项额外文件存储路径")
    private String endAdditionalFile;

    @ApiModelProperty(value = "结项评分")
    private Integer endGrade;

    @ApiModelProperty(value = "申请延期原因")
    private String extensionReason;

    @ApiModelProperty(value = "结项审核延期的原因")
    private String extensionDetails;

    @ApiModelProperty(value = "结项审核教师职工号")
    private String endReviewerId;

    @ApiModelProperty(value = "延期评分")
    private Integer extensionGrade;

    @ApiModelProperty(value = "结项审核失败的原因")
    private String endFailureDetails;

    @ApiModelProperty(value = "延期审核教师职工号")
    private String extensionReviewerId;

    @ApiModelProperty(value = "创建该行时的时间戳")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "创建该行的用户编号即学工号")
    @TableField(fill = FieldFill.INSERT)
    private String createUserCode;

    @ApiModelProperty(value = "修改该行时的时间戳")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "修改该行的用户编号即学工号")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifiedUserCode;

    @ApiModelProperty(value = "删除该行时的时间戳，非空即为已删除")
    private Date gmtDeleted;


}
