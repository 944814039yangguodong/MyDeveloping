package cn.ncepu.mydeveloping.utils;

import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Guodong
 */
public class FileUtil {

    /**
     * 实现文件上传
     */
    public static String fileUploads(MultipartFile file, SimpleDateFormat sdf, String filePath, Logger log, String userFolder, String fileClass, String prefix) {
        // 得到格式化后的日期
        String format = sdf.format(new Date());
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        //获取无后缀的文件名
        String fileName2 = fileName.substring(0, fileName.lastIndexOf("."));
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")+ 1);
        //拼接后的文件名
        String newFileName = fileClass + "/" + userFolder + "/" + format + "/" +prefix + "-" + fileName2 + "." + suffix;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);
        System.out.println(filePath+newFileName);
        try {
            if (!dest.exists()) {
                //路径不存在需创建目录
                dest.mkdirs();
            }
            //上传的文件被保存了
            file.transferTo(dest);
            //打印日志
            log.info("上传成功，当前上传的文件保存在 {}", filePath + newFileName);
        } catch (IOException e) {
            //打印异常
            log.error(e.toString());
            e.printStackTrace();
            return null;
        }
        System.out.println(dest.getPath());
        return newFileName;
    }

    /**
     * 将文件下载功能抽离出来，通过文件的ID实现文件的下载
     * @param response 响应体对象
     */
    public static boolean download(HttpServletResponse response, File download){
        if (download.exists()) {
            BufferedInputStream inputStream = null;
            try {
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                String originalName = download.getName();
                //String simpleName = originalName.substring(originalName.lastIndexOf("-") + 1);
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(originalName, "UTF-8"));
                System.out.println(originalName);
                inputStream = new BufferedInputStream(new FileInputStream(download));
                response.setHeader("Content-Length", String.valueOf(inputStream.available()));
                BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                byte[] buf = new byte[1024 * 10];
                int len;
                while ((len = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert inputStream != null;
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    // 创建文件
    public static void createFile(String path, String fileName) {
        // path表示你所创建文件的路径, fileName为文件名
        File file = new File(path, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String saveFile(MultipartFile file, String filePath, Logger log) {
        try {
            File root = new File(filePath);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("HH-mm-ss");
            //年月日
            String date = format1.format(new Date());
            //时分秒
            String time = format2.format(new Date());
            File target = new File(root.getPath() + "/" + date + "/" +  time + "-" + getFileName(Objects.requireNonNull(file.getOriginalFilename())));
            if (!target.exists()) {
                target.mkdirs();
            }
            log.info(target.getAbsolutePath());
            file.transferTo(target);
            return target.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取文件名的方法
    public static String getFileName(String path){
        int index = path.lastIndexOf("\\");
        String fileName = path.substring(index+1);
        return fileName;
    }
}
