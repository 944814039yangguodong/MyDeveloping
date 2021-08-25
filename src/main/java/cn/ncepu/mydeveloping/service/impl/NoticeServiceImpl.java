package cn.ncepu.mydeveloping.service.impl;

import cn.ncepu.mydeveloping.pojo.entity.Notice;
import cn.ncepu.mydeveloping.mapper.NoticeMapper;
import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.pojo.vo.NoticeListResponseVO;
import cn.ncepu.mydeveloping.pojo.vo.NoticeRequestVO;
import cn.ncepu.mydeveloping.service.NoticeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 *  公告服务实现类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    @Override
    public boolean updateById(Notice notice) {
        return SqlHelper.retBool(noticeMapper.updateById(notice));
    }

    @Override
    public boolean save(Notice notice) {
        return SqlHelper.retBool(noticeMapper.insert(notice));
    }

    @Override
    public boolean removeById(Serializable id) {
        return SqlHelper.retBool(noticeMapper.deleteById(id));
    }

    @Override
    public Notice getById(Serializable id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public Page<NoticeListResponseVO> noticePerPageByOrder(long current, long limit, String property, NoticeRequestVO noticeRequestVO) {
        Page<NoticeListResponseVO> infoPage = new Page<>(current,limit);
        Page<Notice> noticePage = new Page<>(current,limit);
        QueryWrapper<Notice> noticeQueryWrapper =
                new QueryWrapper<>();
        noticeQueryWrapper.select("notice_id","notice_name","gmt_create");
        if (!ObjectUtils.isEmpty(noticeRequestVO.getNoticeId())) {
            noticeQueryWrapper.like("notice_id",noticeRequestVO.getNoticeId());
        }
        if (!ObjectUtils.isEmpty(noticeRequestVO.getNoticeName())) {
            noticeQueryWrapper.like("notice_name",noticeRequestVO.getNoticeName());
        }
        if (!ObjectUtils.isEmpty(noticeRequestVO.getUserId())) {
            noticeQueryWrapper.like("user_id",noticeRequestVO.getUserId());
        }
//        if (!ObjectUtils.isEmpty(noticeRequestVO.getNoticeContent()))
//            noticeQueryWrapper.like("notice_content",noticeRequestVO.getNoticeContent());
//        if (!ObjectUtils.isEmpty(noticeRequestVO.getGmtCreate()))
//            noticeQueryWrapper.like("gmt_create",noticeRequestVO.getGmtCreate());
        noticeQueryWrapper.orderByDesc(property);
        noticeMapper.selectPage(noticePage,noticeQueryWrapper);
        BeanUtils.copyProperties(noticePage,infoPage);
        return infoPage;
    }

}
