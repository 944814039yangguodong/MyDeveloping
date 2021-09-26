package cn.ncepu.mydeveloping.service;

import cn.ncepu.mydeveloping.pojo.entity.Project;
import cn.ncepu.mydeveloping.pojo.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  项目服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-12
 */
public interface ProjectService extends IService<Project> {

    /**
     * 根据id更新记录
     * @param project 新记录，忽略null值的修改
     * @return 是否成功
     */
    @Override
    boolean updateById(Project project);

    /**
     * 新增纪录
     * @param project 新纪录
     * @return 是否成功
     */
    @Override
    boolean save(Project project);

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
    Project getById(Serializable id);

    /**
     * 按property属性顺序，多条件，分页查询
     * @param current 当前页
     * @param limit 页面大小
     * @param property 排序属性
     * @param project 条件
     * @param memberId 参与用户
     * @return 查询结果项目列表
     */
    Page<Project> projectPerPageByOrder(long current, long limit, String property, Project project, String memberId);

    /**
     * 按property属性顺序，多条件，分页查询
     * @param current 当前页
     * @param limit 页面大小
     * @param property 排序属性
     * @param project 条件
     * @param headId 负责人
     * @return 查询结果项目列表
     */
    Page<Project> projectPerPageByOrderAndHead(long current, long limit, String property, Project project, String headId);

    /**
     * 更新项目阶段
     * @param originalPhase 原阶段
     * @param originalStatus 原阶段状态
     * @param newPhase 新阶段
     * @return 是否成功
     */
    boolean updatePhase(Integer originalPhase, Integer originalStatus, Integer newPhase);

    /**
     * 项目升级
     * @param province 省级项目数量
     * @param nation 国家级项目数量
     * @return 是否成功
     */
    boolean updateTop(Integer province, Integer nation);

    /**
     * 查询中期评分前几名的项目
     * @param top 前几名
     * @return 查询结果项目列表
     */
    List<Project> selectTop(Integer top);
}
