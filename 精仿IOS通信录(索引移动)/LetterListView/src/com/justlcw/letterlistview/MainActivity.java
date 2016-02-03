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
     * ���� ʹ��һ���򵥵�  NameValuePair ����,��Ϊ����
     *@Title:
     *@Description:
     *@Author:Justlcw
     *@Since:2014-5-13
     *@Version:
     */
    class TestAdapter extends LetterBaseListAdapter<NameValuePair>
    {
        /** ��ĸ��Ӧ��key,��Ϊ��ĸ��Ҫ���뵽�б��е�,Ϊ������,������ĸ��item��ʹ��ͬһ��key.  **/
        private static final String LETTER_KEY = "letter";
        
        /** ��������ݶ��Ѿ�������ĸ�������, ���Դ������������ҲӦ�����,��Ȼ�������ת����.  **/
        String[] dataArray = { 
                "��ɽ", "����", "�׹�", "����", "�׶�", "��ɳ", "����", "����", "��", "������", 
                "����", "����", "����", "����", "����", "����", "��ͬ", "����","������", "�Ҿ�",
                "����", "��ʼ", "����", "����", "����","ú����", "�ܶ�","����",  "���", "���",
                "��Щ", "ŷ��", "����", "ƶ��", "ƽʱ", "����", "ȷ��", "����", "Ⱦ��", "����",
                "ͷ��", "�Ǹ�", "����", "���", "�˳�", "��ʾ", "Ϊ��", "ά��", "�½�", "�뵽",
                "�û�", "�Ķ�", "֪��", "�Ȿ", "����"};
        
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
            //�ж��ǲ�����ĸ��,ͨ��key�Ƚ�,������NameValuePair����,��������,�������Լ�������ô�ж���.
            return t.getName().equals(LETTER_KEY);
        }

        @Override
        public View getLetterView(int position, View convertView, ViewGroup parent)
        {
            //��������ĸ��item��������.
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
            //�����������������ݵ�item��������.
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
