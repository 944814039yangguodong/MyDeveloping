package cn.ncepu.mydeveloping.consts;

import java.text.SimpleDateFormat;

/**
 * 常量类
 * @author Guodong
 */
public  class Constant {

    //用户类别
    /**
     * 校级负责人
     */
    public static final Integer SCHOOL=4;
    /**
     * 系级负责人
     */
    public static final Integer DEPARTMENT=3;
    /**
     * 审核老师
     */
    public static final Integer REVIEWER=2;
    /**
     * 普通老师
     */
    public static final Integer TEACHER=1;
    /**
     * 学生
     */
    public static final Integer STUDENT=0;

    //项目级别
    /**
     * 国家级
     */
    public static final Integer CLASS_NATION=2;
    /**
     * 省级
     */
    public static final Integer CLASS_PROVINCE=1;
    /**
     * 校级
     */
    public static final Integer CLASS_SCHOOL=0;

    //项目阶段
    /**
     * 已取消
     */
    public static final Integer PHASE_CANCEL=5;
    /**
     * 已结项
     */
    public static final Integer PHASE_OVER=4;
    /**
     * 延期结项
     */
    public static final Integer PHASE_EXTENSION=2;
    /**
     * 结项审核
     */
    public static final Integer PHASE_END=2;
    /**
     * 中期检查
     */
    public static final Integer PHASE_MIDTERM=1;
    /**
     * 立项申请
     */
    public static final Integer PHASE_START=0;

    //立项状态
    /**
     * 立项驳回
     */
    public static final Integer START_REJECT=2;
    /**
     * 立项成功
     */
    public static final Integer START_SUCCESS=1;
    /**
     * 等待审核
     */
    public static final Integer START_REVIEW=0;

    //中期状态
    /**
     * 中期审核未通过
     */
    public static final Integer MIDTERM_REJECT=3;
    /**
     * 中期审核通过
     */
    public static final Integer MIDTERM_SUCCESS=2;
    /**
     * 中期审核
     */
    public static final Integer MIDTERM_REVIEW=1;
    /**
     * 未中期审核
     */
    public static final Integer MIDTERM_WAITING=0;

    //结项状态
    /**
     * 结项审核未通过
     */
    public static final Integer END_REJECT=5;
    /**
     * 延期项目审核
     */
    public static final Integer END_EXTENSION_REVIEW=4;
    /**
     * 延期结项
     */
    public static final Integer END_EXTENSION_WAITING=3;
    /**
     * 结项审核通过
     */
    public static final Integer END_SUCCESS=2;
    /**
     * 结项审核
     */
    public static final Integer END_REVIEW=1;
    /**
     * 未结项审核
     */
    public static final Integer END_WAITING=0;


    //文件常量
    /**
     * Linux服务器和window本地ROOT_PATH
     */
    public static final String ROOT_PATH = "D:/myFiles/";
    //public static final String ROOT_PATH = "/myFiles/";
    /**
     * 时间格式SDF
     */
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    /**
     * 正式环境地址
     */
    public static final String IP = "http://82.157.174.105/";

}
