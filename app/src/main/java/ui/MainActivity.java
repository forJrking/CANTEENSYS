package ui;

import utils.ToastUtils;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;

import com.student.drs.R;

public class MainActivity extends BaseActivity {

	private TextView mTitle;
	private ViewPager mViewPager;
	
	private String[] shops = {"二楼东","二楼西","三楼东","三楼西"};

	@Override
	public int getLayoutResId() {
		return R.layout.main_activity;
	}

	@Override
	public void initView() {
		mTitle = (TextView) findViewById(R.id.title);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
	}

	@Override
	protected void onResume() {
		super.onResume();
//		mViewPager.setCurrentItem(0);
		 mTitle.setText(shops[0]);
	}
	
	@Override
	public void initData() {
		//默认选中第一个
		
		mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new ShopFragment(0);
                        break;
                    case 1:
                        fragment = new ShopFragment(1);
                        break;
                    case 2:
                        fragment = new ShopFragment(2);
                        break;
                    case 3:
                    	fragment = new ShopFragment(3);
                    	break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            	//切换标题
                mTitle.setText(shops[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
		
	}

	@Override
	public void initListener() {
		
	}
	
	long exitTime = 0;
	
	@Override
	public void onBackPressed() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
            ToastUtils.with(this).show("再点击一次退出");
        } else {
        	super.onBackPressed();
            System.exit(0);
        }
		
	}
}
