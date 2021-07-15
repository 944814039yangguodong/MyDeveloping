package cn.ncepu.mydeveloping.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.ncepu.mydeveloping.pojo.entity.Project;
import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.pojo.vo.*;
import cn.ncepu.mydeveloping.result.R;
import cn.ncepu.mydeveloping.service.ProjectService;
import cn.ncepu.mydeveloping.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static cn.ncepu.mydeveloping.consts.Constant.ROOT_PATH;
import static cn.ncepu.mydeveloping.consts.Constant.SDF;
import static cn.ncepu.mydeveloping.utils.FileUtil.fileUploads;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guodong
 * @since 2021-07-12
 */
@Api(value = "项目控制",tags={"项目控制"})
@RestController
@CrossOrigin
@RequestMapping("/mydeveloping/project")
public class ProjectController {
    @Resource
    ProjectService projectService;
    @Resource
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    /**
     * 学生端操作
     */
    @ApiOperation(value = "立项申请新增项目")
    @PostMapping("projectStart")
    @SaCheckPermission("student-operation")
    R projectStart(PstartVO pstartVO, MultipartFile applicationFile, MultipartFile pptFile, MultipartFile additionalFile){
        if (StringUtils.isEmpty(pstartVO.getProjectName()))
            return R.error().message("项目名称不得为空！");
        if (StringUtils.isEmpty(pstartVO.getProjectType()))
            return R.error().message("项目类别不得为空！");
        if (StringUtils.isEmpty(pstartVO.getStartTime()))
            return R.error().message("起始时间不得为空！");
        if (StringUtils.isEmpty(pstartVO.getEndTime()))
            return R.error().message("结束时间不得为空！");
        if (StringUtils.isEmpty(pstartVO.getProjectIntroduction()))
            return R.error().message("项目简介不得为空！");
        if (StringUtils.isEmpty(pstartVO.getDepartment()))
            return R.error().message("项目所在院系不得为空！");
        if (StringUtils.isEmpty(pstartVO.getTeacherId()))
            return R.error().message("指导老师职工号不得为空！");
        Project project=new Project();
        BeanUtils.copyProperties(pstartVO,project);
        if (StringUtils.isEmpty(project.getSecondId())){
            project.setSecondId("");
            project.setSecondJob("");
            project.setSecondPhone("");
        }
        if (StringUtils.isEmpty(project.getThirdId())){
            project.setThirdId("");
            project.setThirdJob("");
            project.setThirdPhone("");
        }
        if (StringUtils.isEmpty(project.getFourthId())){
            project.setFourthId("");
            project.setFourthJob("");
            project.setFourthPhone("");
        }
        if (StringUtils.isEmpty(project.getFifthId())){
            project.setFifthId("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }
        project.setHeadId((String) StpUtil.getLoginId());
        project.setLogSubmitCount(0);//设置初始日志提交数为0
        project.setLogNotReadCount(0);//设置初始日志未阅读数为0
        BigDecimal temp = new BigDecimal(0);
        project.setReimbursementAmount(temp);//设置初始项目报销金额为0
        project.setProjectPhase("立项申请");//设置初始项目阶段为立项申请
        project.setProjectClass("校级");//设置初始项目等级为校级
        project.setStartStatus("等待审核");//设置初始立项状态为等待审核
        project.setMidtermStatus("未中期审核");//设置初始中期状态为未中期审核
        project.setEndStatus("未结项审核");//设置初始结项状态为未结项审核
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "start";
        if(applicationFile!=null){
            String newFileName =ROOT_PATH + fileUploads(applicationFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"StartApplication");
            project.setStartApplication(newFileName);
        } else return R.error().message("立项申请表不得为空！");
        if(pptFile!=null){
            String newFileName =ROOT_PATH + fileUploads(pptFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"StartPpt");
            project.setStartPpt(newFileName);
        } else return R.error().message("答辩PPT不得为空！");
        if(additionalFile!=null){
            String newFileName =ROOT_PATH + fileUploads(additionalFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"StartAdditionalFile");
            project.setStartAdditionalFile(newFileName);
        }
        boolean res = projectService.save(project);
        if (res){
            return R.ok().message("新增项目成功！");
        }
        return R.error().message("新增项目失败！");
    }

    @ApiOperation(value = "申请中期")
    @PostMapping("projectMidterm")
    @SaCheckPermission("student-operation")
    R projectMidterm(String projectId, MultipartFile reportFile, MultipartFile pptFile,MultipartFile changeFile, MultipartFile additionalFile){
        Project project = projectService.getById(projectId);
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "midterm";
        if(reportFile!=null){
            String newFileName =ROOT_PATH + fileUploads(reportFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"MidtermReport");
            project.setMidtermReport(newFileName);
        } else return R.error().message("中期报告不得为空！");
        if(pptFile!=null){
            String newFileName =ROOT_PATH + fileUploads(pptFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"MidtermPpt");
            project.setMidtermPpt(newFileName);
        } else return R.error().message("答辩PPT不得为空！");
        if(changeFile!=null){
            String newFileName =ROOT_PATH + fileUploads(changeFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"MidtermChange");
            project.setMidtermChange(newFileName);
        }
        if(additionalFile!=null){
            String newFileName =ROOT_PATH + fileUploads(additionalFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"MidtermAdditionalFile");
            project.setMidtermAdditionalFile(newFileName);
        }
        project.setMidtermStatus("中期审核");//设置初始中期状态为中期审核
        boolean res = projectService.updateById(project);
        if (res){
            return R.ok().message("申请中期成功！");
        }
        return R.error().message("申请中期失败！");
    }

    @ApiOperation(value = "申请结项")
    @PostMapping("projectEnd")
    @SaCheckPermission("student-operation")
    R projectEnd(String projectId, PendVO pendVO, MultipartFile reportFile, MultipartFile conclusionFile, MultipartFile tableFile, MultipartFile fileFile, MultipartFile pptFile, MultipartFile summaryFile, MultipartFile extensionFile, MultipartFile additionalFile){
        Project project = projectService.getById(projectId);
        BeanUtils.copyProperties(pendVO,project);
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "end";
        if(reportFile!=null){
            String newFileName =ROOT_PATH + fileUploads(reportFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndReport");
            project.setEndReport(newFileName);
        } else return R.error().message("华北电力大学大学生创新创业训练计划结题报告书不得为空！");
        if(conclusionFile!=null){
            String newFileName =ROOT_PATH + fileUploads(conclusionFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"ConclusionReport");
            project.setConclusionReport(newFileName);
        } else return R.error().message("大学生创新性实验计划项目研究总结报告不得为空！");
        if(tableFile!=null){
            String newFileName =ROOT_PATH + fileUploads(tableFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"OutcomeTable");
            project.setOutcomeTable(newFileName);
        } else return R.error().message("成果信息表不得为空！");
        if(fileFile!=null){
            String newFileName =ROOT_PATH + fileUploads(fileFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"OutcomeFile");
            project.setOutcomeFile(newFileName);
        } else return R.error().message("成果展示文件不得为空！");
        if(pptFile!=null){
            String newFileName =ROOT_PATH + fileUploads(pptFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndPpt");
            project.setEndPpt(newFileName);
        } else return R.error().message("答辩PPT不得为空！");
        if(summaryFile!=null){
            String newFileName =ROOT_PATH + fileUploads(summaryFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"PersonalSummary");
            project.setPersonalSummary(newFileName);
        } else return R.error().message("个人总结不得为空！");
        if(additionalFile!=null){
            String newFileName =ROOT_PATH + fileUploads(additionalFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndAdditionalFile");
            project.setEndAdditionalFile(newFileName);
        }
        if(project.getEndStatus().equals("未结项审核"))
            project.setEndStatus("结项审核");//设置结项状态为结项审核
        else
            project.setEndStatus("延期项目审核");//若为延期结项则改结项状态为延期项目审核
        boolean res = projectService.updateById(project);
        if (res){
            return R.ok().message("申请结项成功！");
        }
        return R.error().message("申请结项失败！");
    }

    @ApiOperation(value = "申请延期")
    @PostMapping("projectExtension")
    @SaCheckPermission("student-operation")
    R projectExtension(String projectId, String extensionReason, MultipartFile extensionFile){
        Project project = projectService.getById(projectId);
        project.setExtensionReason(extensionReason);
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "end";
        if(extensionFile!=null){
            String newFileName =ROOT_PATH + fileUploads(extensionFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"ExtensionApplication");
            project.setExtensionApplication(newFileName);
        } else return R.error().message("延期申请表不得为空！");
        project.setEndStatus("延期结项");//设置结项状态为延期结项
        boolean res = projectService.updateById(project);
        if (res){
            return R.ok().message("申请结项成功！");
        }
        return R.error().message("申请结项失败！");
    }

    @ApiOperation(value = "删除项目成员")
    @PostMapping("memberDelete/{projectId}/{memberId}")
    @SaCheckPermission("student-operation")
    R memberDelete(@PathVariable String projectId, @PathVariable String memberId){
        Project project = projectService.getById(projectId);
        if(project.getSecondId().equals(memberId)){
            project.setSecondId(project.getThirdId());
            project.setSecondJob(project.getThirdJob());
            project.setSecondPhone(project.getThirdPhone());
            project.setThirdId(project.getFourthId());
            project.setThirdJob(project.getFourthJob());
            project.setThirdPhone(project.getFourthPhone());
            project.setFourthId(project.getFifthId());
            project.setFourthJob(project.getFifthJob());
            project.setFourthPhone(project.getFifthPhone());
            project.setFifthId("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }
        else if(project.getThirdId().equals(memberId)){
            project.setThirdId(project.getFourthId());
            project.setThirdJob(project.getFourthJob());
            project.setThirdPhone(project.getFourthPhone());
            project.setFourthId(project.getFifthId());
            project.setFourthJob(project.getFifthJob());
            project.setFourthPhone(project.getFifthPhone());
            project.setFifthId("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }else if(project.getFourthId().equals(memberId)){
            project.setFourthId(project.getFifthId());
            project.setFourthJob(project.getFifthJob());
            project.setFourthPhone(project.getFifthPhone());
            project.setFifthId("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }else if(project.getFifthId().equals(memberId)){
            project.setFifthId("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }else
            return R.error().message("该成员不存在！");
        if(projectService.updateById(project))
            return R.ok().message("删除成员成功！");
        return R.error().message("删除成员失败！");
    }

    @ApiOperation(value = "添加项目成员")
    @PostMapping("memberInsert/{projectId}/{memberId}")
    @SaCheckPermission("student-operation")
    R memberInsert(@PathVariable String projectId, @PathVariable String memberId, String job, String phone){
        Project project = projectService.getById(projectId);
        if(project.getSecondId().equals("")){
            project.setSecondId(memberId);
            project.setSecondJob(job);
            project.setSecondPhone(phone);
        }else if(project.getThirdId().equals("")){
            project.setThirdId(memberId);
            project.setThirdJob(job);
            project.setThirdPhone(phone);
        }else if(project.getFourthId().equals("")){
            project.setFourthId(memberId);
            project.setFourthJob(job);
            project.setFourthPhone(phone);
        }else if(project.getFifthId().equals("")){
            project.setFifthId(memberId);
            project.setFifthJob(job);
            project.setFifthPhone(phone);
        }else
            return R.error().message("成员数量以达到上限！");
        return R.ok().message("添加成员成功！");
    }

    @ApiOperation(value = "学生提交日志")
    @PostMapping("logSubmit/{projectId}")
    @SaCheckPermission("student-operation")
    R logSubmit(@PathVariable String projectId){
        Project project = projectService.getById(projectId);
        project.setLogSubmitCount(project.getLogSubmitCount()+1);
        project.setLogNotReadCount(project.getLogNotReadCount()+1);
        if(projectService.updateById(project))
            return R.error().message("提交日志成功！");
        return R.error().message("提交日志失败！");
    }

    @ApiOperation(value = "学生提交财务报销单")
    @PostMapping("financeSubmit/{projectId}")
    @SaCheckPermission("student-operation")
    R financeSubmit(@PathVariable String projectId, @RequestParam BigDecimal amount, MultipartFile tableFile){
        Project project = projectService.getById(projectId);
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "finance";
        if(tableFile!=null){
            String newFileName =ROOT_PATH + fileUploads(tableFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"ReimbursementTable");
            project.setReimbursementTable(newFileName);
        } else return R.error().message("财务报销单不得为空！");
        project.setReimbursementAmount(amount);
        boolean res = projectService.updateById(project);
        if (res){
            return R.ok().message("提交财务报销单成功！");
        }
        return R.error().message("提交财务报销单失败！");
    }

    /**
     * 普通教师操作
     */
    @ApiOperation(value = "导师查阅日志")
    @PostMapping("logRead/{projectId}")
    @SaCheckPermission("teacher-operation")
    R logRead(@PathVariable String projectId){
        Project project = projectService.getById(projectId);
        if(project.getLogNotReadCount()>0){
            project.setLogNotReadCount(project.getLogNotReadCount()-1);
            if(projectService.updateById(project))
                return R.ok().message("查阅日志成功！");
            return R.error().message("查阅日志失败！");
        }
        return R.error().message("待审阅日志数不足！");
    }

    /**
     * 审核教师操作
     */
    @ApiOperation(value = "立项审核")
    @PostMapping("startEvaluation/{projectId}")
    @SaCheckPermission("reviewer-operation")
    R startEvaluation(@PathVariable String projectId, @RequestParam boolean isApproved, Integer grade, String failureDetails){
        Project project = projectService.getById(projectId);
        if(isApproved){
            project.setStartStatus("立项成功");
        }else {
            project.setStartStatus("立项驳回");
            project.setStartFailureDetails(failureDetails);
        }
        project.setStartGrade(grade);
        project.setStartReviewerId((String) StpUtil.getLoginId());
        if(projectService.updateById(project))
            return R.ok().message("立项审核操作成功！");
        return R.error().message("立项审核操作失败！");
    }

    @ApiOperation(value = "中期审核")
    @PostMapping("midtermEvaluation/{projectId}")
    @SaCheckPermission("reviewer-operation")
    R midtermEvaluation(@PathVariable String projectId, @RequestParam boolean isApproved, Integer grade, String failureDetails){
        Project project = projectService.getById(projectId);
        if(isApproved){
            project.setMidtermStatus("中期审核通过");
        }else {
            project.setMidtermStatus("中期审核未通过");
            project.setMidtermFailureDetails(failureDetails);
        }
        project.setMidtermGrade(grade);
        project.setMidtermReviewerId((String) StpUtil.getLoginId());
        if(projectService.updateById(project))
            return R.ok().message("中期审核操作成功！");
        return R.error().message("中期审核操作失败！");
    }

    @ApiOperation(value = "结项审核")
    @PostMapping("endEvaluation/{projectId}")
    @SaCheckPermission("reviewer-operation")
    R endEvaluation(@PathVariable String projectId, @RequestParam boolean isApproved, Integer grade, String failureDetails){
        Project project = projectService.getById(projectId);
        if(isApproved){
            project.setEndStatus("结项审核通过");
        }else {
            project.setEndStatus("延期结项");
            project.setExtensionDetails(failureDetails);
        }
        project.setEndGrade(grade);
        project.setEndReviewerId((String) StpUtil.getLoginId());
        if(projectService.updateById(project))
            return R.ok().message("结项审核操作成功！");
        return R.error().message("结项审核操作失败！");
    }

    @ApiOperation(value = "延期审核")
    @PostMapping("extensionEvaluation/{projectId}")
    @SaCheckPermission("reviewer-operation")
    R extensionEvaluation(@PathVariable String projectId, @RequestParam boolean isApproved, Integer grade, String failureDetails){
        Project project = projectService.getById(projectId);
        if(isApproved){
            project.setEndStatus("结项审核通过");
        }else {
            project.setEndStatus("结项审核未通过");
            project.setEndFailureDetails(failureDetails);
        }
        project.setExtensionGrade(grade);
        project.setExtensionReviewerId((String) StpUtil.getLoginId());
        if(projectService.updateById(project))
            return R.ok().message("延期审核操作成功！");
        return R.error().message("延期审核操作失败！");
    }

    /**
     * 系级负责人操作
     */

    /**
     * 校级负责人操作
     */
    @ApiOperation(value = "所有立项成功的项目转入中期检查状态")
    @PostMapping("enterMidterm")
    @SaCheckPermission("school-operation")
    R enterMidterm(){
        String originalPhase = "立项申请";
        String originalStatus = "立项成功";
        String newPhase = "中期检查";
        if(projectService.updatePhase(originalPhase,originalStatus,newPhase))
            return R.ok().message("操作成功！");
        else
            return R.error().message("操作失败！");
    }

    @ApiOperation(value = "中期结束项目升级")
    @PostMapping("projectPromote")
    @SaCheckPermission("school-operation")
    R projectPromote(@RequestParam Integer province, @RequestParam Integer nation){
        if(projectService.updateTop(province,nation))
            return R.ok().message("操作成功！");
        else
            return R.error().message("操作失败！");
    }

    @ApiOperation(value = "中期结束项目成绩排序显示")
    @PostMapping("projectRank")
    @SaCheckPermission("school-operation")
    R projectRank(@RequestParam Integer top){
        return R.ok().data("list",projectService.selectTop(top));
    }

    @ApiOperation(value = "所有中期审核通过的项目转入结项审核状态")
    @PostMapping("enterEnd")
    @SaCheckPermission("school-operation")
    R enterEnd(){
        String originalPhase = "中期检查";
        String originalStatus = "中期审核通过";
        String newPhase = "结项审核";
        if(projectService.updatePhase(originalPhase,originalStatus,newPhase))
            return R.ok().message("操作成功！");
        else
            return R.error().message("操作失败！");
    }

    @ApiOperation(value = "所有结项成功项目转入已结项状态")
    @PostMapping("end")
    @SaCheckPermission("school-operation")
    R end(){
        String originalPhase = "结项审核";
        String originalStatus = "结项审核通过";
        String newPhase = "已结项";
        if(projectService.updatePhase(originalPhase,originalStatus,newPhase))
            return R.ok().message("操作成功！");
        else
            return R.error().message("操作失败！");
    }

    @ApiOperation(value = "所有延期结项审核未通过项目转入已取消状态")
    @PostMapping("extension")
    @SaCheckPermission("school-operation")
    R extension(){
        String originalPhase = "结项审核";
        String originalStatus = "结项审核未通过";
        String newPhase = "已取消";
        if(projectService.updatePhase(originalPhase,originalStatus,newPhase))
            return R.ok().message("操作成功！");
        else
            return R.error().message("操作失败！");
    }

    /**
     * 公用操作
     */
    @ApiOperation(value = "按属性排序分页模糊获取当前用户参与项目列表（系级以上负责人查看全部）")
    @GetMapping("projectSelect/{current}/{limit}/{property}")
    R projectSelect(@PathVariable long current, @PathVariable long limit,@PathVariable String property, ProjectRequestVO projectRequestVO){
        Project project = new Project();
        BeanUtils.copyProperties(projectRequestVO,project);
        String memberId = (String) StpUtil.getLoginId();
        User user = userService.getById(memberId);
        Page<Project> projectPage;
        if(user.getUserType().equals(3)||user.getUserType().equals(4)){
            projectPage = projectService.projectPerPageByOrder(current,limit,property, project, null);
        }else{
            projectPage = projectService.projectPerPageByOrder(current,limit,property, project, memberId);
        }
        Page<ProjectListResponseVO> projectListPage = new Page<>();
        BeanUtils.copyProperties(projectPage,projectListPage);
        return R.ok().data("total",projectListPage.getTotal()).data("rows",projectListPage.getRecords());
    }

    @ApiOperation(value = "获取项目详细信息")
    @GetMapping("projectInfo/{projectId}")
    R projectSelect(@PathVariable String projectId){
        Project project = projectService.getById(projectId);
        ProjectInfoResponseVO projectInfoResponseVO = new ProjectInfoResponseVO();
        BeanUtils.copyProperties(project,projectInfoResponseVO);
        return R.ok().data("projectInfo",projectInfoResponseVO);
    }

    @ApiOperation(value = "(逻辑)删除项目")
    @DeleteMapping("projectDelete")
    R projectDelete(String projectId){
        Project project = projectService.getById(projectId);
        if (StringUtils.isEmpty(project)) {
            return R.error().message("未找到该项目信息，无法删除！");
        }
        boolean res = projectService.removeById(project);
        if (res){
            return R.ok().message("删除项目成功！");
        }
        return R.error().message("删除项目失败！");
    }
}

