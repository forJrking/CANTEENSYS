package base;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {
	
	
	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		 //全局上下文
		 mContext = getApplicationContext();
	}

	public static Context getContext() {
		return mContext;
	}
}
