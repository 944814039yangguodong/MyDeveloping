package cn.ncepu.mydeveloping.service;

import cn.ncepu.mydeveloping.pojo.entity.Notice;
import cn.ncepu.mydeveloping.pojo.entity.Project;
import cn.ncepu.mydeveloping.pojo.entity.User;
import cn.ncepu.mydeveloping.pojo.vo.NoticeListResponseVO;
import cn.ncepu.mydeveloping.pojo.vo.NoticeRequestVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * <p>
 *  公告服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 根据id更新记录
     * @param notice 新记录，忽略null值的修改
     * @return 是否成功
     */
    @Override
    boolean updateById(Notice notice);

    /**
     * 新增纪录
     * @param notice 新纪录
     * @return 是否成功
     */
    @Override
    boolean save(Notice notice);

    /**
     * 逻辑删除纪录
     * @param id id
     * @return 是否成功
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 查询纪录
     * @param id id
     * @return 查询结果
     */
    @Override
    Notice getById(Serializable id);

    /**
     * 按照property属性的顺序，多条件查询，分页返回数据
     * @param current 当前页
     * @param limit 页面大小
     * @param property 排序属性
     * @param noticeRequestVO 条件
     * @return 查询结果公告简要信息列表
     */
    Page<NoticeListResponseVO> noticePerPageByOrder(long current, long limit, String property, NoticeRequestVO noticeRequestVO);
}
