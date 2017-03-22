package ui;

import adapter.CooksAdapter;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;
import bean.Shop;

import com.student.drs.R;

import conf.Constants;

public class CookListActivity extends BaseActivity implements OnClickListener {

	private ExpandableListView mList;
	private TextView mTitle;
	private Shop mShop;

	@Override
	public int getLayoutResId() {
		return R.layout.list_activity;
	}

	@Override
	public void initView() {
		mTitle = (TextView) findViewById(R.id.title);
		mList = (ExpandableListView) findViewById(R.id.list);
	}

	@Override
	public void initData() {
		Intent intent = getIntent();
		if (intent != null) {
			//拿取 intent传递数据    必须序列化的
			mShop = (Shop) intent.getSerializableExtra(Constants.IntentKEY.cooks);
			if (mShop != null)
				mTitle.setText(mShop.name);
		}

		CooksAdapter adapter = new CooksAdapter(this, mShop);
		mList.setAdapter(adapter);
		// 全部默认展开方法
		int groupCount = mList.getCount();
		for (int i = 0; i < groupCount; i++) {
			mList.expandGroup(i);
		}
	}

	@Override
	public void initListener() {
		findViewById(R.id.btn).setOnClickListener(this);
		findViewById(R.id.goback_iv).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:

			// 调用打电话意图
			Intent intent = new Intent();
			// intent.setAction(Intent.ACTION_CALL); 直接拨打
			// 到打电话界面等待拨号
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel://" + mShop.phone));
			startActivity(intent);

			break;
		case R.id.goback_iv:
			onBackPressed();
			break;
		}

	}

}
