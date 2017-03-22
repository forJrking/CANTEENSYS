package utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;
import bean.Cook;
import bean.Cookbook;
import bean.Shop;

public class ShopDao {

	

	public List<Shop> parse(InputStream is) throws Exception {

		List<Shop> mList = null;

		Shop shop = null;

		Cookbook cookbook = null;

		Cook mCook= null;
		// 由android.util.Xml创建一个XmlPullParser实例
		XmlPullParser xpp = Xml.newPullParser();
		// 设置输入流 并指明编码方式
		xpp.setInput(is, "UTF-8");
		// 产生第一个事件
		int eventType = xpp.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			// 判断当前事件是否为文档开始事件
			case XmlPullParser.START_DOCUMENT:
				mList = new ArrayList<Shop>(); // 初始化集合
				break;
			// 判断当前事件是否为标签元素开始事件
			case XmlPullParser.START_TAG:

				if (xpp.getName().equals("shop")) { // 判断开始标签元素是否是shops
					shop = new Shop();
				} else if (xpp.getName().equals("name")) {

					shop.name = xpp.nextText();
				} else if (xpp.getName().equals("adImg")) {

					shop.adImg = xpp.nextText();

				} else if (xpp.getName().equals("phone")) {

					shop.phone = xpp.nextText();

				} else if (xpp.getName().equals("cookbooks")) {
					shop.Cookbooks = new ArrayList<Cookbook>();

				} else if (xpp.getName().equals("cookbook")) {
					cookbook = new Cookbook();
					
					cookbook.cooks = new ArrayList<Cook>();
					
				} else if (xpp.getName().equals("cookmenu")) {
					cookbook.cookmenu = xpp.nextText();
				} else if (xpp.getName().equals("cook")) {
					mCook = new Cook();
				} else if (xpp.getName().equals("cookname")) {
					mCook.cookName = xpp.nextText();
				} else if (xpp.getName().equals("price")) {
					mCook.price = xpp.nextText();
				}
				break;

			// 判断当前事件是否为标签元素结束事件
			case XmlPullParser.END_TAG:
				if (xpp.getName().equals("shop")) { 
					mList.add(shop); 
					shop = null;
				}else if (xpp.getName().equals("cook")) {
					//添加菜
					cookbook.cooks.add(mCook);
				}else if (xpp.getName().equals("cookbook")) {
					//添加菜品
					shop.Cookbooks.add(cookbook);
				}
				break;
			}
			// 进入下一个元素并触发相应事件
			eventType = xpp.next();
		}

		return mList;

	}
}
