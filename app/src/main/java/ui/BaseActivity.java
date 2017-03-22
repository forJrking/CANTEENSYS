package ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());
		initView();
		initListener();
		initData();
	}

	public abstract int getLayoutResId();
	public abstract void initView();
	public abstract void initData();
	public abstract void initListener();
	
}
