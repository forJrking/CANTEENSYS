package adapter;

import java.util.List;

import bean.Cook;
import bean.Cookbook;
import bean.Shop;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class CooksAdapter extends BaseExpandableListAdapter {

	Context context;

	List<Cookbook> cookbooks;

	public CooksAdapter(Context context, Shop shop) {
		this.context = context;
		this.cookbooks = shop.Cookbooks;
	}

	@Override
	public int getGroupCount() {
		return cookbooks.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return (cookbooks.get(groupPosition)).cooks.size();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		TextView tv = new TextView(context);
		tv.setTextSize(18);
		tv.setPadding(90, 25, 25, 25);
		tv.setText(cookbooks.get(groupPosition).cookmenu);
		tv.setBackgroundColor(Color.parseColor("#33000000"));

		return tv;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		TextView tv = new TextView(context);
		tv.setTextSize(15);
		tv.setPadding(25, 15, 15, 15);
		tv.setLineSpacing(35,1.2f);
		List<Cook> cooks = cookbooks.get(groupPosition).cooks;
		Cook cook = cooks.get(childPosition);
		tv.setText(cook.cookName + "\n" + cook.price);
		return tv;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
