package cn.ncepu.mydeveloping.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.ncepu.mydeveloping.pojo.entity.Notice;
import cn.ncepu.mydeveloping.pojo.vo.NoticeAddRequestVO;
import cn.ncepu.mydeveloping.pojo.vo.NoticeListResponseVO;
import cn.ncepu.mydeveloping.pojo.vo.NoticeRequestVO;
import cn.ncepu.mydeveloping.pojo.vo.NoticeInfoResponseVO;
import cn.ncepu.mydeveloping.result.R;
import cn.ncepu.mydeveloping.service.NoticeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

import static cn.ncepu.mydeveloping.consts.Constant.*;
import static cn.ncepu.mydeveloping.consts.FieldNameConstants.*;
import static cn.ncepu.mydeveloping.utils.FileUtil.fileUploads;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
@Api(value="公告控制",tags={"公告控制"})
@RestController
@CrossOrigin
@RequestMapping("/api/notice")
public class NoticeController {
    @Resource
    NoticeService noticeService;

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @ApiOperation(value = "系级以上负责人发布公告")
    @PostMapping("noticeInsert")
    @SaCheckPermission("department-operation")
    R noticeInsert(NoticeAddRequestVO noticeAddRequestVO, MultipartFile fileOne, MultipartFile fileTwo, MultipartFile fileThree) {
        Notice notice=new Notice();
        notice.setUserId((String) StpUtil.getLoginId());
        notice.setNoticeName(noticeAddRequestVO.getNoticeName());
        notice.setNoticeContent(noticeAddRequestVO.getNoticeContent());
        if (ObjectUtils.isEmpty(notice.getNoticeName())) {
            return R.error().message("公告标题不得为空！");
        }
        if (ObjectUtils.isEmpty(notice.getNoticeContent())) {
            return R.error().message("公告内容不得为空！");
        }
        long count = 0;
        //自动设置初始浏览量为0
        notice.setNoticeReadCount(count);
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "notice";
        if(fileOne!=null){
            String newFileName =ROOT_PATH + fileUploads(fileOne,SDF,ROOT_PATH,logger,userFolder,fileClass,"附件1");
            notice.setNoticeAttachmentOne(newFileName);
        }
        if(fileTwo!=null){
            String newFileName =ROOT_PATH + fileUploads(fileTwo,SDF,ROOT_PATH,logger,userFolder,fileClass,"附件2");
            notice.setNoticeAttachmentTwo(newFileName);
        }
        if(fileThree!=null){
            String newFileName =ROOT_PATH + fileUploads(fileThree,SDF,ROOT_PATH,logger,userFolder,fileClass,"附件3");
            notice.setNoticeAttachmentThree(newFileName);
        }
        boolean res = noticeService.save(notice);
        if (res){
            return R.ok().message("新增公告成功！");
        }
        return R.error().message("新增公告失败！");
    }

    @ApiOperation(value = "系级以上负责人(逻辑)删除公告")
    @DeleteMapping("noticeDelete")
    @SaCheckPermission("department-operation")
    R noticeDelete(Integer noticeId){
        if (ObjectUtils.isEmpty(noticeService.getById(noticeId))) {
            return R.error().message("未找到该公告信息，无法删除！");
        }
        boolean res = noticeService.removeById(noticeId);
        if (res){
            return R.ok().message("删除公告信息成功！");
        }
        return R.error().message("删除公告信息失败！");
    }

    @ApiOperation(value = "系级以上负责人修改公告")
    @PostMapping("noticeModify")
    @SaCheckPermission("department-operation")
    R noticeModify(Integer noticeId, NoticeAddRequestVO noticeAddRequestVO, MultipartFile fileOne, MultipartFile fileTwo, MultipartFile fileThree){
        Notice notice = noticeService.getById(noticeId);
        if (ObjectUtils.isEmpty(notice)){
            return R.error().message("未找到该公告信息，无法修改！");
        }
        if(noticeAddRequestVO.getNoticeName()!=null) {
            notice.setNoticeName(noticeAddRequestVO.getNoticeName());
        }
        if(noticeAddRequestVO.getNoticeContent()!=null) {
            notice.setNoticeContent(noticeAddRequestVO.getNoticeContent());
        }
        //文件上传
        String userFolder = (String) StpUtil.getLoginId();
        String fileClass= "notice";
        if(fileOne!=null){
            String newFileName =ROOT_PATH + fileUploads(fileOne,SDF,ROOT_PATH,logger,userFolder,fileClass,"附件1");
            notice.setNoticeAttachmentOne(newFileName);
        }
        if(fileTwo!=null){
            String newFileName =ROOT_PATH + fileUploads(fileTwo,SDF,ROOT_PATH,logger,userFolder,fileClass,"附件2");
            notice.setNoticeAttachmentTwo(newFileName);
        }
        if(fileThree!=null){
            String newFileName =ROOT_PATH + fileUploads(fileThree,SDF,ROOT_PATH,logger,userFolder,fileClass,"附件3");
            notice.setNoticeAttachmentThree(newFileName);
        }
        boolean res = noticeService.updateById(notice);
        if (res){
            return R.ok().message("修改公告信息成功！");
        }
        return R.error().message("修改公告信息失败！");
    }

    @ApiOperation(value = "属性排序分页模糊查询公告简要信息")
    @GetMapping("/public/noticeSelectPage")
    R noticeSelectPage(long current, long limit, String property, NoticeRequestVO noticeRequestVO){
        if(GMT_CREATE.equals(property)|| NOTICE_NAME.equals(property)|| NOTICE_CONTENT.equals(property)){
            Page<NoticeListResponseVO> noticePage =
                    noticeService.noticePerPageByOrder(current,limit,property,noticeRequestVO);
            //总记录数
            long total = noticePage.getTotal();
            //数据list集合
            List<NoticeListResponseVO> records = noticePage.getRecords();
            return R.ok().data("total",total).data("rows",records);
        }
        return R.error().message("不允许以属性:"+property+"排序，仅允许以属性‘gmt_create’,'notice_name','notice_content'排序");
    }

    @ApiOperation("获取单个公告内容详情")
    @GetMapping("/public/getNotice")
    public R getNotice(Integer noticeId){
        Notice notice = noticeService.getById(noticeId);
        if(ObjectUtils.isEmpty(notice)){
            return R.error().message("该公告不存在");
        }
        NoticeInfoResponseVO noticeInfoResponseVO = new NoticeInfoResponseVO();
        BeanUtils.copyProperties(notice, noticeInfoResponseVO);
        //每次获取公告内容详情阅读数加一
        notice.setNoticeReadCount(notice.getNoticeReadCount()+1);
        noticeService.updateById(notice);
        return R.ok().data("noticeInfo", noticeInfoResponseVO);
    }
}

