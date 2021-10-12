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
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        //获取无后缀的文件名
        String fileName2 = fileName.substring(0, fileName.lastIndexOf("."));
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")+ 1);
        //拼接后的文件名:文件类型/用户ID/当前时间/前缀-文件名.后缀
        String newFileName = fileClass + "/" + userFolder + "/" + sdf.format(new Date()) + "/" +prefix + "-" + fileName2 + "." + suffix;
        //得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);
        //得到上级目录
        File destParent = new File(filePath + newFileName).getParentFile();
        System.out.println(filePath+newFileName);
        try {
            if (!destParent.exists()) {
                //上级路径不存在需创建目录
                boolean res = destParent.mkdirs();
                if(!res){
                    log.info("创建文件夹失败");
                    return null;
                }
            }
            if(!dest.exists()){
                //创建文件
                boolean res = dest.createNewFile();
                if(!res){
                    log.info("创建文件失败");
                    return null;
                }
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
                boolean res = target.mkdirs();
                if(!res){
                    return null;
                }
            }
            log.info(target.getAbsolutePath());
            file.transferTo(target);
            return target.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件名的方法
     */
    public static String getFileName(String path){
        int index = path.lastIndexOf("\\");
        return path.substring(index+1);
    }

    /**
     * 删除文件，可以是文件或文件夹
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }
}
