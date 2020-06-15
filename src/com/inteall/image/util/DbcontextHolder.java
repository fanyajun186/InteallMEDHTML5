package com.inteall.image.util;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 
 * 工程中动态切换数据源,在执行sql语句之前必须制定用那个数据源
 * @author 韩明君  
 * @date 创建时间：2018年3月20日 上午9:57:18 
 * @version 1.0 
 * @parameter  
 */
public class DbcontextHolder extends AbstractRoutingDataSource {
	static Logger log = Logger.getLogger(DbcontextHolder.class.getName());
	 //线程安全的ThreadLocal
    public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    
    /**
     * 设置当前数据源
     * @param dbType
     */
    public static void setDbType(String dbType){
    	log.info("setDbType(String)");
        contextHolder.set(dbType);
    }
    /**
     * 获得当前数据源
     * @return
     */
    public static String getDbType(){
    	log.info("getDbType()");
        String dbType = (String)contextHolder.get();
        return dbType;
    }
    /**
     *清除上下文
     *
     */
    public void clearContext(){
    	log.info("clearContext()");
        contextHolder.remove();
    }
    /**
     * 建立动态数据源类，
     * 注意，这个类必须继承AbstractRoutingDataSource，
     * 且实现方法 determineCurrentLookupKey
     * 框架在每次调用数据源时会先调用这个方法，以便知道使用哪个数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
    	log.info("determineCurrentLookupKey()");
        return DbcontextHolder.getDbType();
    }
    
}
