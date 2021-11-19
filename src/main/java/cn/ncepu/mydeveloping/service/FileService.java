package cn.ncepu.mydeveloping.service;

import cn.ncepu.mydeveloping.pojo.entity.File;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-11-13
 */
public interface FileService extends IService<File> {

    /**
     * 多条件查询，分页返回数据
     * @param current 当前页
     * @param limit 页面大小
     * @param ownerName 主体名
     * @param fileType 文件类型
     * @param fileName 文件名
     * @return 查询结果
     */
    Page<File> filePerPage(long current, long limit, String ownerName, String fileType, String fileName);
}
