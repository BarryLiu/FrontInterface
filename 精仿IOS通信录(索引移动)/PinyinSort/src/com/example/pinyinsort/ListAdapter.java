package com.example.pinyinsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private String[] stringArr;
	private List<Country> listCountry = new ArrayList<Country>();
	private Context context;
	// key - 拼音首字母大写+号码，value-name
	private Map<String, String> map = new HashMap<String, String>();
	// 首字母是A的拼音
	private List<String> listA = new ArrayList<String>();
	private List<String> listB = new ArrayList<String>();
	private List<String> listC = new ArrayList<String>();
	private List<String> listD = new ArrayList<String>();
	private List<String> listE = new ArrayList<String>();
	private List<String> listF = new ArrayList<String>();
	private List<String> listG = new ArrayList<String>();
	private List<String> listH = new ArrayList<String>();
	private List<String> listI = new ArrayList<String>();
	private List<String> listJ = new ArrayList<String>();
	private List<String> listK = new ArrayList<String>();
	private List<String> listL = new ArrayList<String>();
	private List<String> listM = new ArrayList<String>();
	private List<String> listN = new ArrayList<String>();
	private List<String> listO = new ArrayList<String>();
	private List<String> listP = new ArrayList<String>();
	private List<String> listQ = new ArrayList<String>();
	private List<String> listR = new ArrayList<String>();
	private List<String> listS = new ArrayList<String>();
	private List<String> listT = new ArrayList<String>();
	private List<String> listU = new ArrayList<String>();
	private List<String> listV = new ArrayList<String>();
	private List<String> listW = new ArrayList<String>();
	private List<String> listX = new ArrayList<String>();
	private List<String> listY = new ArrayList<String>();
	private List<String> listZ = new ArrayList<String>();
	// 将没有拼音的词条放入list_ 对应#
	private List<String> list_ = new ArrayList<String>();
	// key - 拼音首字母大写+号码
	private List<String> listAll = new ArrayList<String>();

	public ListAdapter(Context context) {
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
	}

	public void setList(List<Country> listCountry) {
		this.listCountry.clear();
		this.listCountry.addAll(listCountry);
		clear();
		setListItem();

		String[] ss = new String[listAll.size()];
		this.stringArr = (String[]) listAll.toArray(ss);
	}

	private void clear() {
		map.clear();
		listA.clear();
		listB.clear();
		listC.clear();
		listD.clear();
		listE.clear();
		listF.clear();
		listG.clear();
		listH.clear();
		listI.clear();
		listJ.clear();
		listK.clear();
		listL.clear();
		listM.clear();
		listN.clear();
		listO.clear();
		listP.clear();
		listQ.clear();
		listR.clear();
		listS.clear();
		listT.clear();
		listU.clear();
		listV.clear();
		listW.clear();
		listX.clear();
		listY.clear();
		listZ.clear();
		list_.clear();
		listAll.clear();
	}

	/**
	 * 设置ListView的item需要显示的值
	 */
	private void setListItem() {
		for (Country country : listCountry) {
			String[] s = PinyinHelper.toHanyuPinyinStringArray(country.name
					.charAt(0));
			if (null != s && s.length > 0) {
				String temp = String.valueOf(s[0].charAt(0)).toUpperCase();
				map.put(temp + country.code, country.name);
				setListValue(temp, temp + country.code);
			} else {
				map.put("#" + country.code, country.name);
				list_.add("#" + country.code);
			}
		}
		add();
	}

	/**
	 * 拼音检索调用
	 * 
	 * @param s
	 * @param listView
	 */
	public void notifyListSelect(String s, ListView listView) {
		boolean all_has_no = false;
		int num = 0;
		if ("A".equals(s)) {
			num = 0;
		} else {
			for (int i = 0; i < listAll.size(); i++) {
				String pinyin = listAll.get(i);
				// 拼音首字母
				String str;
				if (pinyin.length() > 1) {
					str = pinyin.substring(0, 1);
				} else {
					str = pinyin;
				}
				if (!s.equals(str)) {
					num++;
					all_has_no = true;
				} else {
					all_has_no = false;
					break;
				}
			}
		}
		if (!all_has_no) {
			listView.setSelectionFromTop(num, 0);
		}
	}

	private void add() {
		listAll.addAll(listA);
		listAll.addAll(listB);
		listAll.addAll(listC);
		listAll.addAll(listD);
		listAll.addAll(listE);
		listAll.addAll(listF);
		listAll.addAll(listG);
		listAll.addAll(listH);
		listAll.addAll(listI);
		listAll.addAll(listJ);
		listAll.addAll(listK);
		listAll.addAll(listL);
		listAll.addAll(listM);
		listAll.addAll(listN);
		listAll.addAll(listO);
		listAll.addAll(listP);
		listAll.addAll(listQ);
		listAll.addAll(listR);
		listAll.addAll(listS);
		listAll.addAll(listT);
		listAll.addAll(listU);
		listAll.addAll(listV);
		listAll.addAll(listW);
		listAll.addAll(listX);
		listAll.addAll(listY);
		listAll.addAll(listZ);
		listAll.addAll(list_);
	}

	private void setListValue(String str, String wordSpell) {
		if ("A".equals(str)) {
			listA.add(wordSpell);
			return;
		}
		if ("B".equals(str)) {
			listB.add(wordSpell);
			return;
		}
		if ("C".equals(str)) {
			listC.add(wordSpell);
			return;
		}
		if ("D".equals(str)) {
			listD.add(wordSpell);
			return;
		}
		if ("E".equals(str)) {
			listE.add(wordSpell);
			return;
		}
		if ("F".equals(str)) {
			listF.add(wordSpell);
			return;
		}
		if ("G".equals(str)) {
			listG.add(wordSpell);
			return;
		}
		if ("H".equals(str)) {
			listH.add(wordSpell);
			return;
		}
		if ("I".equals(str)) {
			listI.add(wordSpell);
			return;
		}
		if ("J".equals(str)) {
			listJ.add(wordSpell);
			return;
		}
		if ("K".equals(str)) {
			listK.add(wordSpell);
			return;
		}
		if ("L".equals(str)) {
			listL.add(wordSpell);
			return;
		}
		if ("M".equals(str)) {
			listM.add(wordSpell);
			return;
		}
		if ("N".equals(str)) {
			listN.add(wordSpell);
			return;
		}
		if ("O".equals(str)) {
			listO.add(wordSpell);
			return;
		}
		if ("P".equals(str)) {
			listP.add(wordSpell);
			return;
		}
		if ("Q".equals(str)) {
			listQ.add(wordSpell);
			return;
		}

		if ("R".equals(str)) {
			listR.add(wordSpell);
			return;
		}
		if ("S".equals(str)) {
			listS.add(wordSpell);
			return;
		}
		if ("T".equals(str)) {
			listT.add(wordSpell);
			return;
		}

		if ("U".equals(str)) {
			listU.add(wordSpell);
			return;
		}
		if ("V".equals(str)) {
			listV.add(wordSpell);
			return;
		}
		if ("W".equals(str)) {
			listW.add(wordSpell);
			return;
		}
		if ("X".equals(str)) {
			listX.add(wordSpell);
			return;
		}
		if ("Y".equals(str)) {
			listY.add(wordSpell);
			return;
		}
		if ("Z".equals(str)) {
			listZ.add(wordSpell);
			return;
		}
	}

	public int getCount() {
		return stringArr == null ? 0 : stringArr.length;
	}

	public Object getItem(int position) {
		String code = stringArr[position].substring(1);
		String name = map.get(stringArr[position]);
		Country country = new Country();
		country.name = name;
		country.code = code;
		return country;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.home_word_list_item,
					null);
			holder = new ViewHolder();
			holder.firstCharHintTextView = (TextView) convertView
					.findViewById(R.id.text_first_char_hint);
			holder.nameTextView = (TextView) convertView
					.findViewById(R.id.text_website_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.nameTextView.setText(map.get(stringArr[position]));
		int idx = position - 1;
		// 如果当前的position大于等于1，则获取上一个位置的拼音首字母-- 即获取需要标识分类的字母
		char previewChar = idx >= 0 ? stringArr[idx].charAt(0) : ' ';
		// char previewChar = result1.toUpperCase().charAt(0);
		char currentChar = stringArr[position].charAt(0);
		// 如果currentChar与previewChar不相同
		if (currentChar != previewChar) {
			// 展现标识分类的拼音字�?
			holder.firstCharHintTextView.setVisibility(View.VISIBLE);
			holder.firstCharHintTextView.setText(String.valueOf(currentChar));
		} else {
			// 实例化一个CurrentView后
			holder.firstCharHintTextView.setVisibility(View.GONE);
		}
		return convertView;
	}

	public final class ViewHolder {
		// 拼音字母
		public TextView firstCharHintTextView;
		// 拼音下的文字
		public TextView nameTextView;
	}
}