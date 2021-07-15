package cn.ncepu.mydeveloping.service.impl;

import cn.ncepu.mydeveloping.mapper.ProjectMapper;
import cn.ncepu.mydeveloping.pojo.entity.Project;
import cn.ncepu.mydeveloping.service.ProjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-12
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Resource
    ProjectMapper projectMapper;

    @Override
    public Page<Project> projectPerPageByOrder(long current, long limit, String property, Project project, String memberId) {
        Page<Project> projectPage = new Page<>(current,limit);
        QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(project.getProjectName()))
            projectQueryWrapper.like("project_name",project.getProjectName());
        if (!ObjectUtils.isEmpty(project.getDepartment()))
            projectQueryWrapper.eq("department",project.getDepartment());
        if (!ObjectUtils.isEmpty(project.getProjectType()))
            projectQueryWrapper.eq("project_type",project.getProjectType());
        if (!ObjectUtils.isEmpty(project.getProjectClass()))
            projectQueryWrapper.eq("project_class",project.getProjectClass());
        if (!ObjectUtils.isEmpty(project.getProjectPhase()))
            projectQueryWrapper.eq("project_phase",project.getProjectPhase());

        if(!ObjectUtils.isEmpty(memberId))
            projectQueryWrapper.eq("head_id",memberId)
                    .or().eq("second_id",memberId)
                    .or().eq("third_id",memberId)
                    .or().eq("fourth_id",memberId)
                    .or().eq("fifth_id",memberId)
                    .or().eq("teacher_id",memberId);

        projectQueryWrapper.orderByDesc(property);
        projectMapper.selectPage(projectPage,projectQueryWrapper);
        return projectPage;
    }

    @Override
    public boolean updatePhase(String originalPhase, String originalStatus, String newPhase) {
        //修改值
        Project project = new Project();
        project.setProjectPhase(newPhase);

        //修改条件
        UpdateWrapper<Project> projectUpdateWrapper = new UpdateWrapper<>();
        projectUpdateWrapper.eq("project_phase", originalPhase);
        switch (originalPhase) {
            case "立项申请":
                projectUpdateWrapper.eq("start_status", originalPhase);
                break;
            case "中期检查":
                projectUpdateWrapper.eq("midterm_status", originalPhase);
                break;
            case "结项审核":
                projectUpdateWrapper.eq("end_status", originalPhase);
                break;
            case "延期结项":
                projectUpdateWrapper.eq("end_status", originalPhase);
                break;
        }
        int res = projectMapper.update(project, projectUpdateWrapper);
        if(res==0)
            return false;
        else
            return true;
    }

    @Override
    public boolean updateTop(Integer province, Integer nation) {
        QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
        projectQueryWrapper.last("limit "+province)
                .eq("project_phase", "中期检查")
                .eq("midterm_status", "中期审核通过")
                .orderByDesc("midterm_grade");
        List<Project> projectList = projectMapper.selectList(projectQueryWrapper);
        for (Project temp : projectList) {
            temp.setProjectClass("省级");
            projectMapper.updateById(temp);
        }
        QueryWrapper<Project> projectQueryWrapper2 = new QueryWrapper<>();
        projectQueryWrapper2.last("limit "+nation)
                .eq("project_phase", "中期检查")
                .eq("midterm_status", "中期审核通过")
                .orderByDesc("midterm_grade");
        List<Project> projectList2 = projectMapper.selectList(projectQueryWrapper2);
        for (Project temp : projectList2) {
            temp.setProjectClass("国家级");
            projectMapper.updateById(temp);
        }
        return true;
    }

    @Override
    public List<Project> selectTop(Integer top) {

        QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
        projectQueryWrapper.last("limit "+top)
                .eq("project_phase", "中期检查")
                .eq("midterm_status", "中期审核通过")
                .orderByDesc("midterm_grade");
        return projectMapper.selectList(projectQueryWrapper);
    }
}
