package com.zuo.quickaction;
//Download by http://www.codefans.net
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.zuo.quickaction.QuickLocationRightTool.OnTouchingLetterChangedListener;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zuowen
 * @time 2013.05.08
 * @email 782287169@qq.com
 * <p/>
 * 混合字符串处理  中英文排序   右侧悬浮栏   顶部名称提示栏   滚动悬浮提示
 */
public class QuickLocationMainActivity extends Activity implements ListView.OnScrollListener,
        OnItemClickListener, android.view.View.OnClickListener
{
    private QuickLocationRightTool letterListView;
    private Handler handler;
    private DisapearThread disapearThread;
    private int scrollState;
    private QuickLocationListAdapter quickLocationListAdapter;
    private ListView listMain;
    private TextView txtOverlay, title;
    private WindowManager windowManager;

    private String[] stringArr = {"ai", "zuo", "wn", "g黄真伊", "he河智苑", "@", "&&*(*", "?? ??? ???", "?", "擬好",
            "上饶", "厦门", "深圳", "武林", "text1", "+*())*&%$^", "11112", "6666", "898和",
            "阿拉伯", "阿镇", "下午", "责打", "浙江", "浙江", "阿布", "北京", "北城", "成", "城市", "123a", "234b", "678c", "得", "额",
            "方", "搞", "广州", "黄石", "黄冈", "杭州", "上海", "武林"};

    private String[] stringArr3 = new String[0];
    private ArrayList arrayList = new ArrayList();
    private ArrayList arrayList2 = new ArrayList();
    private ArrayList arrayList3 = new ArrayList();
    private Map<String, String> map = new HashMap<String, String>();


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        for (int i = 0; i < stringArr.length; i++) {
            String pinyin = converterToPinYin(stringArr[i]);
            arrayList.add(pinyin);                                 //此列表增加拼音
            Collections.sort(arrayList, new MixComparator());
            if (!arrayList2.contains(pinyin.substring(0, 1)) && isWord(pinyin.substring(0, 1))) {
                arrayList2.add(pinyin.substring(0, 1));        //此列表添加拼音首字母
                Collections.sort(arrayList2, new MixComparator());
            }
            map.put(pinyin, stringArr[i]);
        }
        stringArr = (String[]) arrayList.toArray(stringArr);

        arrayList3.add("#");                                     //此列表添加不规则字符
        for (int i = 0; i < arrayList2.size(); i++) {
            String string = (String) arrayList2.get(i);
            arrayList3.add(string.toUpperCase());       //toUpperCase大写字母
        }
        arrayList3.add("*");

        stringArr3 = (String[]) arrayList3.toArray(stringArr3); // 得到右侧英文字母列表
        letterListView = (QuickLocationRightTool) findViewById(R.id.rightCharacterListView);
        letterListView.setB(stringArr3);
        letterListView.setOnTouchingLetterChangedListener(new LetterListViewListener());

        textOverlayout();

        // 初始化ListAdapter
        quickLocationListAdapter = new QuickLocationListAdapter(this, stringArr, this, null);
        listMain = (ListView) findViewById(R.id.listInfo);
        listMain.setOnItemClickListener(this);
        listMain.setOnScrollListener(this);
        listMain.setAdapter(quickLocationListAdapter);
        disapearThread = new DisapearThread();
    }

    /**
     * 滚到悬浮字母
     */
    public void textOverlayout()
    {
        handler = new Handler();
        //顶部悬浮
        title = (TextView) findViewById(R.id.list_title);
        // 初始化首字母悬浮提示框
        txtOverlay = (TextView) LayoutInflater.from(this).inflate(
                R.layout.popup_char, null);
        txtOverlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(txtOverlay, lp);
    }

    /**
     * 右侧导航条点击列表滚动指定位置
     */
    public class LetterListViewListener implements
            OnTouchingLetterChangedListener
    {
        public void onTouchingLetterChanged(final String s)
        {
            int num = 0;
            for (int i = 0; i < stringArr.length; i++) {
                if ("a".equals(s) || "#".equals(s)) {      //顶部
                    num = 0;
                }
                else if ("*".equals(s)) {                      //底部
                    num = stringArr.length;
                }
                else if (isWord(stringArr[i].substring(0, 1)) && (character2ASCII(stringArr[i].substring(0, 1)) < (character2ASCII(s) + 32))) {
                    num += 1;                                     //首先判断是字母，字母的ascll值小于s是，滚动位置+1；如果有10个数据小于s，就滚到10处
                }

            }
            if (num < 2) {
                listMain.setSelectionFromTop(num, 0);
            }
            else {
                listMain.setSelectionFromTop(num, 5);    //留点间隔
            }
        }
    }

    /**
     * 滚动处理
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount)
    {
        title.setVisibility(View.VISIBLE);
        if (firstVisibleItem != 0) {
            title.setText(map.get(stringArr[firstVisibleItem]));
        }
        else {
            title.setText("a");
        }
        title.setText(map.get(stringArr[firstVisibleItem]));
        txtOverlay.setText(String.valueOf(stringArr[firstVisibleItem].charAt(0)));// 泡泡文字以第一个可见列表为准

    }

    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        this.scrollState = scrollState;
        if (scrollState == ListView.OnScrollListener.SCROLL_STATE_IDLE) {
            handler.removeCallbacks(disapearThread);
            // 提示延迟1.0s再消失
            boolean bool = handler.postDelayed(disapearThread, 1000);
        }
        else {
            txtOverlay.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 列表点击
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id)
    {
        String personalName = map.get(stringArr[position]);
        Toast.makeText(QuickLocationMainActivity.this, personalName, 1).show();
    }

    public void onClick(View view)
    {

    }

    private class DisapearThread implements Runnable
    {
        public void run()
        {
            // 避免在1.5s内，用户再次拖动时提示框又执行隐藏命令。
            if (scrollState == ListView.OnScrollListener.SCROLL_STATE_IDLE) {
                txtOverlay.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        txtOverlay.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
        windowManager.removeView(txtOverlay);
    }


    /**
     * 汉语拼音转换工具
     *
     * @param chinese
     * @return
     */
    public String converterToPinYin(String chinese)
    {
        String pinyinString = "";
        char[] charArray = chinese.toCharArray();
        // 根据需要定制输出格式，我用默认的即可
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        try {
            // 遍历数组，ASC码大于128进行转换
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] > 128) {
                    // charAt(0)取出首字母
                    if (charArray[i] >= 0x4e00 && charArray[i] <= 0x9fa5) {    //判断是否中文
                        pinyinString += PinyinHelper.toHanyuPinyinStringArray(
                                charArray[i], defaultFormat)[0].charAt(0);
                    }
                    else {                          //不是中文的打上未知，所以无法处理韩文日本等等其他文字
                        pinyinString += "?";
                    }
                }
                else {
                    pinyinString += charArray[i];
                }
            }
            return pinyinString;
        }
        catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 把单个英文字母或者字符串转换成数字ASCII码
     *
     * @param input
     * @return
     */
    public static int character2ASCII(String input)
    {
        char[] temp = input.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char each : temp) {
            builder.append((int) each);
        }
        String result = builder.toString();
        return Integer.parseInt(result);
    }

    /**
     * 混合排序工具
     */
    public class MixComparator implements Comparator<String>
    {
        public int compare(String o1, String o2)
        {
            // 判断是否为空""
            if (isEmpty(o1) && isEmpty(o2))
                return 0;
            if (isEmpty(o1))
                return -1;
            if (isEmpty(o2))
                return 1;
            String str1 = "";
            String str2 = "";
            try {
                str1 = (o1.toUpperCase()).substring(0, 1);
                str2 = (o2.toUpperCase()).substring(0, 1);
            }
            catch (Exception e) {
                System.out.println("某个str为\" \" 空");
            }
            if (isWord(str1) && isWord(str2)) {               //字母
                return str1.compareTo(str2);
            }
            else if (isNumeric(str1) && isWord(str2)) {     //数字字母
                return 1;
            }
            else if (isNumeric(str2) && isWord(str1)) {
                return -1;
            }
            else if (isNumeric(str1) && isNumeric(str2)) {       //数字数字
                if (Integer.parseInt(str1) > Integer.parseInt(str2)) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else if (isAllWord(str1) && (!isAllWord(str2))) {      //数字字母  其他字符
                return -1;
            }
            else if ((!isAllWord(str1)) && isWord(str2)) {
                return 1;
            }
            else {
                return 1;
            }
        }
    }

    /**
     * 判断空
     *
     * @param str
     * @return
     */
    private boolean isEmpty(String str)
    {
        return "".equals(str.trim());
    }

    /**
     * 判断数字
     *
     * @param str
     * @return
     */
    public boolean isNumeric(String str)
    {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 判读字母
     *
     * @param str
     * @return
     */
    public boolean isWord(String str)
    {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 判断字母数字混合
     *
     * @param str
     * @return
     */
    public boolean isAllWord(String str)
    {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        else {
            return true;
        }
    }

}