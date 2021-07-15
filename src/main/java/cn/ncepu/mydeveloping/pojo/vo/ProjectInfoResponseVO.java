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

    @ApiModelProperty(value = "等待审核、立项成功、立项驳回")
    private String startStatus;

    @ApiModelProperty(value = "未中期审核、中期审核、中期审核通过、中期审核未通过")
    private String midtermStatus;

    @ApiModelProperty(value = "未结项审核、结项审核、结项审核通过、延期结项、延期项目审核、结项审核未通过")
    private String endStatus;

}
