package com.example.expressiondemo;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;



public class ExpressionUtil {
	/**
	 * 对spanableString进行正则判断，如果符合要求，则以表情图片代替
	 */
    public static void dealExpression(Context context,SpannableString spannableString, Pattern patten, int start) throws Exception {
    	Matcher matcher = patten.matcher(spannableString);//得到一个比较器
        while (matcher.find()) {
            String key = matcher.group();//spannableString作为整个匹配
            if (matcher.start() < start) {//开始的位置
                continue;
            }
            Field field = R.drawable.class.getDeclaredField(key);
			int resId = Integer.parseInt(field.get(null).toString());//通过上面匹配得到的字符串来生成图片资源id
            if (resId != 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
                ImageSpan imageSpan = new ImageSpan(bitmap);//通过图片资源id来得到bitmap，用一个ImageSpan来包装			            
                int end = matcher.start() + key.length();//计算该图片名字的长度，也就是要替换的字符串的长度					
                spannableString.setSpan(imageSpan, matcher.start(), end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将该图片替换字符串中规定的位置中  	
                if (end < spannableString.length()) {//如果整个字符串还未验证完，则继续。。 						
                    dealExpression(context,spannableString,  patten, end);
                }
                break;
            }
        }
    }
    public static SpannableString getExpressionString(Context context,String str){
    	SpannableString spannableString = new SpannableString(str);
    	String zhengze = "f0[0-9]{2}|f10[0-7]"; // 正则表达式，用来判断消息内是否有表情
        Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);//通过传入的正则表达式来生成一个pattern
        try {
            dealExpression(context,spannableString, sinaPatten, 0);
        } catch (Exception e) {
            Log.e("dealExpression", e.getMessage());
        }
        return spannableString;
    }
}