package lab.sodino.list_quickaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Sodino E-mail:sodinoopen@hotmail.com
 * @version Time：2011-5-1 下午03:22:00
 */
public class ListAdapter extends BaseAdapter {
	public static final String URL_PREFIX = "http://www.google.com/#sclient=psy&hl=en&newwindow=1&q=";
	private LayoutInflater layoutInflater;
	private OnClickListener onClickListener;
	private String[] stringArr;

	public ListAdapter(Context context, String[] arr, OnClickListener listener) {
		layoutInflater = LayoutInflater.from(context);
		this.onClickListener = listener;
		stringArr = arr;
	}

	public int getCount() {
		return stringArr == null ? 0 : stringArr.length;
	}

	public Object getItem(int position) {
		if (stringArr != null) {
			return stringArr[position];
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.firstCharHintTextView = (TextView) convertView
					.findViewById(R.id.text_first_char_hint);
			holder.orderTextView = (TextView) convertView.findViewById(R.id.list_order_number);
			holder.nameTextView = (TextView) convertView.findViewById(R.id.text_website_name);
			holder.urlTextView = (TextView) convertView.findViewById(R.id.text_website_url);
			holder.imgView = (ImageView) convertView.findViewById(R.id.list_item_img_view);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.orderTextView.setText(String.valueOf(position + 1) + ".");
		holder.nameTextView.setText(stringArr[position]);
		holder.urlTextView.setText(URL_PREFIX + stringArr[position]);
		holder.urlTextView.setTextColor(0xFFFFFF00);
		holder.imgView.setOnClickListener(onClickListener);
		holder.imgView.setTag(position);
		int idx = position - 1;
		char previewChar = idx >= 0 ? stringArr[idx].charAt(0) : ' ';
		char currentChar = stringArr[position].charAt(0);
		if (currentChar != previewChar) {
			holder.firstCharHintTextView.setVisibility(View.VISIBLE);
			holder.firstCharHintTextView.setText(String.valueOf(currentChar));
		} else {
			// 此段代码不可缺：实例化一个CurrentView后，会被多次赋值并且只有最后一次赋值的position是正确
			holder.firstCharHintTextView.setVisibility(View.GONE);
		}
		return convertView;
	}

	public final class ViewHolder {
		public TextView firstCharHintTextView;
		public TextView orderTextView;
		public TextView nameTextView;
		public TextView urlTextView;
		public ImageView imgView;
	}
}