package com.media.video_meeting.page;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 分页插件
 *
 * ThreadLocal - 当前线程设置的参数，只能再当前线程获取
 *
 * @author ken
 *
 */
@Intercepts(@Signature(
		type = StatementHandler.class,
		method = "prepare",
		args = { Connection.class, Integer.class }
))
public class PagePlugin implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(PagePlugin.class);

	//ThreadLocal
	private static ThreadLocal<Page> threadLocal = new ThreadLocal<Page>();

	public static void startPage(Page page){
		threadLocal.set(page);
	}

	/**
	 * 拦截过程
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		// 1、获得目标对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaObject = getNoProxy(statementHandler);

		//2、获得sql语句
		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		String sql = boundSql.getSql().toLowerCase().trim();

		//3、判断是否需要分页  select * from student
		if(!sql.startsWith("select")){
			return invocation.proceed();
		}

		if(sql.indexOf("limit") != -1){
			return invocation.proceed();
		}

		Page page = threadLocal.get();
		if(page == null){
			return invocation.proceed();
		} else {
			threadLocal.set(null);
		}

		//4、说明需要分页的sql语句
		//计算出总条数  select count(*) from student where sex = ?
		Integer pageCount = getCount(sql, invocation, metaObject);

		//设置共有多少条记录
		page.setPageCount(pageCount);

		//5、开始进行分页查询 sql + " limit ?,?"
		if(sql.endsWith(";")){
			sql = sql.substring(0, sql.indexOf(";"));
		}

		sql += " limit ?,?";
		logger.info("真正要执行分页的sql语句：" + sql);

		//改变mybatis需要执行的sql语句
		metaObject.setValue("delegate.boundSql.sql", sql);

		//放行 - prepare
		PreparedStatement ps = (PreparedStatement) invocation.proceed();
		int pcount = ps.getParameterMetaData().getParameterCount();
		ps.setInt(pcount - 1, (page.getPage() - 1) * page.getPageSize());
		ps.setInt(pcount, page.getPageSize());

		return ps;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {

	}

	/**
	 * 剥离代理对象 - 获得真正的目标对象
	 *
	 * @param statementHandler
	 * @return
	 */
	private MetaObject getNoProxy(StatementHandler statementHandler) {
		// 剥离插件 - 有可能这个目标对象还是一个插件，要继续往下找到真正的目标对象操作 -
		// MetaObject - mybatis提供的一个帮助我们获得目标对象中的某个属性值的工具类
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		while (metaObject.hasGetter("h")) {
			Object object =  metaObject.getValue("h");
			metaObject = SystemMetaObject.forObject(object);
		}

		// 最终的目标对象 - RoutingStatementHandler
		if (metaObject.hasGetter("target")) {
			statementHandler = (StatementHandler) metaObject.getValue("target");
		}
		metaObject = SystemMetaObject.forObject(statementHandler);
		return metaObject;
	}

	/**
	 * 计算查询的总条数
	 * @return
	 */
	private Integer getCount(String sql, Invocation invocation, MetaObject metaObject){
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"));
		logger.info("计算总条数的sql语句：" + countSql);

		//获得数据的连接 执行总条数的sql语句
		Connection conn = (Connection) invocation.getArgs()[0];
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {

			ps = conn.prepareStatement(countSql);

			//设置参数 - paramentHandler
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(ps);

			Integer pageCount = 0;
			resultSet = ps.executeQuery();
			if(resultSet.next()){
				pageCount = resultSet.getInt(1);
			}

			return pageCount;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null){
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return 0;
	}
}
