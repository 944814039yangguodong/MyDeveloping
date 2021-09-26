package cn.ncepu.mydeveloping.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Guodong
 */
public class StrUtil {
    /**
     * 中文匹配
     */
    public static final Pattern PATTERN = Pattern.compile("[\u4e00-\u9fa5]");
    /**
     * 判断是否包含中文
     */
    public static boolean isChineseIncluded(String str){
        Matcher m = PATTERN.matcher(str);
        return m.find();
    }
}
