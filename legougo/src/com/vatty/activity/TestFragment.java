package com.vatty.activity;

import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
/**
 * vatty * 
 *
 * hongshengpeng.com
 * 
 */

import com.vatty.adapter.ContactAdapter;
import com.vatty.model.Contact;

public class TestFragment extends Fragment {
    private static final String TAG = "TestFragment";
    private String hello;// = "hello android";
    private String defaultHello = "default value";
    private Map<String, Object> maplist;
    static TestFragment newInstance(String s, Map<String, Object> map) {
        TestFragment newFragment = new TestFragment();
       // Bundle bundle = new Bundle();
       // bundle.putString("hello", s);
       // newFragment.setArguments(bundle);
        
        final SerializableMap myMap=new SerializableMap();
        myMap.setMap(map);
        Bundle bundle = new Bundle();
       
       
       
        bundle.putSerializable("map", myMap);
        newFragment.setArguments(bundle);
        return newFragment;

        
      
       
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "TestFragment-----onCreate");
        Bundle args = getArguments();
      //  hello = args != null ? args.getString("hello") : defaultHello;
        
       
        
        Bundle bundle = getArguments();
        SerializableMap serializableMap = (SerializableMap) bundle.get("map");
        maplist =serializableMap.getMap();
      
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d(TAG, "TestFragment-----onCreateView");
        View view = inflater.inflate(R.layout.lay1, container, false);
        
        
        
        // TextView viewhello = (TextView) view.findViewById(R.id.tv_hello);
        //viewhello.setText(maplist.get("userid")+"time");

        ListView lv  = (ListView) view.findViewById(R.id.listView3);
        ContactAdapter hc = new ContactAdapter(getActivity().getApplicationContext(),getContact());
        lv.setAdapter(hc);
        lv.setCacheColorHint(0);
        
        return view;

    }
    private ArrayList<Contact> getContact(){
		ArrayList<Contact> hcList = new ArrayList<Contact>();
		
		for(int i=0;i<10;i++)
		{
		Contact c0 = new Contact();
		c0.setTxPath(R.drawable.more_game+"");
		
		c0.setName(maplist.get("userid")+"  ÄêÁä£º"+maplist.get("age"));
		hcList.add(c0);
		}
		
	
		
		return hcList;
	}

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "TestFragment-----onDestroy");
    }

}
