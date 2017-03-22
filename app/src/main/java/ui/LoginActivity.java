package ui;

import utils.SPUtils;
import utils.ToastUtils;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import bean.User;

import com.student.drs.R;

import conf.Constants;

import db.DrsDao;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText mPsd; // 密码
	private EditText mUserName;// 用户名
	private Button mBtn;

	private int requestCode = 0x01;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		initView();
		initDB();
	}

	private void initDB() {
		mDrsDao = new DrsDao(this);
		String userName = SPUtils.getString(this, Constants.KEY_USERNAME);
		//回显
		if(!TextUtils.isEmpty(userName)){
			mUserName.setText(userName);
		}
	}

	private void initView() {
		// 查找控件
		mUserName = (EditText) findViewById(R.id.username);
		mPsd = (EditText) findViewById(R.id.password);
		mBtn = (Button) findViewById(R.id.login_btn);

		// 设置监听的常见方法
		mBtn.setOnClickListener(this);
		mPsd.addTextChangedListener(loginWatcher);
		mUserName.addTextChangedListener(loginWatcher);

		findViewById(R.id.register).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 跳转
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);

				startActivityForResult(intent, requestCode);
			}
		});
	}

	// 输入监听
	TextWatcher loginWatcher = new TextWatcher() {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// EditText里的内容发生变化之时触发，
		}

		@Override
		public void afterTextChanged(Editable s) {
			// 按钮可点击的处理 解决无端的请求 必须带有帐号密码才请求数据
			if (!TextUtils.isEmpty(mPsd.getText())
					&& !TextUtils.isEmpty(mUserName.getText())) {
				mBtn.setEnabled(true);
			} else {
				mBtn.setEnabled(false);
			}
		}
	};
	
	private DrsDao mDrsDao;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			// 登录操作 查找数据库 匹配用户名和密码
			String username = mUserName.getText().toString().trim();
			String password = mPsd.getText().toString().trim();
			User user = mDrsDao.queryPsd(username);
			if(user==null){
				//登录失败
				ToastUtils.with(this).show("检查用户名或者密码是否正确");
				//作用是清除密码
				mPsd.getText().clear();
			}else {
				if(password.equals(user.password)){
					SPUtils.putString(LoginActivity.this, Constants.KEY_USERNAME,username);
					//跳转主页 结束登录页
					startActivity(new Intent(LoginActivity.this, MainActivity.class));
					finish();
				}else {
					ToastUtils.with(this).show("检查密码是否正确");
					
				}
			}
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == this.requestCode) {
			if (data != null) {
				//获取其他界面传值
				String userName = data.getStringExtra(Constants.IntentKEY.userName);
				if(!TextUtils.isEmpty(userName)){
					mUserName.setText(userName);
				}
			}
		}
	}
}
