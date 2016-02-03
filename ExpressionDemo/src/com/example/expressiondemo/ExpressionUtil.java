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
	 * 瀵箂panableString杩涜姝ｅ垯鍒ゆ柇锛屽鏋滅鍚堣姹傦紝鍒欎互琛ㄦ儏鍥剧墖浠ｆ浛
	 */
    public static void dealExpression(Context context,SpannableString spannableString, Pattern patten, int start) throws Exception {
    	Matcher matcher = patten.matcher(spannableString);//寰楀埌涓�釜姣旇緝鍣�        while (matcher.find()) {
            String key = matcher.group();//spannableString浣滀负鏁翠釜鍖归厤
            if (matcher.start() < start) {//寮�鐨勪綅缃�                continue;
            }
            Field field = R.drawable.class.getDeclaredField(key);
			int resId = Integer.parseInt(field.get(null).toString());//閫氳繃涓婇潰鍖归厤寰楀埌鐨勫瓧绗︿覆鏉ョ敓鎴愬浘鐗囪祫婧恑d
            if (resId != 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
                ImageSpan imageSpan = new ImageSpan(bitmap);//閫氳繃鍥剧墖璧勬簮id鏉ュ緱鍒癰itmap锛岀敤涓�釜ImageSpan鏉ュ寘瑁�		            
                int end = matcher.start() + key.length();//璁＄畻璇ュ浘鐗囧悕瀛楃殑闀垮害锛屼篃灏辨槸瑕佹浛鎹㈢殑瀛楃涓茬殑闀垮害					
                spannableString.setSpan(imageSpan, matcher.start(), end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//灏嗚鍥剧墖鏇挎崲瀛楃涓蹭腑瑙勫畾鐨勪綅缃腑  	
                if (end < spannableString.length()) {//濡傛灉鏁翠釜瀛楃涓茶繕鏈獙璇佸畬锛屽垯缁х画銆傘� 						
                    dealExpression(context,spannableString,  patten, end);
                }
                break;
            }
        }
    }
    public static SpannableString getExpressionString(Context context,String str){
    	SpannableString spannableString = new SpannableString(str);
    	String zhengze = "f0[0-9]{2}|f10[0-7]"; // 姝ｅ垯琛ㄨ揪寮忥紝鐢ㄦ潵鍒ゆ柇娑堟伅鍐呮槸鍚︽湁琛ㄦ儏
        Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);//閫氳繃浼犲叆鐨勬鍒欒〃杈惧紡鏉ョ敓鎴愪竴涓猵attern
        try {
            dealExpression(context,spannableString, sinaPatten, 0);
        } catch (Exception e) {
            Log.e("dealExpression", e.getMessage());
        }
        return spannableString;
    }
}