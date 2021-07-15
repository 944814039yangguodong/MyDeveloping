package cn.ncepu.mydeveloping.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.ncepu.mydeveloping.pojo.entity.Time;
import cn.ncepu.mydeveloping.pojo.vo.TimeVO;
import cn.ncepu.mydeveloping.result.R;
import cn.ncepu.mydeveloping.service.TimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guodong
 * @since 2021-07-10
 */
@Api(value = "时间控制",tags={"时间控制"})
@RestController
@CrossOrigin
@RequestMapping("/api/time")
public class TimeController {
    @Resource
    TimeService timeService;

    @ApiOperation(value = "发布时间信息")
    @PostMapping("timeInsert")
    @SaCheckPermission("school-operation")
    R timePerInsert(TimeVO timeVO){
        Time timeNow = timeService.selectNow();
        if(!ObjectUtils.isEmpty(timeNow)){
            boolean res1 = timeService.removeById(timeNow);//将原有时间逻辑删除，再新增时间
            if (!res1){
                return R.error().message("删除原时间失败！");
            }
        }
        Time time = new Time();
        BeanUtils.copyProperties(timeVO, time);
        boolean res2 = timeService.save(time);
        if (res2){
            return R.ok().message("发布时间成功！");
        }
        return R.error().message("发布时间失败！");
    }

    @ApiOperation(value = "查询时间信息")
    @GetMapping("timeSelect")
    R timePerSelect(){
        Time time = timeService.selectNow();
        if(!ObjectUtils.isEmpty(time)){
            TimeVO timeVO = new TimeVO();
            BeanUtils.copyProperties(time, timeVO);
            return R.ok().data("time", timeVO);
        }
        else
            return R.error().message("尚未发布时间");
    }
}

