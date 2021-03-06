package cn.ncepu.mydeveloping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.ncepu.mydeveloping.pojo.entity.File;
import cn.ncepu.mydeveloping.pojo.entity.Paper;
import cn.ncepu.mydeveloping.pojo.entity.Project;
import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.pojo.vo.*;
import cn.ncepu.mydeveloping.result.R;
import cn.ncepu.mydeveloping.service.FileService;
import cn.ncepu.mydeveloping.service.ProjectService;
import cn.ncepu.mydeveloping.service.UserService;
import cn.ncepu.mydeveloping.service.PaperService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static cn.ncepu.mydeveloping.consts.Constant.*;
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
@RequestMapping("/api/project")
public class ProjectController {

    @Resource
    ProjectService projectService;
    @Resource
    UserService userService;
    @Resource
    PaperService paperService;
    @Resource
    FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    /**
     * 学生端操作
     */
    @ApiOperation(value = "立项申请新增项目")
    @PostMapping("projectStart")
    @SaCheckPermission("student-operation")
    R projectStart(PstartVO pstartVO, MultipartFile applicationFile, MultipartFile pptFile, MultipartFile additionalFile){
        if (ObjectUtils.isEmpty(pstartVO.getProjectName())) {
            return R.error().message("项目名称不得为空！");
        }
        if (ObjectUtils.isEmpty(pstartVO.getProjectType())) {
            return R.error().message("项目类别不得为空！");
        }else if((!pstartVO.getProjectType().equals(0))&&(!pstartVO.getProjectType().equals(1))&&(!pstartVO.getProjectType().equals(2))){
            return R.error().message("项目类别不符合要求！");
        }
        if (ObjectUtils.isEmpty(pstartVO.getStartTime())) {
            return R.error().message("起始时间不得为空！");
        }
        if (ObjectUtils.isEmpty(pstartVO.getEndTime())) {
            return R.error().message("结束时间不得为空！");
        }
        if (pstartVO.getStartTime().after(pstartVO.getEndTime())) {
            return R.error().message("起始时间应在结束时间之前！");
        }
        if (ObjectUtils.isEmpty(pstartVO.getProjectIntroduction())) {
            return R.error().message("项目简介不得为空！");
        }
        if (ObjectUtils.isEmpty(pstartVO.getDepartment())) {
            return R.error().message("项目所在院系不得为空！");
        }
        if (ObjectUtils.isEmpty(pstartVO.getTeacherId())) {
            return R.error().message("指导老师职工号不得为空！");
        }
        if (ObjectUtils.isEmpty(userService.getById(pstartVO.getTeacherId()))) {
            return R.error().message("指导老师职工号不存在！");
        }
        if (ObjectUtils.isEmpty(pstartVO.getHeadId())) {
            return R.error().message("负责人学工号不得为空！");
        }
        if (ObjectUtils.isEmpty(userService.getById(pstartVO.getHeadId()))) {
            return R.error().message("负责人学工号不存在！");
        }
        Project project=new Project();
        BeanUtils.copyProperties(pstartVO,project);
        if (ObjectUtils.isEmpty(project.getSecondId())){
            project.setSecondId("");
            project.setSecondName("");
            project.setSecondDepartment("");
            project.setSecondMajor("");
            project.setSecondJob("");
            project.setSecondPhone("");
        }else{
            if (ObjectUtils.isEmpty(userService.getById(project.getSecondId()))) {
                return R.error().message("成员二学工号不存在！");
            }
        }
        if (ObjectUtils.isEmpty(project.getThirdId())){
            project.setThirdId("");
            project.setThirdName("");
            project.setThirdDepartment("");
            project.setThirdMajor("");
            project.setThirdJob("");
            project.setThirdPhone("");
        }else{
            if (ObjectUtils.isEmpty(userService.getById(project.getThirdId()))) {
                return R.error().message("成员三学工号不存在！");
            }
        }
        if (ObjectUtils.isEmpty(project.getFourthId())){
            project.setFourthId("");
            project.setFourthName("");
            project.setFourthDepartment("");
            project.setFourthMajor("");
            project.setFourthJob("");
            project.setFourthPhone("");
        }else{
            if (ObjectUtils.isEmpty(userService.getById(project.getFourthId()))) {
                return R.error().message("成员四学工号不存在！");
            }
        }
        if (ObjectUtils.isEmpty(project.getFifthId())){
            project.setFifthId("");
            project.setFifthName("");
            project.setFifthDepartment("");
            project.setFifthMajor("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }else{
            if (ObjectUtils.isEmpty(userService.getById(project.getFifthId()))) {
                return R.error().message("成员五学工号不存在！");
            }
        }
        //project.setHeadId((String) StpUtil.getLoginId());

        //设置初始日志提交数为0
        project.setLogSubmitCount(0);
        //设置初始日志未阅读数为0
        project.setLogNotReadCount(0);
        //设置初始项目报销金额为0
        BigDecimal temp = new BigDecimal(0);
        project.setReimbursementAmount(temp);
        //设置初始项目阶段为立项申请
        project.setProjectPhase(PHASE_START);
        //设置初始项目等级为校级
        project.setProjectClass(CLASS_SCHOOL);
        //设置初始立项状态为等待审核
        project.setStartStatus(START_REVIEW);
        //设置初始中期状态为未中期审核
        project.setMidtermStatus(MIDTERM_WAITING);
        //设置初始结项状态为未结项审核
        project.setEndStatus(END_WAITING);
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "start";
        if(applicationFile!=null){
            String newFileName =ROOT_PATH + fileUploads(applicationFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"StartApplication");
            project.setStartApplication(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(pstartVO.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("StartApplication");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("立项申请表不得为空！");
        }
        if(pptFile!=null){
            String newFileName =ROOT_PATH + fileUploads(pptFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"StartPpt");
            project.setStartPpt(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(pstartVO.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("StartPpt");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("答辩PPT不得为空！");
        }
        if(additionalFile!=null){
            String newFileName =ROOT_PATH + fileUploads(additionalFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"StartAdditionalFile");
            project.setStartAdditionalFile(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(pstartVO.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("StartAdditionalFile");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
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
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("MidtermReport");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("中期报告不得为空！");
        }
        if(pptFile!=null){
            String newFileName =ROOT_PATH + fileUploads(pptFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"MidtermPpt");
            project.setMidtermPpt(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("MidtermPpt");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("答辩PPT不得为空！");
        }
        if(changeFile!=null){
            String newFileName =ROOT_PATH + fileUploads(changeFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"MidtermChange");
            project.setMidtermChange(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("MidtermChange");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        }
        if(additionalFile!=null){
            String newFileName =ROOT_PATH + fileUploads(additionalFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"MidtermAdditionalFile");
            project.setMidtermAdditionalFile(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("MidtermAdditionalFile");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        }
        //设置初始中期状态为中期审核
        project.setMidtermStatus(MIDTERM_REVIEW);
        boolean res = projectService.updateById(project);
        if (res){
            return R.ok().message("申请中期成功！");
        }
        return R.error().message("申请中期失败！");
    }

    @ApiOperation(value = "申请结项/延期结项")
    @PostMapping("projectEnd")
    @SaCheckPermission("student-operation")
    R projectEnd(String projectId, PendVO pendVO, MultipartFile reportFile, MultipartFile conclusionFile, MultipartFile tableFile, MultipartFile fileFile, MultipartFile pptFile, MultipartFile summaryFile, MultipartFile additionalFile){
        Project project = projectService.getById(projectId);
        BeanUtils.copyProperties(pendVO,project);
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "end";
        if(reportFile!=null){
            String newFileName =ROOT_PATH + fileUploads(reportFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndReport");
            project.setEndReport(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("EndReport");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("华北电力大学大学生创新创业训练计划结题报告书不得为空！");
        }
        if(conclusionFile!=null){
            String newFileName =ROOT_PATH + fileUploads(conclusionFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"ConclusionReport");
            project.setConclusionReport(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("ConclusionReport");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("大学生创新性实验计划项目研究总结报告不得为空！");
        }
        if(tableFile!=null){
            String newFileName =ROOT_PATH + fileUploads(tableFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"OutcomeTable");
            project.setOutcomeTable(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("OutcomeTable");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("成果信息表不得为空！");
        }
        if(fileFile!=null){
            String newFileName =ROOT_PATH + fileUploads(fileFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"OutcomeFile");
            project.setOutcomeFile(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("OutcomeFile");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("成果展示文件不得为空！");
        }
        if(pptFile!=null){
            String newFileName =ROOT_PATH + fileUploads(pptFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndPpt");
            project.setEndPpt(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("EndPpt");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("答辩PPT不得为空！");
        }
        if(summaryFile!=null){
            String newFileName =ROOT_PATH + fileUploads(summaryFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"PersonalSummary");
            project.setPersonalSummary(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("PersonalSummary");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("个人总结不得为空！");
        }
        if(additionalFile!=null){
            String newFileName =ROOT_PATH + fileUploads(additionalFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndAdditionalFile");
            project.setEndAdditionalFile(newFileName);
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("EndAdditionalFile");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        }
        if(project.getEndStatus().equals(END_WAITING)) {
            //设置结项状态为结项审核
            project.setEndStatus(END_REVIEW);
        } else {
            //若为延期结项则改结项状态为延期项目审核
            project.setEndStatus(END_EXTENSION_REVIEW);
        }
        boolean res = projectService.updateById(project);
        if (res){
            return R.ok().message("申请结项成功！");
        }
        return R.error().message("申请结项失败！");
    }

    @ApiOperation(value = "申请结项/延期结项(多论文)")
    @PostMapping("projectEndMultiPaper")
    @SaCheckPermission("student-operation")
    R projectEndMultiPaper(String projectId, PendVO pendVO,List<PaperAddVO> paperList, MultipartFile reportFile, MultipartFile conclusionFile, MultipartFile tableFile, MultipartFile fileFile, MultipartFile pptFile, MultipartFile summaryFile, MultipartFile additionalFile){
        Project project = projectService.getById(projectId);
        BeanUtils.copyProperties(pendVO,project);
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "end";
        if(reportFile!=null){
            String newFileName =ROOT_PATH + fileUploads(reportFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndReport");
            project.setEndReport(newFileName);
        } else {
            return R.error().message("华北电力大学大学生创新创业训练计划结题报告书不得为空！");
        }
        if(conclusionFile!=null){
            String newFileName =ROOT_PATH + fileUploads(conclusionFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"ConclusionReport");
            project.setConclusionReport(newFileName);
        } else {
            return R.error().message("大学生创新性实验计划项目研究总结报告不得为空！");
        }
        if(tableFile!=null){
            String newFileName =ROOT_PATH + fileUploads(tableFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"OutcomeTable");
            project.setOutcomeTable(newFileName);
        } else {
            return R.error().message("成果信息表不得为空！");
        }
        if(fileFile!=null){
            String newFileName =ROOT_PATH + fileUploads(fileFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"OutcomeFile");
            project.setOutcomeFile(newFileName);
        } else {
            return R.error().message("成果展示文件不得为空！");
        }
        if(pptFile!=null){
            String newFileName =ROOT_PATH + fileUploads(pptFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndPpt");
            project.setEndPpt(newFileName);
        } else {
            return R.error().message("答辩PPT不得为空！");
        }
        if(summaryFile!=null){
            String newFileName =ROOT_PATH + fileUploads(summaryFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"PersonalSummary");
            project.setPersonalSummary(newFileName);
        } else {
            return R.error().message("个人总结不得为空！");
        }
        if(additionalFile!=null){
            String newFileName =ROOT_PATH + fileUploads(additionalFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"EndAdditionalFile");
            project.setEndAdditionalFile(newFileName);
        }
        if(project.getEndStatus().equals(END_WAITING)) {
            //设置结项状态为结项审核
            project.setEndStatus(END_REVIEW);
        } else {
            //若为延期结项则改结项状态为延期项目审核
            project.setEndStatus(END_EXTENSION_REVIEW);
        }
        boolean res = projectService.updateById(project);
        //多个论文保存
        for (PaperAddVO paperAddVO : paperList) {
            Paper paper = new Paper();
            BeanUtils.copyProperties(paperAddVO, paper);
            paper.setUserId((String) StpUtil.getLoginId());
            paperService.save(paper);
        }
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
            File saveFile = new File();
            saveFile.setOwnerName(project.getProjectName());
            saveFile.setFileType("1");
            saveFile.setFileName("ExtensionApplication");
            saveFile.setFilePath(newFileName);
            fileService.save(saveFile);
        } else {
            return R.error().message("延期申请表不得为空！");
        }
        //设置项目阶段为延期结项
        project.setProjectPhase(PHASE_EXTENSION);
        //设置结项状态为延期结项
        project.setEndStatus(END_EXTENSION_WAITING);
        boolean res = projectService.updateById(project);
        if (res){
            return R.ok().message("申请延期成功！");
        }
        return R.error().message("申请延期失败！");
    }

    @ApiOperation(value = "删除项目成员")
    @PostMapping("memberDelete")
    @SaCheckPermission("student-operation")
    R memberDelete(String projectId, String memberId){
        Project project = projectService.getById(projectId);
        if(project.getSecondId().equals(memberId)){
            project.setSecondId(project.getThirdId());
            project.setSecondName(project.getThirdName());
            project.setSecondDepartment(project.getThirdDepartment());
            project.setSecondMajor(project.getThirdMajor());
            project.setSecondJob(project.getThirdJob());
            project.setSecondPhone(project.getThirdPhone());
            project.setThirdId(project.getFourthId());
            project.setThirdName(project.getFourthName());
            project.setThirdDepartment(project.getFourthDepartment());
            project.setThirdMajor(project.getFourthMajor());
            project.setThirdJob(project.getFourthJob());
            project.setThirdPhone(project.getFourthPhone());
            project.setFourthId(project.getFifthId());
            project.setFourthName(project.getFifthName());
            project.setFourthDepartment(project.getFifthDepartment());
            project.setFourthMajor(project.getFifthMajor());
            project.setFourthJob(project.getFifthJob());
            project.setFourthPhone(project.getFifthPhone());
            project.setFifthId("");
            project.setFifthName("");
            project.setFifthDepartment("");
            project.setFifthMajor("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }
        else if(project.getThirdId().equals(memberId)){
            project.setThirdId(project.getFourthId());
            project.setThirdName(project.getFourthName());
            project.setThirdDepartment(project.getFourthDepartment());
            project.setThirdMajor(project.getFourthMajor());
            project.setThirdJob(project.getFourthJob());
            project.setThirdPhone(project.getFourthPhone());
            project.setFourthId(project.getFifthId());
            project.setFourthName(project.getFifthName());
            project.setFourthDepartment(project.getFifthDepartment());
            project.setFourthMajor(project.getFifthMajor());
            project.setFourthJob(project.getFifthJob());
            project.setFourthPhone(project.getFifthPhone());
            project.setFifthId("");
            project.setFifthName("");
            project.setFifthDepartment("");
            project.setFifthMajor("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }
        else if(project.getFourthId().equals(memberId)){
            project.setFourthId(project.getFifthId());
            project.setFourthName(project.getFifthName());
            project.setFourthDepartment(project.getFifthDepartment());
            project.setFourthMajor(project.getFifthMajor());
            project.setFourthJob(project.getFifthJob());
            project.setFourthPhone(project.getFifthPhone());
            project.setFifthId("");
            project.setFifthName("");
            project.setFifthDepartment("");
            project.setFifthMajor("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }
        else if(project.getFifthId().equals(memberId)){
            project.setFifthId("");
            project.setFifthName("");
            project.setFifthDepartment("");
            project.setFifthMajor("");
            project.setFifthJob("");
            project.setFifthPhone("");
        }
        else {
            return R.error().message("该成员不存在！");
        }
        if(projectService.updateById(project)) {
            return R.ok().message("删除成员成功！");
        }
        return R.error().message("删除成员失败！");
    }

    @ApiOperation(value = "添加项目成员")
    @PostMapping("memberInsert")
    @SaCheckPermission("student-operation")
    R memberInsert(String projectId, String memberId, String name,  String department, String major, String job, String phone){
        Project project = projectService.getById(projectId);
        if("".equals(project.getSecondId())){
            project.setSecondId(memberId);
            project.setSecondName(name);
            project.setSecondDepartment(department);
            project.setSecondMajor(major);
            project.setSecondJob(job);
            project.setSecondPhone(phone);
        }
        else if("".equals(project.getThirdId())){
            project.setThirdId(memberId);
            project.setThirdName(name);
            project.setThirdDepartment(department);
            project.setThirdMajor(major);
            project.setThirdJob(job);
            project.setThirdPhone(phone);
        }
        else if("".equals(project.getFourthId())){
            project.setFourthId(memberId);
            project.setFourthName(name);
            project.setFourthDepartment(department);
            project.setFourthMajor(major);
            project.setFourthJob(job);
            project.setFourthPhone(phone);
        }
        else if("".equals(project.getFifthId())){
            project.setFifthId(memberId);
            project.setFifthName(name);
            project.setFifthDepartment(department);
            project.setFifthMajor(major);
            project.setFifthJob(job);
            project.setFifthPhone(phone);
        }
        else {
            return R.error().message("成员数量已达到上限！");
        }
        projectService.updateById(project);
        return R.ok().message("添加成员成功！");
    }

    @ApiOperation(value = "学生提交日志")
    @PostMapping("logSubmit")
    @SaCheckPermission("student-operation")
    R logSubmit(String projectId){
        Project project = projectService.getById(projectId);
        project.setLogSubmitCount(project.getLogSubmitCount()+1);
        project.setLogNotReadCount(project.getLogNotReadCount()+1);
        if(projectService.updateById(project)) {
            return R.ok().message("提交日志成功！");
        }
        return R.error().message("提交日志失败！");
    }

    @ApiOperation(value = "学生提交财务报销单")
    @PostMapping("financeSubmit")
    @SaCheckPermission("student-operation")
    R financeSubmit(String projectId, BigDecimal amount, MultipartFile tableFile){
        if(ObjectUtils.isEmpty(projectId)){
            return R.error().message("项目id不得为空！");
        }
        Project project = projectService.getById(projectId);
        if(ObjectUtils.isEmpty(amount)){
            return R.error().message("总金额不得为空！");
        }
        if(ObjectUtils.isEmpty(tableFile)){
            return R.error().message("财务报销单不得为空！");
        }
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "finance";
        String newFileName =ROOT_PATH + fileUploads(tableFile,SDF,ROOT_PATH,logger,userFolder,fileClass,"ReimbursementTable");
        project.setReimbursementTable(newFileName);
        project.setReimbursementAmount(amount);
        File saveFile = new File();
        saveFile.setOwnerName(project.getProjectName());
        saveFile.setFileType("1");
        saveFile.setFileName("ReimbursementTable");
        saveFile.setFilePath(newFileName);
        fileService.save(saveFile);
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
    @PostMapping("logRead")
    @SaCheckPermission("teacher-operation")
    R logRead(String projectId){
        Project project = projectService.getById(projectId);
        if(project.getLogNotReadCount()>0){
            project.setLogNotReadCount(project.getLogNotReadCount()-1);
            if(projectService.updateById(project)) {
                return R.ok().message("查阅日志成功！");
            }
            return R.error().message("查阅日志失败！");
        }
        return R.error().message("待审阅日志数不足！");
    }

    /**
     * 审核教师操作
     */
    @ApiOperation(value = "分页获取当前用户可审核项目列表（校级以上负责人查看全部）")
    @GetMapping("projectReviewSelect")
    @SaCheckPermission("reviewer-operation")
    R projectReviewSelect(long current, long limit, Integer projectPhase, Integer status){
        Project project = new Project();
        switch (projectPhase){
            case 0:
                project.setStartStatus(status);break;
            case 1:
                project.setMidtermStatus(status);break;
            case 2:
            case 3:
                project.setEndStatus(status);break;
            default:
        }
        String memberId = (String) StpUtil.getLoginId();
        User user = userService.getById(memberId);
        Page<Project> projectPage;
        if(user.getUserType().equals(DEPARTMENT)||user.getUserType().equals(REVIEWER)){
            project.setDepartment(user.getDepartment());
            projectPage = projectService.projectPerPageByOrder(current,limit,"start_time", project, null);
        }else if(user.getUserType().equals(SCHOOL)){
            projectPage = projectService.projectPerPageByOrder(current,limit,"start_time", project, null);
        }else{
            return R.error().message("该用户无审核权限！");
        }
        List<ProjectListResponseVO> projectList = new ArrayList<>();
        for(int i=0;i<projectPage.getRecords().size();i++){
            ProjectListResponseVO temp = new ProjectListResponseVO();
            BeanUtils.copyProperties(projectPage.getRecords().get(i),temp);
            if(ObjectUtils.isEmpty(temp.getHeadName())||ObjectUtils.isEmpty(temp.getTeacherName())){
                temp.setTeacherName(userService.getById(temp.getTeacherId()).getUserName());
                temp.setHeadName(userService.getById(temp.getHeadId()).getUserName());
            }
            projectList.add(temp);
        }
        return R.ok().data("total",projectPage.getTotal()).data("rows",projectList);
    }

    @ApiOperation(value = "立项审核")
    @PostMapping("startEvaluation")
    @SaCheckPermission("reviewer-operation")
    R startEvaluation(String projectId, boolean isApproved, Integer grade, String failureDetails){
        if (ObjectUtils.isEmpty(projectId)) {
            return R.error().message("项目ID不得为空！");
        }
        if (ObjectUtils.isEmpty(isApproved)) {
            return R.error().message("是否通过不得为空！");
        }
        if (ObjectUtils.isEmpty(grade)) {
            return R.error().message("分数不得为空！");
        }else if(0>=grade||grade>=100){
            return R.error().message("分数不符合要求，应在0-100之间！");
        }
        if ((!isApproved)&&ObjectUtils.isEmpty(failureDetails)) {
            return R.error().message("不通过的原因不得为空！");
        }
        Project project = projectService.getById(projectId);
        if(isApproved){
            project.setStartStatus(START_SUCCESS);
        }else {
            project.setStartStatus(START_REJECT);
            project.setStartFailureDetails(failureDetails);
        }
        project.setStartGrade(grade);
        project.setStartReviewerId((String) StpUtil.getLoginId());
        if(projectService.updateById(project)) {
            return R.ok().message("立项审核操作成功！");
        }
        return R.error().message("立项审核操作失败！");
    }

    @ApiOperation(value = "中期审核")
    @PostMapping("midtermEvaluation")
    @SaCheckPermission("reviewer-operation")
    R midtermEvaluation(String projectId, boolean isApproved, Integer grade, String failureDetails){
        if (ObjectUtils.isEmpty(projectId)) {
            return R.error().message("项目ID不得为空！");
        }
        if (ObjectUtils.isEmpty(isApproved)) {
            return R.error().message("是否通过不得为空！");
        }
        if (ObjectUtils.isEmpty(grade)) {
            return R.error().message("分数不得为空！");
        }else if(0>=grade||grade>=100){
            return R.error().message("分数不符合要求，应在0-100之间！");
        }
        if ((!isApproved)&&ObjectUtils.isEmpty(failureDetails)) {
            return R.error().message("不通过的原因不得为空！");
        }
        Project project = projectService.getById(projectId);
        if(isApproved){
            project.setMidtermStatus(MIDTERM_SUCCESS);
        }else {
            project.setMidtermStatus(MIDTERM_REJECT);
            project.setMidtermFailureDetails(failureDetails);
        }
        project.setMidtermGrade(grade);
        project.setMidtermReviewerId((String) StpUtil.getLoginId());
        if(projectService.updateById(project)) {
            return R.ok().message("中期审核操作成功！");
        }
        return R.error().message("中期审核操作失败！");
    }

    @ApiOperation(value = "结项审核（失败自动转入延期审核阶段）")
    @PostMapping("endEvaluation")
    @SaCheckPermission("reviewer-operation")
    R endEvaluation(String projectId, boolean isApproved, Integer grade, String failureDetails){
        if (ObjectUtils.isEmpty(projectId)) {
            return R.error().message("项目ID不得为空！");
        }
        if (ObjectUtils.isEmpty(isApproved)) {
            return R.error().message("是否通过不得为空！");
        }
        if (ObjectUtils.isEmpty(grade)) {
            return R.error().message("分数不得为空！");
        }else if(0>=grade||grade>=100){
            return R.error().message("分数不符合要求，应在0-100之间！");
        }
        if ((!isApproved)&&ObjectUtils.isEmpty(failureDetails)) {
            return R.error().message("不通过的原因不得为空！");
        }
        Project project = projectService.getById(projectId);
        if(isApproved){
            project.setEndStatus(END_SUCCESS);
        }else {
            project.setEndStatus(END_EXTENSION_WAITING);
            project.setExtensionDetails(failureDetails);
            project.setProjectPhase(PHASE_EXTENSION);
        }
        project.setEndGrade(grade);
        project.setEndReviewerId((String) StpUtil.getLoginId());
        if(projectService.updateById(project)) {
            return R.ok().message("结项审核操作成功！");
        }
        return R.error().message("结项审核操作失败！");
    }

    @ApiOperation(value = "延期审核")
    @PostMapping("extensionEvaluation")
    @SaCheckPermission("reviewer-operation")
    R extensionEvaluation(String projectId, boolean isApproved, Integer grade, String failureDetails){
        if (ObjectUtils.isEmpty(projectId)) {
            return R.error().message("项目ID不得为空！");
        }
        if (ObjectUtils.isEmpty(isApproved)) {
            return R.error().message("是否通过不得为空！");
        }
        if (ObjectUtils.isEmpty(grade)) {
            return R.error().message("分数不得为空！");
        }else if(0>=grade||grade>=100){
            return R.error().message("分数不符合要求，应在0-100之间！");
        }
        if ((!isApproved)&&ObjectUtils.isEmpty(failureDetails)) {
            return R.error().message("不通过的原因不得为空！");
        }
        Project project = projectService.getById(projectId);
        if(isApproved){
            project.setEndStatus(END_SUCCESS);
        }else {
            project.setEndStatus(END_REJECT);
            project.setEndFailureDetails(failureDetails);
        }
        project.setExtensionGrade(grade);
        project.setExtensionReviewerId((String) StpUtil.getLoginId());
        if(projectService.updateById(project)) {
            return R.ok().message("延期审核操作成功！");
        }
        return R.error().message("延期审核操作失败！");
    }

    /**
     * 校级负责人操作
     */
    @ApiOperation(value = "所有立项成功的项目转入中期检查状态")
    @PostMapping("enterMidterm")
    @SaCheckPermission("school-operation")
    R enterMidterm(){
        if(projectService.updatePhase(PHASE_START,START_SUCCESS,PHASE_MIDTERM)) {
            return R.ok().message("操作成功！");
        } else {
            return R.error().message("没有项目被改变！");
        }
    }

    @ApiOperation(value = "中期结束单个项目升级操作")
    @PostMapping("projectPromoteOne")
    @SaCheckPermission("school-operation")
    R projectPromoteOne(String projectId){
        Project project = projectService.getById(projectId);
        if(project.getProjectClass().equals(CLASS_SCHOOL)){
            projectService.updateClassToProvince(projectId);
            return R.ok().message("成功升级为省级！");
        }else if(project.getProjectClass().equals(CLASS_PROVINCE)) {
            projectService.updateClassToNation(projectId);
            return R.ok().message("成功升级为国家级！");
        }else if(project.getProjectClass().equals(CLASS_NATION)) {
            return R.error().message("该项目已为国家级，无法升级！");
        }else {
            return R.error().message("操作失败！");
        }
    }

    @ApiOperation(value = "中期结束单个项目降级操作")
    @PostMapping("projectDemotionOne")
    @SaCheckPermission("school-operation")
    R projectDemotionOne(String projectId){
        Project project = projectService.getById(projectId);
        if(project.getProjectClass().equals(CLASS_SCHOOL)){
            return R.ok().message("该项目已为校级，无法降级！");
        }else if(project.getProjectClass().equals(CLASS_PROVINCE)) {
            projectService.updateClassToSchool(projectId);
            return R.ok().message("成功降级为校级！");
        }else if(project.getProjectClass().equals(CLASS_NATION)) {
            projectService.updateClassToProvince(projectId);
            return R.error().message("成功降级为省级！");
        }else {
            return R.error().message("操作失败！");
        }
    }

    @ApiOperation(value = "中期结束项目升级")
    @PostMapping("projectPromote")
    @SaCheckPermission("school-operation")
    R projectPromote(@RequestParam Integer province, @RequestParam Integer nation){
        if(projectService.updateTop(province,nation)) {
            return R.ok().message("操作成功！");
        } else {
            return R.error().message("操作失败！");
        }
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
        if(projectService.updatePhase(PHASE_MIDTERM,MIDTERM_SUCCESS,PHASE_END)) {
            return R.ok().message("操作成功！");
        } else {
            return R.error().message("没有项目被改变！");
        }
    }

    @ApiOperation(value = "所有结项/延期结项成功项目转入已结项状态")
    @PostMapping("end")
    @SaCheckPermission("school-operation")
    R end(){
        if(projectService.updatePhase(PHASE_END,END_SUCCESS,PHASE_OVER)||projectService.updatePhase(PHASE_EXTENSION,END_SUCCESS,PHASE_OVER)) {
            return R.ok().message("操作成功！");
        } else {
            return R.error().message("没有项目被改变！");
        }
    }

    @ApiOperation(value = "所有立项、中期、延期审核未通过项目转入已取消状态")
    @PostMapping("enterCancel")
    @SaCheckPermission("school-operation")
    R enterCancel(){
        if(projectService.updatePhase(PHASE_EXTENSION,END_REJECT,PHASE_CANCEL)||projectService.updatePhase(PHASE_MIDTERM,MIDTERM_REJECT,PHASE_CANCEL)||projectService.updatePhase(PHASE_START,MIDTERM_REJECT,PHASE_CANCEL)) {
            return R.ok().message("操作成功！");
        } else {
            return R.error().message("没有项目被改变！");
        }
    }

    /**
     * 公用操作
     */
    @ApiOperation(value = "按属性排序分页模糊获取当前用户参与项目列表（系级以上负责人查看全部，包含报销金额信息）")
    @GetMapping("projectSelect")
    R projectSelect(long current, long limit, String property, ProjectRequestVO projectRequestVO){
        Project project = new Project();
        BeanUtils.copyProperties(projectRequestVO,project);
        String memberId = (String) StpUtil.getLoginId();
        User user = userService.getById(memberId);
        Page<Project> projectPage;
        if(user.getUserType().equals(DEPARTMENT)||user.getUserType().equals(SCHOOL)){
            projectPage = projectService.projectPerPageByOrder(current,limit,property, project, null);
        }else{
            projectPage = projectService.projectPerPageByOrder(current,limit,property, project, memberId);
        }
        List<ProjectListResponseVO> projectList = new ArrayList<>();
        for(int i=0;i<projectPage.getRecords().size();i++){
            ProjectListResponseVO temp = new ProjectListResponseVO();
            BeanUtils.copyProperties(projectPage.getRecords().get(i),temp);
            if(ObjectUtils.isEmpty(temp.getHeadName())||ObjectUtils.isEmpty(temp.getTeacherName())){
                temp.setTeacherName(userService.getById(temp.getTeacherId()).getUserName());
                temp.setHeadName(userService.getById(temp.getHeadId()).getUserName());
            }
            projectList.add(temp);
        }
        return R.ok().data("total",projectPage.getTotal()).data("rows",projectList);
    }

    @ApiOperation(value = "按属性排序分页模糊获取当前用户负责的项目列表")
    @GetMapping("projectSelectByHead")
    R projectSelectByHead(long current, long limit, String property, ProjectRequestVO projectRequestVO){
        String headId = (String) StpUtil.getLoginId();
        Project project = new Project();
        BeanUtils.copyProperties(projectRequestVO,project);
        Page<Project> projectPage;
        projectPage = projectService.projectPerPageByOrderAndHead(current,limit,property, project, headId);
        List<ProjectListResponseVO> projectList = new ArrayList<>();
        for(int i=0;i<projectPage.getRecords().size();i++){
            ProjectListResponseVO temp = new ProjectListResponseVO();
            BeanUtils.copyProperties(projectPage.getRecords().get(i),temp);
            if(ObjectUtils.isEmpty(temp.getHeadName())||ObjectUtils.isEmpty(temp.getTeacherName())){
                temp.setTeacherName(userService.getById(temp.getTeacherId()).getUserName());
                temp.setHeadName(userService.getById(temp.getHeadId()).getUserName());
            }
            projectList.add(temp);
        }
        return R.ok().data("total",projectPage.getTotal()).data("rows",projectList);
    }

    @ApiOperation(value = "获取项目详细信息")
    @GetMapping("projectInfo")
    R projectInfo(String projectId){
        Project project = projectService.getById(projectId);
        ProjectInfoResponseVO projectInfoResponseVO = new ProjectInfoResponseVO();
        BeanUtils.copyProperties(project,projectInfoResponseVO);
        return R.ok().data("projectInfo",projectInfoResponseVO);
    }

    @ApiOperation(value = "(逻辑)删除项目")
    @DeleteMapping("projectDelete")
    R projectDelete(String projectId){
        Project project = projectService.getById(projectId);
        if (ObjectUtils.isEmpty(project)) {
            return R.error().message("未找到该项目信息，无法删除！");
        }
        boolean res = projectService.removeById(project);
        if (res){
            return R.ok().message("删除项目成功！");
        }
        return R.error().message("删除项目失败！");
    }
}