package cn.ncepu.mydeveloping.service;

import cn.ncepu.mydeveloping.pojo.entity.Project;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-12
 */
public interface ProjectService extends IService<Project> {

    Page<Project> projectPerPageByOrder(long current, long limit, String property, Project project, String memberId);

    boolean updatePhase(String originalPhase, String originalStatus, String newPhase);

    boolean updateTop(Integer province, Integer nation);

    List<Project> selectTop(Integer top);
}
