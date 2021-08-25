package cn.ncepu.mydeveloping.controller;

import cn.ncepu.mydeveloping.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.ncepu.mydeveloping.consts.Constant.*;
import static cn.ncepu.mydeveloping.utils.FileUtil.download;

/**
 * @author Guodong
 */
@RestController
@RequestMapping("/api/file")
@Api(value="文件操作",tags={"文件操作"})
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R uploadFile(@RequestParam("file") MultipartFile file) {
        long startTime = System.currentTimeMillis();
        String originalFilename = file.getOriginalFilename();
        logger.info("fileName：" + originalFilename);
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(lastIndexOf + 1);
        SimpleDateFormat sdf = SDF;
        sdf.format(new Date());
        String path = ROOT_PATH + sdf.format(new Date()) + " " + originalFilename;
        //文件类型判断 doc,docx,jpg,png,xls
        logger.info("截取文件名类型:{}", fileType);
        if (fileType.equals("jpg") || fileType.equals("png") || fileType.equals("dox") || fileType.equals("docx") || fileType.equals("xls")|| fileType.equals("txt")|| fileType.equals("ppt")|| fileType.equals("pdf")) {
            File newFile = new File(path);
            try {
                // 路径不存在需创建目录
                if (!newFile.exists()) {
                    newFile.mkdirs();
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