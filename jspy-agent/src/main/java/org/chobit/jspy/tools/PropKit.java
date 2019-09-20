package org.chobit.jspy.tools;


import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.RejectedExecutionException;

/**
 * 配置文件操作工具类
 */
public abstract class PropKit {

    private static final Map m = new Hashtable();

    /**
     * 加载配置文件
     *
     * @param propertyFileName 配置文件名称，通常使用“/name.properties”这样的值
     * @return 配置信息
     */
    static Properties load(String propertyFileName) {
        Properties p = new Properties();
        InputStream in = null;
        try {
            in = PropKit.class.getResourceAsStream(propertyFileName);
            if (in != null) {
                p.load(in);
                m.putAll(p);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return p;
    }

    static String getProp(String key, String defaultValue) {
        Object oval = m.get(key);
        String sval = (oval instanceof String) ? (String) oval : null;
        return (sval == null) ? defaultValue : sval;
    }

    static String getProp(String key) {
        return getProp(key, null);
    }

    static Object setProp(String key, String value) {
        return m.put(key, value);
    }

    static Integer getInt(String key, Integer defaultValue) {
        String value = getProp(key);
        return (value != null) ? Integer.parseInt(value) : defaultValue;
    }

    static Integer getInt(String key) {
        return getInt(key, 0);
    }

    static Double getDouble(String key, Double defaultValue) {
        String value = getProp(key);
        return (value != null) ? Double.parseDouble(value) : defaultValue;
    }

    static Double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    static Boolean getBoolean(String key, Boolean defaultValue) {
        String value = getProp(key);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

    static Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    private PropKit() {
        throw new RejectedExecutionException("private constructor, cannot be called.");
    }

}
