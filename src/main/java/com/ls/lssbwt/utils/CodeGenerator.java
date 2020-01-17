package com.ls.lssbwt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author lostsakura
 * @create 2020-01-13 13:47
 */
public class CodeGenerator {

    private static Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);

    public static void main(String[] args) {
        System.out.println("我被调用了");
        LOGGER.debug("CodeGenerator被调用了");
    }
}
