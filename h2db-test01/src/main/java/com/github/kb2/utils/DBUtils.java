package com.github.kb2.utils;

import java.util.List;

import jp.sf.amateras.mirage.ClasspathSqlResource;
import jp.sf.amateras.mirage.SqlManager;
import jp.sf.amateras.mirage.SqlResource;
import jp.sf.amateras.mirage.session.Session;
import jp.sf.amateras.mirage.session.SessionFactory;

public class DBUtils {

	/**
	 * @see DBUtil.Class#getList(Class<T> clazz, String sqlFilePath, Object param)
	 */
	public static <T> List<T> getResultList(Class<T> clazz, String sqlFilePath) {
		return getResultList(clazz, sqlFilePath, null);
	}
	
	/**
	 * SELECT実行結果を指定したクラスのオブジェクトに変換して取得する
	 * 
	 * @param clazz 
	 * @param sqlFilePath
	 * @param param MapかBeanクラス
	 * @return
	 */
	public static <T> List<T> getResultList(Class<T> clazz, String sqlFilePath, Object param) {
		// セッション設定(明示的な指定がなければ、クラスパスからjdbc.propertiesを読み込む)
		Session session = SessionFactory.getSession();
		SqlManager sqlManager = session.getSqlManager();
		session.begin();

		List<T> results;
		try {
			
			// SELECT実行
			SqlResource selecSql = new ClasspathSqlResource(sqlFilePath);
			results = sqlManager.getResultList(clazz, selecSql, param);

			session.commit();
			
			return results;
		} catch (Exception e) {

			// 実行失敗
			session.rollback();
			throw new RuntimeException(e);
		} finally {
			session.release();
		}
	}
	
	/**
	 * 更新
	 * 
	 * @param sqlFilePath
	 */
	public static void executeUpdate(String sqlFilePath) {
		Session session = SessionFactory.getSession();
		SqlManager sqlManager = session.getSqlManager();
		session.begin();

		try {
			SqlResource sql = new ClasspathSqlResource(sqlFilePath);
			sqlManager.executeUpdate(sql);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.release();
		}
	}
}
