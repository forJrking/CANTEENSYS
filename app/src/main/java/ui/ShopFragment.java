package ui;

import java.io.InputStream;
import java.util.List;

import utils.ShopDao;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import bean.Shop;

import com.student.drs.R;

import conf.Constants;

public class ShopFragment extends Fragment implements OnClickListener {

	private View mRootView;
	private TextView mTitle1;
	private TextView mTitle2;

	private ImageView mIv1;
	private ImageView mIv2;


	private List<Shop> shops;// 店铺数据

	private String[] shopXml = { "east_2.xml", "west_2.xml", "east_3.xml",
			"west_3.xml" };// 店铺数据

	private String xmlName;

	// 不同类型
	public ShopFragment(int i) {
		xmlName = shopXml[i];
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.shopfragment, container, false);
		init();
		initData();
		return mRootView;
	}

	private void init() {
		mTitle1 = (TextView) findViewById(R.id.title1);
		mTitle2 = (TextView) findViewById(R.id.title2);

		mIv1 = (ImageView) findViewById(R.id.iv1);
		mIv2 = (ImageView) findViewById(R.id.iv2);

		findViewById(R.id.container_1).setOnClickListener(this);
		findViewById(R.id.container_2).setOnClickListener(this);

	}

	private void initData() {

		ShopDao shopDao = new ShopDao();
		InputStream is;

		try {
			is = getActivity().getAssets().open(xmlName);
			shops = shopDao.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Shop shop1 = shops.get(0);
		Shop shop2 = shops.get(1);

		setData(1, shop1);
		setData(2, shop2);

	}

	public void setData(int i, Shop shop) {
		if (shop == null) {
			return;
		}
		ApplicationInfo appInfo = getActivity().getApplicationInfo();
		int resID = getResources().getIdentifier(shop.adImg, "drawable", appInfo.packageName);

		if (i == 1) {
			mTitle1.setText(shop.name);
			mIv1.setImageResource(resID);
		} else {
			mTitle2.setText(shop.name);
			mIv2.setImageResource(resID);
		}

	}

	/**
	 * 
	 * 获取控件对象
	 * 
	 * @param id
	 *            控件id
	 * @return 控件对象
	 */
	public View findViewById(int id) {
		if (getContentView() != null) {
			return getContentView().findViewById(id);
		} else {
			return null;
		}
	}

	/**
	 * 说明：返回当前View
	 * 
	 * @return view
	 */
	protected View getContentView() {
		return mRootView;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(), CookListActivity.class);
		Shop shop = null;
		
		switch (v.getId()) {
		case R.id.container_1:
			shop= shops.get(0);
			break;
		case R.id.container_2:
			shop = shops.get(1);
			break;

		}
		
		intent.putExtra(Constants.IntentKEY.cooks,shop );
		startActivity(intent);
	}
}
