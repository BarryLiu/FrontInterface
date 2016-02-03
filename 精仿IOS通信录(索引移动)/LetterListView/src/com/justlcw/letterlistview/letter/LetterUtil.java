package com.justlcw.letterlistview.letter;

import net.sourceforge.pinyin4j.PinyinHelper;


/**
 * ×ÖÄ¸¹¤¾ßÀà
 *@Title:
 *@Description:
 *@Author:Justlcw
 *@Since:2014-5-8
 *@Version:
 */
public class LetterUtil
{
    /**
     * @param chinese Ò»¸öºº×Ö
     * @return Æ´ÒôÊ××ÖÄ¸
     * @Description:
     * @Author Justlcw
     * @Date 2014-5-8
     */
    public static String[] getFirstPinyin(char chinese)
    {
        return PinyinHelper.toHanyuPinyinStringArray(chinese);
    }
    
    /**
     * ÊÇ·ñÊÇ×ÖÄ¸
     * 
     * @return true ×ÖÄ¸,false ·Ç×ÖÄ¸
     * @Description:
     * @Author Justlcw
     * @Date 2014-5-8
     */
    public static boolean isLetter(char c)
    {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 112);
    }
}
