package cn.ncepu.mydeveloping.config.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @author Guodong
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        this.setFieldValByName("createUserCode", StpUtil.getLoginId(), metaObject);
        this.setFieldValByName("modifiedUserCode", StpUtil.getLoginId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        if(!ObjectUtils.isEmpty(StpUtil.getLoginIdDefaultNull())) {
            this.setFieldValByName("modifiedUserCode", StpUtil.getLoginId(), metaObject);
        }
    }
}
