package com.maple.dal.config.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Date;

@Data
@ConfigurationProperties(prefix = "mybatis-plus.intercept")
@Slf4j
public class CommonUpdateInterceptor implements InnerInterceptor {

    /**
     * 插入时间的字段名
     */
    private String insertTimeColumn;
    /**
     * 插入人的字段名
     */
    private String insertUserColumn;
    /**
     * 修改时间的字段名
     */
    private String modifyTimeColumn;
    /**
     * 修改人id的字段名
     */
    private String modifyUserColumn;

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        SqlCommandType sqlCommandType = ms.getSqlCommandType();
            if (SqlCommandType.INSERT == sqlCommandType) {
                reflectionSetField(parameter, insertTimeColumn, new Date());
                reflectionSetField(parameter, modifyTimeColumn, new Date());
            } else if (SqlCommandType.UPDATE == sqlCommandType) {
                reflectionSetField(parameter, modifyTimeColumn, new Date());
            }
        InnerInterceptor.super.beforeUpdate(executor, ms, parameter);
    }

    private void reflectionSetField(Object target, String fieldName, Object value) {
        try {
            FieldUtils.writeField(target, fieldName, value, true);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
            log.warn("写入属性失败, 原因为: " + e.getMessage());
        }
    }

}
