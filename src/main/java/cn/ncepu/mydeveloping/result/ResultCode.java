package cn.ncepu.mydeveloping.result;

/**
 * 返回代码常量类
 */
public class ResultCode {

        public static final Integer SUCCESS = 200;			// 成功状态码
        public static final Integer ERROR = 500;			// 错误状态码
        public static final Integer CODE_WARNING = 300;			// 警告状态码
        public static final Integer CODE_NOT_JUR = 403;			// 无权限状态码
        public static final Integer CODE_NOT_LOGIN = 401;		// 未登录状态码
        public static final Integer CODE_INVALID_REQUEST = 402;	// 无效请求状态码
}
