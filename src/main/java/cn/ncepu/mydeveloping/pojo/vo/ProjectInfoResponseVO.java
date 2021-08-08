package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProjectInfoResponseVO {

    @ApiModelProperty(value = "主键")
    private String projectId;

    @ApiModelProperty(value = "项目全称")
    private String projectName;

    @ApiModelProperty(value = "0创新训练、1创业训练、2创业实践")
    private Integer projectType;

    @ApiModelProperty(value = "0校级、1省级、2国家级")
    private Integer projectClass;

    @ApiModelProperty(value = "项目起始时间")
    private Date startTime;

    @ApiModelProperty(value = "项目结束时间")
    private Date endTime;

    @ApiModelProperty(value = "项目简介，不超过200字")
    private String projectIntroduction;

    @ApiModelProperty(value = "0立项申请、1中期检查、2结项审核、3延期结项、4已结项、5已取消")
    private Integer projectPhase;

    @ApiModelProperty(value = "项目所属院系")
    private String department;

    @ApiModelProperty(value = "指导老师的职工号")
    private String teacherId;

    @ApiModelProperty(value = "指导老师的姓名")
    private String teacherName;

    @ApiModelProperty(value = "指导老师的院系")
    private String teacherDepartment;

    @ApiModelProperty(value = "指导老师的专业")
    private String teacherMajor;

    @ApiModelProperty(value = "指导老师的分工")
    private String teacherJob;

    @ApiModelProperty(value = "指导老师的联系电话")
    private String teacherPhone;

    @ApiModelProperty(value = "负责人的学生编号")
    private String headId;

    @ApiModelProperty(value = "负责人的姓名")
    private String headName;

    @ApiModelProperty(value = "负责人的院系")
    private String headDepartment;

    @ApiModelProperty(value = "负责人的专业")
    private String headMajor;

    @ApiModelProperty(value = "负责人的分工")
    private String headJob;

    @ApiModelProperty(value = "负责人的联系电话")
    private String headPhone;

    @ApiModelProperty(value = "成员二的学生编号")
    private String secondId;

    @ApiModelProperty(value = "成员二的姓名")
    private String secondName;

    @ApiModelProperty(value = "成员二的院系")
    private String secondDepartment;

    @ApiModelProperty(value = "成员二的专业")
    private String secondMajor;

    @ApiModelProperty(value = "成员二的分工")
    private String secondJob;

    @ApiModelProperty(value = "成员二的联系电话")
    private String secondPhone;

    @ApiModelProperty(value = "成员三的学生编号")
    private String thirdId;

    @ApiModelProperty(value = "成员三的姓名")
    private String thirdName;

    @ApiModelProperty(value = "成员三的院系")
    private String thirdDepartment;

    @ApiModelProperty(value = "成员三的专业")
    private String thirdMajor;

    @ApiModelProperty(value = "成员三的分工")
    private String thirdJob;

    @ApiModelProperty(value = "成员三的联系电话")
    private String thirdPhone;

    @ApiModelProperty(value = "成员四的学生编号")
    private String fourthId;

    @ApiModelProperty(value = "成员四的姓名")
    private String fourthName;

    @ApiModelProperty(value = "成员四的院系")
    private String fourthDepartment;

    @ApiModelProperty(value = "成员四的专业")
    private String fourthMajor;

    @ApiModelProperty(value = "成员四的分工")
    private String fourthJob;

    @ApiModelProperty(value = "成员四的联系电话")
    private String fourthPhone;

    @ApiModelProperty(value = "成员五的学生编号")
    private String fifthId;

    @ApiModelProperty(value = "成员五的姓名")
    private String fifthName;

    @ApiModelProperty(value = "成员五的院系")
    private String fifthDepartment;

    @ApiModelProperty(value = "成员五的专业")
    private String fifthMajor;

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

    @ApiModelProperty(value = "0等待审核、1立项成功、2立项驳回")
    private Integer startStatus;

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

    @ApiModelProperty(value = "0未中期审核、1中期审核、2中期审核通过、3中期审核未通过")
    private Integer midtermStatus;

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

    @ApiModelProperty(value = "0未结项审核、1结项审核、2结项审核通过、3延期结项、4延期项目审核、5结项审核未通过")
    private Integer endStatus;

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
}
