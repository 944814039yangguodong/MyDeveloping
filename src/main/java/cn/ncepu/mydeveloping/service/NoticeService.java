package cn.ncepu.mydeveloping.service;

import cn.ncepu.mydeveloping.pojo.entity.Notice;
import cn.ncepu.mydeveloping.pojo.vo.NoticeListResponseVO;
import cn.ncepu.mydeveloping.pojo.vo.NoticeRequestVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
public interface NoticeService extends IService<Notice> {

    Page<NoticeListResponseVO> noticePerPageByOrder(long current, long limit, String property, NoticeRequestVO noticeRequestVO);
}
