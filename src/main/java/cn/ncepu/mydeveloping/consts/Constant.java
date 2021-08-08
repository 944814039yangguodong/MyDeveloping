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

    //项目类型
    public static final Integer TYPE_PRACTICE=2;//创业实践
    public static final Integer TYPE_TRAINING=1;//创业训练
    public static final Integer TYPE_INNOVATION=0;//创新训练

    //项目级别
    public static final Integer CLASS_NATION=2;//国家级
    public static final Integer CLASS_PROVINCE=1;//省级
    public static final Integer CLASS_SCHOOL=0;//校级

    //项目阶段
    public static final Integer PHASE_CANCEL=5;//已取消
    public static final Integer PHASE_OVER=4;//已结项
    public static final Integer PHASE_EXTENSION=3;//延期结项
    public static final Integer PHASE_END=2;//结项审核
    public static final Integer PHASE_MIDTERM=1;//中期检查
    public static final Integer PHASE_START=0;//立项申请

    //立项状态
    public static final Integer START_REJECT=2;//立项驳回
    public static final Integer START_SUCCESS=1;//立项成功
    public static final Integer START_REVIEW=0;//等待审核

    //中期状态
    public static final Integer MIDTERM_REJECT=3;//中期审核未通过
    public static final Integer MIDTERM_SUCCESS=2;//中期审核通过
    public static final Integer MIDTERM_REVIEW=1;//中期审核
    public static final Integer MIDTERM_WAITING=0;//未中期审核

    //结项状态
    public static final Integer END_REJECT=5;//结项审核未通过
    public static final Integer END_EXTENSION_REVIEW=4;//延期项目审核
    public static final Integer END_EXTENSION_WAITING=3;//延期结项
    public static final Integer END_SUCCESS=2;//结项审核通过
    public static final Integer END_REVIEW=1;//结项审核
    public static final Integer END_WAITING=0;//未结项审核

    //文件常量
    //public static final String ROOT_PATH = "/myFiles/";//Linux服务器
    public static final String ROOT_PATH = "D:/myFiles/";//window本地
    //public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//Linux服务器
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//window本地

    //正式环境地址
    public static final String IP = "http://82.157.174.105/";

}
