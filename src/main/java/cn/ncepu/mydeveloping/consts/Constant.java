package cn.ncepu.mydeveloping.consts;

import java.text.SimpleDateFormat;

/**
 * 常量类
 */
public  class Constant {

    //用户类别
    public static final Integer SCHOOL=4;//校级负责人
    public static final Integer DEPARTMENT=3;//系级负责人
    public static final Integer REVIEWER=2;//审核老师
    public static final Integer TEACHER=1;//普通老师
    public static final Integer STUDENT=0;//学生

    //文件常量
    public static final String ROOT_PATH = "/myFiles/";
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH-mm-ssss");

}
