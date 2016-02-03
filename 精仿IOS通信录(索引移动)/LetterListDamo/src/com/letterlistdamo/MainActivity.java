package com.letterlistdamo;

import android.app.Activity;
import android.os.Bundle;
import android.database.Cursor;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AlphabetIndexer;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {
    private ExpressDbHelper helper;
    private LetterView letterView;
    private TextView tvToast;
    private ListView lv;
    /*顶部的view*/
    private View viewTop;
    private TextView tvTop;
    private RelativeLayout.LayoutParams params;
    /*字母索引辅助类*/
    private AlphabetIndexer indexer;
    private String alphabet = "☆ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	copyDatabase();
    	initView();
    	afterInit();
    }

    protected void initView() {
        letterView = (LetterView) findViewById(R.id.letterView);
        tvToast = (TextView) findViewById(R.id.show_now_abc);
        lv = (ListView) findViewById(R.id.listView_express);
        viewTop =  findViewById(R.id.viewOverlay_express);
        tvTop = (TextView) findViewById(R.id.tvOverlay_express);
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    protected void afterInit() {
        helper = new ExpressDbHelper(this);
        Cursor cursor = helper.getReadableDatabase().rawQuery("select * from company order by initial", null);
        /*
         * 参数1：包含数据的Cursor对象
         * 参数2：进行索引排序的列号
         * 参数3：字母表（空格将会作为第一个字符。字母要大写，并且按ascii/unicode排序。）
         */
        indexer =  new AlphabetIndexer(cursor, cursor.getColumnIndex("initial"), alphabet);
        lv.setAdapter(new OrderAdapter(this, cursor, indexer));
        letterView.setOnLetterChangeListener(new LetterView.OnLetterChangeListener() {
            @Override
            public void onLetterChange(int selectedIndex) {
                lv.setSelection(indexer.getPositionForSection(selectedIndex));
                tvToast.setText(LetterView.letters.charAt(selectedIndex) + "");//设置中间显示的字母
                tvToast.setVisibility(View.VISIBLE);//设置为可见
            }

            @Override
            public void onClickUp() {
                tvToast.setVisibility(View.GONE);//当放开时，设置为不可见
            }
        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            /*
             * firstVisibleItem表示在现时屏幕第一个ListItem(部分显示的ListItem也算)
             * totalItemCount表示ListView的ListItem总数
             * view 表示整个ListView
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //按指定数据项的位置，返回匹配的索引项。
                int section = indexer.getSectionForPosition(firstVisibleItem);
                //得到下一个索引项
                int nextSection = section+1;
                //按指定索引查找，返回匹配的第一行数据项位置或比较接近的数据项的位置
                int nextPosition = indexer.getPositionForSection(nextSection);
                //#=>判断ListView第二排的item的位置是否是下一个索引项的开始
                if (firstVisibleItem + 1 != nextPosition)
                {
                    params.topMargin = 0;
                    viewTop.setLayoutParams(params);
                    tvTop.setText(alphabet.charAt(section) + "");
                }
                else
                {
                    View v = view.getChildAt(0);
                    if (v == null)
                    {
                        return;
                    }
                    int dex = v.getBottom() - tvTop.getHeight();
                    if (dex <= 0)
                    {
                        params.topMargin = dex;
                    }
                    else
                    {
                        params.topMargin = 0;
                    }
                    tvTop.setText(alphabet.charAt(section) + "");
                    viewTop.setLayoutParams(params);
                }
                letterView.setSelected(section);
            }
        });
    }

    /**
     * copy express.db到数据库
     */
    private void copyDatabase(){
        try {
            InputStream in = getAssets().open("express.db");
            //得到数据库文件的路径
            File file = getDatabasePath("express.db");
            if(!file.exists()){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdir();
                }
                file.createNewFile();
            }
            else
            {
                return;
            }
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int lenght;
            while((lenght = in.read(buffer))!=-1){
                fos.write(buffer, 0, lenght);
            }
            fos.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
