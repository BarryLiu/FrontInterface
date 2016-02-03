package com.justlcw.letterlistview;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.justlcw.letterlistview.letter.LetterBaseListAdapter;
import com.justlcw.letterlistview.letter.LetterListView;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LetterListView letterListView = (LetterListView) findViewById(R.id.letterListView);
        letterListView.setAdapter(new TestAdapter());
    }

    /**
     * 这里 使用一个简单的  NameValuePair 对象,做为测试
     *@Title:
     *@Description:
     *@Author:Justlcw
     *@Since:2014-5-13
     *@Version:
     */
    class TestAdapter extends LetterBaseListAdapter<NameValuePair>
    {
        /** 字母对应的key,因为字母是要插入到列表中的,为了区别,所有字母的item都使用同一的key.  **/
        private static final String LETTER_KEY = "letter";
        
        /** 这里的数据都已经按着字母排序好了, 所以传入进来的数据也应排序好,不然会出现跳转问题.  **/
        String[] dataArray = { 
                "鞍山", "案场", "白宫", "白云", "白俄", "长沙", "常州", "常熟", "大厂", "大娜迦", 
                "福州", "福建", "富豪", "广州", "湖南", "湖北", "胡同", "加州","加拉大", "家具",
                "开门", "开始", "可能", "连接", "利用","煤化工", "密度","漫画",  "你好", "你的",
                "哪些", "欧版", "排行", "贫困", "平时", "请问", "确认", "其他", "染发", "让他",
                "头像", "是个", "数据", "天空", "退出", "提示", "为空", "维护", "新建", "想到",
                "用户", "阅读", "知道", "这本", "足球"};
        
        public TestAdapter()
        {
            super();
            
            List<NameValuePair> dataList = new ArrayList<NameValuePair>();
            for(int i=0; i<dataArray.length; i++)
            {
                NameValuePair pair = new BasicNameValuePair(String.valueOf(i), dataArray[i]);
                dataList.add(pair);
            }
            setContainerList(dataList);
        }

        @Override
        public Object getItem(int position)
        {
            return list.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public String getItemString(NameValuePair t)
        {
            return t.getValue();
        }

        @Override
        public NameValuePair create(char letter)
        {
            return new BasicNameValuePair(LETTER_KEY, String.valueOf(letter));
        }

        @Override
        public boolean isLetter(NameValuePair t)
        {
            //判断是不是字母行,通过key比较,这里是NameValuePair对象,其他对象,就由你自己决定怎么判断了.
            return t.getName().equals(LETTER_KEY);
        }

        @Override
        public View getLetterView(int position, View convertView, ViewGroup parent)
        {
            //这里是字母的item界面设置.
            if(convertView == null)
            {
                convertView = new TextView(MainActivity.this);
                ((TextView)convertView).setGravity(Gravity.CENTER_VERTICAL);
                convertView.setBackgroundColor(getResources().getColor(android.R.color.white));
            }
            ((TextView)convertView).setText(list.get(position).getValue());
            
            return convertView;
        }

        @Override
        public View getContainerView(int position, View convertView, ViewGroup parent)
        {
            //这里是其他正常数据的item界面设置.
            if(convertView == null)
            {
                convertView = new TextView(MainActivity.this);
                ((TextView)convertView).setGravity(Gravity.CENTER_VERTICAL);
            }
            ((TextView)convertView).setText(list.get(position).getValue());
            
            return convertView;
        }
    }
}
