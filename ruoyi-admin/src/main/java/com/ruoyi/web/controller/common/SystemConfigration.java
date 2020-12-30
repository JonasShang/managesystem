package com.ruoyi.web.controller.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.SystemProperties;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class SystemConfigration {
    private static ConcurrentMap g_config = new ConcurrentHashMap();

    public static Object getConfig(String key) {
        return g_config.get(key);
    }

    public static void setConfig(String key, Object value) {
        g_config.put(key, value);
    }

    /**
     * 获得应用基准目录
     * @return
     */
    public static String getHomePath() {
        return getPath("BCIC_HOME");
    }

    public static String getPath(String envSetting) {
        /**
         * 如果在系统环境变量中有定义优先使用此目录
         */
        String app_Home = SystemProperties.get(envSetting);
        if (StringUtils.hasText(app_Home)) {
            return app_Home;
        }

        /**
         * 被封装成jar部署运行了
         */
        String path = SystemConfigration.class.getResource("").getPath();
        if (null != path && path.contains(".jar!/")) {
            log.debug("****************current path:{}", path);
            return new File("").getAbsolutePath();
        }
        else if(null != path &&path.contains("/WEB-INF/classes/")){
            return path.substring(0,path.lastIndexOf("/WEB-INF/classes/"));
        }

        /**
         * 开发运行时，防止程序目录下出现过多目录侵入，将log等都归集放在domain目录下
         */
        return new File("").getAbsolutePath()+"/domain";
    }
}
