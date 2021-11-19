package cn.ncepu.mydeveloping.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 首页控制器
 * @author Guodong
 */

@Controller
@CrossOrigin
@ApiIgnore
public class PageController {

    /**
     * 基础页面控制
     */

    @RequestMapping("/")
    String index(){
        return "index";
    }
    //包含thymeleaf模板引擎时可以找名字叫index的(HTML5等)文件，将其直接显示在localhost/页面中
    //在前面加上@ResponseBody，会使得根目录localhost/下的页面出现index字符串

    @RequestMapping("/loginPage")
    String loginPage(){
        return "pages/front/login/loginPage";
    }

    @RequestMapping("/logout")
    String logout(){
        StpUtil.logout();
        return "index";
    }

    @RequestMapping("/api/notice/public/noticeInfoPage/{noticeId}")
    String noticeInfoPage(){
        return "pages/front/notice/noticeInfo";
    }

    @RequestMapping("/project/projectInfoPage/{projectId}")
    String projectInfoPage(){
        return "pages/front/project/projectInfo";
    }

    /**
     * 教师端页面控制
     */

    //公告操作

    @RequestMapping("/teacher/noticeSelectPage")
    String noticeSelectPage(){
        return "pages/teacher/notice/noticeSelect";
    }

    @RequestMapping("/teacher/noticeAddPage")
    String noticeAddPage(){
        return "pages/teacher/notice/noticeAdd";
    }

    @RequestMapping("/teacher/noticeUpdatePage/{noticeId}")
    String noticeUpdatePage(){
        return "pages/teacher/notice/noticeUpdate";
    }

    //用户操作

    @RequestMapping("/teacher/userInfoPage")
    String teacherUserInfoPage(){
        return "pages/teacher/user/teacherUserInfo";
    }

    @RequestMapping("/teacher/userPasswordPage")
    String teacherUserPasswordPage(){
        return "pages/teacher/user/teacherUserPassword";
    }

    @RequestMapping("/teacher/userSelectPage")
    String userSelectPage(){
        return "pages/teacher/user/userSelect";
    }

    @RequestMapping("/teacher/userAddPage")
    String userAddPage(){
        return "pages/teacher/user/userAdd";
    }

    @RequestMapping("/teacher/userUpdatePage/{userId}")
    String userUpdatePage(){
        return "pages/teacher/user/userUpdate";
    }

    //时间操作

    @RequestMapping("/teacher/timePublicPage")
    String timePublicPage(){
        return "pages/teacher/timePublic";
    }

    //项目操作

    @RequestMapping("/teacher/myProjectPage")
    String teacherMyProject(){
        return "pages/teacher/project/teacherMyProject";
    }

    @RequestMapping("/teacher/pstartPage")
    String teacherPstartPage(){
        return "pages/teacher/project/teacherPstart";
    }

    @RequestMapping("/teacher/processPage")
    String teacherProcessPage(){
        return "pages/teacher/project/teacherProcess";
    }

    @RequestMapping("/teacher/pmidtermPage")
    String teacherPmidtermPage(){
        return "pages/teacher/project/teacherPmidterm";
    }

    @RequestMapping("/teacher/promotePage")
    String teacherPromotePage(){
        return "pages/teacher/project/teacherPromote";
    }

    @RequestMapping("/teacher/pendPage")
    String teacherPendPage(){
        return "pages/teacher/project/teacherPend";
    }

    @RequestMapping("/teacher/pextensionPage")
    String teacherPextensionPage(){
        return "pages/teacher/project/teacherPextension";
    }

    //财务操作

    @RequestMapping("/teacher/moneyPage")
    String teacherMoneyPage(){
        return "pages/teacher/teacherMoney";
    }

    //文件操作

    @RequestMapping("/teacher/fileDownloadPage")
    String teacherFileDownloadPage(){
        return "pages/teacher/teacherFileDownload";
    }

    /**
     * 学生端页面控制
     */

    @RequestMapping("/student/myProjectPage")
    String studentMyProjectPage(){
        return "pages/student/myProject";
    }

    @RequestMapping("/student/pstartPage")
    String pstartPage(){
        return "pages/student/pstart";
    }

    @RequestMapping("/student/processPage")
    String processPage(){
        return "pages/student/process";
    }

    @RequestMapping("/student/pmidtermPage")
    String pmidtermPage(){
        return "pages/student/pmidterm";
    }

    @RequestMapping("/student/pendPage")
    String pendPage(){
        return "pages/student/pend";
    }

    @RequestMapping("/student/moneyPage")
    String moneyPage(){
        return "pages/student/money";
    }

    @RequestMapping("/student/userInfoPage")
    String userInfoPage(){
        return "pages/student/userInfo";
    }

    @RequestMapping("/student/userPasswordPage")
    String userPasswordPage(){
        return "pages/student/userPassword";
    }

    @RequestMapping("/student/memberChangePage/{projectId}")
    String memberChangePage(){
        return "pages/student/memberChange";
    }

}
