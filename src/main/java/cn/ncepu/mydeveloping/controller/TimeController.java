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
import java.util.Date;

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

    @ApiOperation(value = "发布立项时间信息")
    @PostMapping("timeStart")
    @SaCheckPermission("school-operation")
    R timeStart(Date startBegin, Date startOver){
        Time timeNow = timeService.selectNow();
        if(ObjectUtils.isEmpty(startBegin))
            return R.error().message("立项开始时间不可以为空！");
        if(ObjectUtils.isEmpty(startOver))
            return R.error().message("立项结束时间不可以为空！");
        timeNow.setStartBegin(startBegin);
        timeNow.setStartOver(startOver);
        boolean res2 = timeService.updateById(timeNow);
        if (res2){
            return R.ok().message("发布时间成功！");
        }
        return R.error().message("发布时间失败！");
    }

    @ApiOperation(value = "发布中期时间信息")
    @PostMapping("timeMidterm")
    @SaCheckPermission("school-operation")
    R timeMidterm(Date midtermBegin, Date midtermOver){
        Time timeNow = timeService.selectNow();
        if(ObjectUtils.isEmpty(midtermBegin))
            return R.error().message("中期开始时间不可以为空！");
        if(ObjectUtils.isEmpty(midtermOver))
            return R.error().message("中期结束时间不可以为空！");
        timeNow.setMidtermBegin(midtermBegin);
        timeNow.setMidtermOver(midtermOver);
        boolean res2 = timeService.updateById(timeNow);
        if (res2){
            return R.ok().message("发布时间成功！");
        }
        return R.error().message("发布时间失败！");
    }

    @ApiOperation(value = "发布结项时间信息")
    @PostMapping("timeEnd")
    @SaCheckPermission("school-operation")
    R timeEnd(Date endBegin, Date endOver){
        Time timeNow = timeService.selectNow();
        if(ObjectUtils.isEmpty(endBegin))
            return R.error().message("结项开始时间不可以为空！");
        if(ObjectUtils.isEmpty(endOver))
            return R.error().message("结项结束时间不可以为空！");
        timeNow.setEndBegin(endBegin);
        timeNow.setEndOver(endOver);
        boolean res2 = timeService.updateById(timeNow);
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

