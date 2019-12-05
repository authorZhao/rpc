package com.git.sql.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;

/**
 * ORM映射工具类
 */
public class OrmUtil {
    private static final Logger logger = LoggerFactory.getLogger(OrmUtil.class);

    public static Object convertResultSet( ResultSet resultSet,Class clazz){
        logger.info("正在进行ORM映射{}",resultSet);
        return null;
    }
}
