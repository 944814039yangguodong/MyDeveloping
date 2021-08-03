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

    boolean updateById(Project project);

    boolean save(Project project);

    Page<Project> projectPerPageByOrder(long current, long limit, String property, Project project, String memberId);

    boolean updatePhase(Integer originalPhase, Integer originalStatus, Integer newPhase);

    boolean updateTop(Integer province, Integer nation);

    List<Project> selectTop(Integer top);
}
