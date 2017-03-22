package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DrsDB extends SQLiteOpenHelper {

	/** @Fields : 数据库名 */
	private static final String NAME_DB = "drs.db";

	/** @Fields versionCode:数据库版本 */
	private static int versionCode = 1;

	// 字段
	public static final String DRS_TB = "drs_tb";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String STUDENTID = "student_id";
	public static final String COLLEGE = "college";
	public static final String CLASSES = "classes";

	// 建表语句
	public static final String CREATE_TABLE_SQL = "CREATE TABLE " + DRS_TB
			+ " ( _ID INTEGER PRIMARY KEY AUTOINCREMENT , " + USERNAME
			+ " VARCHAR UNIQUE , " + PASSWORD + " VARCHAR, " + STUDENTID
			+ " VARCHAR, " + COLLEGE + " VARCHAR, " + CLASSES + " VARCHAR )";

	/**
	 * 构造函数
	 * 
	 * @param context
	 */
	public DrsDB(Context context) {
		super(context, NAME_DB, null, versionCode);

	}

	/**
	 * 创建数据库
	 * 
	 * @param db
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 执行一次
		db.execSQL(CREATE_TABLE_SQL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table drs_tb");
		onCreate(db);
	}

}
