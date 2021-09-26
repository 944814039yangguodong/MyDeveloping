package cn.ncepu.mydeveloping.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Guodong
 */
@Data
public class PstartVO {

    @ApiModelProperty(value = "项目全称")
    private String projectName;

    @ApiModelProperty(value = "0创新训练、1创业训练、2创业实践")
    private Integer projectType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "项目起始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "项目结束时间")
    private Date endTime;

    @ApiModelProperty(value = "项目简介，不超过200字")
    private String projectIntroduction;

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

}
