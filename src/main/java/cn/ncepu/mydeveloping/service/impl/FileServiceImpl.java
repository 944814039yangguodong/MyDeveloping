package cn.ncepu.mydeveloping.service.impl;

import cn.ncepu.mydeveloping.pojo.entity.File;
import cn.ncepu.mydeveloping.mapper.FileMapper;
import cn.ncepu.mydeveloping.service.FileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guodong
 * @since 2021-11-13
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Resource
    FileMapper fileMapper;

    @Override
    public Page<File> filePerPage(long current, long limit, String ownerName, String fileType, String fileName) {
        Page<File> filePage = new Page<>(current,limit);
        QueryWrapper<File> noticeQueryWrapper =
                new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(ownerName)) {
            noticeQueryWrapper.like("owner_name",ownerName);
        }
        if (!ObjectUtils.isEmpty(fileType)) {
            noticeQueryWrapper.like("file_type",fileType);
        }
        if (!ObjectUtils.isEmpty(fileName)) {
            noticeQueryWrapper.like("file_name",fileName);
        }
        noticeQueryWrapper.orderByDesc("gmt_create");
        fileMapper.selectPage(filePage,noticeQueryWrapper);
        return filePage;
    }
}
