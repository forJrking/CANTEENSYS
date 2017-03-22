package ui;

import utils.ToastUtils;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import bean.User;

import com.student.drs.R;

import conf.Constants;

import db.DrsDao;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	private Button mRegister;
	private EditText mClasses;
	private EditText mCollege;
	private EditText mStudnetId;
	private EditText mPassword;
	private EditText mUserName;
	private DrsDao mDrsDao;
	int resultCode = 0x02;
	
	@Override
	public int getLayoutResId() {
		return R.layout.register_activity;
	}

	@Override
	public void initView() {
		mUserName = (EditText) findViewById(R.id.username);
		mPassword = (EditText) findViewById(R.id.password);
		mStudnetId = (EditText) findViewById(R.id.uid_et);
		mCollege = (EditText) findViewById(R.id.college_et);
		mClasses = (EditText) findViewById(R.id.classes_et);
		mRegister = (Button) findViewById(R.id.register_btn);
	}

	@Override
	public void initData() {
		mDrsDao = new DrsDao(this);
	}

	@Override
	public void initListener() {
		findViewById(R.id.goback_iv).setOnClickListener(this);
		mRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.goback_iv:
			onBackPressed();
			break;
		case R.id.register_btn:
			toRegister();
			break;
		}
	}
	
	private void toRegister() {
		String userName = mUserName.getText().toString();
		String password = mPassword.getText().toString();
		String studnetId = mStudnetId.getText().toString();
		String college = mCollege.getText().toString();
		String classes = mClasses.getText().toString();
		
		if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)||
				TextUtils.isEmpty(studnetId)||TextUtils.isEmpty(college)||TextUtils.isEmpty(classes)){
			ToastUtils.with(this).show("信息不完善不允许注册");
		}else {
			boolean successs = mDrsDao.add(new User(userName, password, studnetId, college, classes));
			if(successs){
				ToastUtils.with(this).show("注册成功！");
				
				Intent data = new Intent();
				data.putExtra(Constants.IntentKEY.userName, userName);
				setResult(resultCode, data );
				finish();
			}else {
				ToastUtils.with(this).show("注册失败！");
				//可添加用户名校验是否重复用户名功能
			}
		}
			
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
