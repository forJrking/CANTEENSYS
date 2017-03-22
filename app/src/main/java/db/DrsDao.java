package db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import bean.User;

public class DrsDao {

	private DrsDB mHelper;

	public DrsDao(Context context) {

		mHelper = new DrsDB(context);
	}

	/**
	 * @Description 添加数据库
	 * @param bean
	 *            用户数据
	 * @return -1 失败
	 */

	public boolean add(User bean) {
		SQLiteDatabase db = mHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DrsDB.USERNAME, bean.username);
		values.put(DrsDB.PASSWORD, bean.password);
		values.put(DrsDB.STUDENTID, bean.studentId);
		values.put(DrsDB.COLLEGE, bean.college);
		values.put(DrsDB.CLASSES, bean.classes);
		long result = db.insert(DrsDB.DRS_TB, null, values);
		db.close();

		return result >= 0;
	}

	/**
	 * @param number
	 *            学号
	 * @return > 0 删除成功
	 */

	public boolean remove(String studentid) {

		SQLiteDatabase db = mHelper.getWritableDatabase();

		int result = db.delete(DrsDB.DRS_TB, DrsDB.STUDENTID + "=?",
				new String[] { studentid });
		db.close();
		return result > 0;

	}

	/**
	 * @Description
	 * @param number
	 * @return > 0 删除成功
	 */

	public boolean remove(User bean) {
		return remove(bean.getStudentId());
	}

	/**
	 * @Description 更新操作
	 * @param bean
	 * @return > 0 更新了
	 */

	public boolean update(User bean) {

		SQLiteDatabase db = mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DrsDB.USERNAME, bean.username);
		values.put(DrsDB.PASSWORD, bean.password);
		values.put(DrsDB.STUDENTID, bean.studentId);
		values.put(DrsDB.COLLEGE, bean.college);
		values.put(DrsDB.CLASSES, bean.classes);

		int update = db.update(DrsDB.DRS_TB, values, DrsDB.STUDENTID + "=?",
				new String[] { bean.getStudentId() });
		db.close();
		return update > 0;
	}

	/**
	 * @Description 查询数据库
	 * @return bean的集合
	 */

	public List<User> queryDatas() {
		List<User> beans = new ArrayList<User>();

		SQLiteDatabase db = mHelper.getReadableDatabase();

		Cursor cursor = db.query(DrsDB.DRS_TB,
				new String[] { DrsDB.USERNAME, DrsDB.PASSWORD, DrsDB.STUDENTID,
						DrsDB.COLLEGE, DrsDB.CLASSES }, null, null, null, null,
				null);

		while (cursor.moveToNext()) {
			beans.add(new User(cursor.getString(0), cursor.getString(1), cursor
					.getString(2), cursor.getString(3), cursor.getString(4)));
		}

		if (cursor != null)
			cursor.close();

		db.close();
		return beans;
	}

	// 查询数据库
	public User queryPsd(String userName) {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		String[] projection = { DrsDB.USERNAME, DrsDB.PASSWORD,
				DrsDB.STUDENTID, DrsDB.COLLEGE, DrsDB.CLASSES };

		Cursor cursor = db.query(DrsDB.DRS_TB, projection, DrsDB.USERNAME
				+ "=?", new String[] { userName }, null, null, null);

		User user = null;

		if (cursor.moveToNext()) {
			user = new User(cursor.getString(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4));
		}
		if (cursor != null)
			cursor.close();
		db.close();

		return user;
	}
	// /**
	// * @Description 分页查询
	// * @param pageSize
	// * @param offset
	// * @return
	// */
	//
	// public List<BlackBean> queryDatas(int pageSize, int offset) {
	//
	// // blackBeans.clear(); 这样用会出错误的
	// List<BlackBean> blackBeans = new ArrayList<BlackBean>();
	// SQLiteDatabase db = mHelper.getReadableDatabase();
	//
	// String sql = "select * from " + BlackDB.BLACK_TB + " limit " + pageSize
	// + " offset " + offset;
	//
	// Cursor cursor = db.rawQuery(sql, null);
	//
	// while (cursor.moveToNext()) {
	// // 重要
	// String number = cursor.getString(cursor
	// .getColumnIndex(BlackDB.NUMBER));
	// int model = cursor.getInt(cursor.getColumnIndex(BlackDB.MODEL));
	// blackBeans.add(new BlackBean(number, model));
	// }
	// if (cursor != null)
	// cursor.close();
	// db.close();
	// return blackBeans;
	// }
}
