package cn.ncepu.mydeveloping.controller;

import cn.ncepu.mydeveloping.pojo.entity.Notice;
import cn.ncepu.mydeveloping.pojo.entity.Project;
import cn.ncepu.mydeveloping.result.R;
import cn.ncepu.mydeveloping.service.FileService;
import cn.ncepu.mydeveloping.service.NoticeService;
import cn.ncepu.mydeveloping.service.ProjectService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static cn.ncepu.mydeveloping.consts.Constant.*;
import static cn.ncepu.mydeveloping.consts.FileConstants.*;
import static cn.ncepu.mydeveloping.consts.SuffixConstants.*;
import static cn.ncepu.mydeveloping.utils.FileUtil.deleteFile;
import static cn.ncepu.mydeveloping.utils.FileUtil.download;

/**
 * @author Guodong
 */
@RestController
@RequestMapping("/api/file")
@Api(value="文件操作",tags={"文件操作"})
public class FileController {

    @Resource
    FileService fileService;

    @Resource
    NoticeService noticeService;

    @Resource
    ProjectService projectService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @ApiOperation("多条件查询所有文件")
    @GetMapping("/selectFile")
    public R selectFile(@RequestParam long current, @RequestParam long limit, String ownerName, String fileType, String fileName) {
        Page<cn.ncepu.mydeveloping.pojo.entity.File> filePage = fileService.filePerPage(current,limit,ownerName,fileType,fileName);
        //总记录数
        long total = filePage.getTotal();
        //数据list集合
        List<cn.ncepu.mydeveloping.pojo.entity.File> records = filePage.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("删除文件")
    @DeleteMapping("/removeFile")
    public R removeFile(String fileId) {
        cn.ncepu.mydeveloping.pojo.entity.File file = fileService.getById(fileId);
        if(!deleteFile(file.getFilePath())){
            return R.error().message("删除文件" + file.getFilePath() + "失败！");
        }
        if(!fileService.removeById(fileId)){
            return R.error().message("删除文件表" + fileId + "失败！");
        }
        if(Objects.equals(file.getFileType(), NOTICE)){
            Notice notice = noticeService.getByFile(file.getFilePath());
            Notice newNotice = new Notice();
            newNotice.setNoticeId(notice.getNoticeId());
            if (NOTICE_A_1.equals(file.getFileName())) {
                newNotice.setNoticeAttachmentOne("");
            }else if(NOTICE_A_2.equals(file.getFileName())){
                newNotice.setNoticeAttachmentTwo("");
            }else if(NOTICE_A_3.equals(file.getFileName())){
                newNotice.setNoticeAttachmentThree("");
            }
            noticeService.updateById(newNotice);
        }else if(Objects.equals(file.getFileType(), PROJECT)){
            Project project = projectService.getByFile(file.getFilePath());
            if (REIMBURSEMENT_TABLE.equals(file.getFileName())) {
                project.setReimbursementTable("");
            }else if(START_APPLICATION.equals(file.getFileName())){
                project.setStartApplication("");
            }else if(START_PPT.equals(file.getFileName())){
                project.setStartPpt("");
            }else if(START_ADDITIONAL_FILE.equals(file.getFileName())){
                project.setStartAdditionalFile("");
            }else if(MIDTERM_REPORT.equals(file.getFileName())){
                project.setMidtermReport("");
            }else if(MIDTERM_CHANGE.equals(file.getFileName())){
                project.setMidtermChange("");
            }else if(MIDTERM_PPT.equals(file.getFileName())){
                project.setMidtermPpt("");
            }else if(MIDTERM_ADDITIONAL_FILE.equals(file.getFileName())){
                project.setMidtermAdditionalFile("");
            }else if(END_REPORT.equals(file.getFileName())){
                project.setEndReport("");
            }else if(CONCLUSION_REPORT.equals(file.getFileName())){
                project.setConclusionReport("");
            }else if(OUTCOME_TABLE.equals(file.getFileName())){
                project.setOutcomeTable("");
            }else if(OUTCOME_FILE.equals(file.getFileName())){
                project.setOutcomeFile("");
            }else if(PERSONAL_SUMMARY.equals(file.getFileName())){
                project.setPersonalSummary("");
            }else if(END_PPT.equals(file.getFileName())){
                project.setEndPpt("");
            }else if(EXTENSION_APPLICATION.equals(file.getFileName())){
                project.setExtensionApplication("");
            }else if(END_ADDITIONAL_FILE.equals(file.getFileName())){
                project.setEndAdditionalFile("");
            }
            projectService.updateById(project);
        }
        return R.ok().message("删除"+file.getFileName()+"成功！");
    }

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R uploadFile(@RequestParam("file") MultipartFile file) {
        long startTime = System.currentTimeMillis();
        String originalFilename = file.getOriginalFilename();
        logger.info("fileName：" + originalFilename);
        assert originalFilename != null;
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(lastIndexOf + 1);
        SimpleDateFormat sdf = SDF;
        sdf.format(new Date());
        String path = ROOT_PATH + sdf.format(new Date()) + " " + originalFilename;
        //文件类型判断 doc,docx,jpg,png,xls
        logger.info("截取文件名类型:{}", fileType);
        if (fileType.equals(JPG) || fileType.equals(PNG) || fileType.equals(DOX) || fileType.equals(DOCX) || fileType.equals(XLS)|| fileType.equals(TXT)|| fileType.equals(PPT)|| fileType.equals(PDF)) {
            File newFile = new File(path);
            try {
                // 路径不存在需创建目录
                if (!newFile.exists()) {
                    boolean res = newFile.mkdirs();
                    if(!res){
                        return R.error().message("创建文件夹失败！");
                    }
                }
                file.transferTo(newFile);
                long endTime = System.currentTimeMillis();
                logger.info("上传成功，当前上传的文件保存在:"+ path +";file.Transto runnning time:" + (endTime - startTime) + "ms");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return R.error().message("不允许该" + fileType + "文件类型上传");
        }
        return R.ok().message("上传文件成功！当前上传的文件保存在:"+ path);
    }

    @ApiOperation("文件下载")
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response,@RequestParam("path") String path) {
        File file = new File(path);
        download(response, file);
    }
}