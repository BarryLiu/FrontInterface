package com.example.expressiondemo;




public class Expressions {

	public static int[] expressionImgs = new int[] { R.drawable.f000,
			R.drawable.f001, R.drawable.f002, R.drawable.f003, R.drawable.f004,
			R.drawable.f005, R.drawable.f006, R.drawable.f007, R.drawable.f008,
			R.drawable.f009, R.drawable.f010, R.drawable.f011, R.drawable.f012,
			R.drawable.f013, R.drawable.f014, R.drawable.f015, R.drawable.f016,
			R.drawable.f017, R.drawable.f018, R.drawable.f019, R.drawable.f020,
			R.drawable.f021, R.drawable.f022, R.drawable.f023 };

	/**
	 * 本地表情的名字
	 */
	public static String[] expressionImgNames = new String[] { "img[f000]",
			"img[f001]", "img[f002]", "img[f003]", "img[f004]", "img[f005]", "img[f006]",
			"img[f007]", "img[f008]", "img[f009]", "img[f010]", "img[f011]", "img[f012]",
			"img[f013]", "img[f014]", "img[f015]", "img[f016]", "img[f017]", "img[f018]",
			"img[f019]", "img[f020]", "img[f021]", "img[f022]", "img[f023]" };
	
	
	
	public static int[] expressionImgs1 = new int[] { R.drawable.f024,
		R.drawable.f025, R.drawable.f026, R.drawable.f027, R.drawable.f028,
		R.drawable.f029, R.drawable.f030, R.drawable.f031, R.drawable.f032,
		R.drawable.f033, R.drawable.f034, R.drawable.f035, R.drawable.f036,
		R.drawable.f037, R.drawable.f038, R.drawable.f039, R.drawable.f040,
		R.drawable.f041, R.drawable.f042, R.drawable.f043, R.drawable.f044,
		R.drawable.f045, R.drawable.f046, R.drawable.f047 };
	
	/**
	 * 本地表情的名字1
	 */
	public static String[] expressionImgNames1 = new String[] { "img[f024]",
		"img[f025]", "img[f026]", "img[f027]", "img[f028]", "img[f029]", "img[f030]",
		"img[f031]", "img[f032]", "img[f033]", "img[f034]", "img[f035]", "img[f036]",
		"img[f037]", "img[f038]", "img[f039]", "img[f040]", "img[f041]", "img[f042]",
		"img[f043]", "img[f044]", "img[f045]", "img[f046]", "img[f047]" };
	
	
	
	
	public static int[] expressionImgs2 = new int[] { R.drawable.f048,
		R.drawable.f049, R.drawable.f050, R.drawable.f051, R.drawable.f052,
		R.drawable.f053, R.drawable.f054, R.drawable.f055, R.drawable.f056,
		R.drawable.f057, R.drawable.f058, R.drawable.f059, R.drawable.f060,
		R.drawable.f061, R.drawable.f062, R.drawable.f063, R.drawable.f064,
		R.drawable.f065, R.drawable.f066, R.drawable.f067, R.drawable.f068,
		R.drawable.f069, R.drawable.f070, R.drawable.f071 };
	
	/**
	 * 本地表情的名字2
	 */
	public static String[] expressionImgNames2 = new String[] { "img[f048]",
		"img[f049]", "img[f050]", "img[f051]", "img[f052]", "img[f053]", "img[f054]",
		"img[f055]", "img[f056]", "img[f057]", "img[f058]", "img[f059]", "img[f060]",
		"img[f061]", "img[f062]", "img[f063]", "img[f064]", "img[f065]", "img[f066]",
		"img[f067]", "img[f068]", "img[f069]", "img[f070]", "img[f071]" };

	
	/**
	 * 
	 * 
	 * 
	 */
	/*
	 * 服务器存储的表情名字
	 */
	public static String[] expressionRegImgNames = new String[] { "\\U0001F601",
			"f0asd01", "f00asd2", "fasd003", "f0gf04", "f00fg5", "f0gfdh06",
			"fhjgh007", "f0gh08", "ffgh009", "f010", "f011", "f012", "f013",
			"f014", "f015", "f016", "f017", "f05err18", "f045fd19",
			"f0234sdf20", "fsdfg021", "f0jjjh22", "f0hjh23" };

	/**
	 * 
	 * 在存入数据库时，将表情名字进行替换
	 * 
	 */
	public static String[] replaceStrings(String[] str, String[] str2) {
		String newStr[] = new String[str.length - 1];
		for (int i = 0; i < str.length; i++) {
			newStr[i] = str[i].replace(str[i], str2[i]);
		}
		return newStr;
	}
}
