package com.example.expressiondemo;

import java.util.HashMap;
import java.util.Map;



public class QQExpressions extends AExpressionLib {

	public static int[] expressionImgs = new int[] { R.drawable.f000,
			R.drawable.f001, R.drawable.f002, R.drawable.f003, R.drawable.f004,
			R.drawable.f005, R.drawable.f006, R.drawable.f007, R.drawable.f008,
			R.drawable.f009, R.drawable.f010, R.drawable.f011, R.drawable.f012,
			R.drawable.f013, R.drawable.f014, R.drawable.f015, R.drawable.f016,
			R.drawable.f017, R.drawable.f018, R.drawable.f019, R.drawable.f020,
			R.drawable.f021, R.drawable.f022, R.drawable.f023, R.drawable.f024,
			R.drawable.f025, R.drawable.f026, R.drawable.f027, R.drawable.f028,
			R.drawable.f029, R.drawable.f030, R.drawable.f031, R.drawable.f032,
			R.drawable.f033, R.drawable.f034, R.drawable.f035, R.drawable.f036,
			R.drawable.f037, R.drawable.f038, R.drawable.f039, R.drawable.f040,
			R.drawable.f041, R.drawable.f042, R.drawable.f043, R.drawable.f044,
			R.drawable.f045, R.drawable.f046, R.drawable.f047, R.drawable.f048,
			R.drawable.f049, R.drawable.f050, R.drawable.f051, R.drawable.f052,
			R.drawable.f053, R.drawable.f054, R.drawable.f055, R.drawable.f056,
			R.drawable.f057, R.drawable.f058, R.drawable.f059, R.drawable.f060,
			R.drawable.f061, R.drawable.f062, R.drawable.f063, R.drawable.f064,
			R.drawable.f065, R.drawable.f066, R.drawable.f067, R.drawable.f068,
			R.drawable.f069, R.drawable.f070, R.drawable.f071  };

	public static String[] expressionNames = new String[] { "[f000]",
			"[f001]", "[f002]", "[f003]", "[f004]", "[f005]", "[f006]",
			"[f007]", "[f008]", "[f009]", "[f010]", "[f011]", "[f012]",
			"[f013]", "[f014]", "[f015]", "[f016]", "[f017]", "[f018]",
			"[f019]", "[f020]", "[f021]", "[f022]", "[f023]", "[f024]",
			"[f025]", "[f026]", "[f027]", "[f028]", "[f029]", "[f030]",
			"[f031]", "[f032]", "[f033]", "[f034]", "[f035]", "[f036]",
			"[f037]", "[f038]", "[f039]", "[f040]", "[f041]", "[f042]",
			"[f043]", "[f044]", "[f045]", "[f046]", "[f047]", "[f048]",
			"[f049]", "[f050]", "[f051]", "[f052]", "[f053]", "[f054]",
			"[f055]", "[f056]", "[f057]", "[f058]", "[f059]", "[f060]",
			"[f061]", "[f062]", "[f063]", "[f064]", "[f065]", "[f066]",
			"[f067]", "[f068]", "[f069]", "[f070]", "[f071]" };
	
	public QQExpressions() {
		Map<String, Integer> expressions = new HashMap<String, Integer>();
		for(int i=0; i<QQExpressions.expressionImgs.length; i++) {
			expressions.put(QQExpressions.expressionNames[i]
					, QQExpressions.expressionImgs[i]);
		}
		
		super.setPackageName("QQExpress");
		super.setPackageIcon(R.drawable.ic_qq_expression_n);
		super.setExpressions(expressions);
	}

	@Override
	public String getMatchReg() {
		return "\\[f0.{2}\\]";
	}
	
	
	

}
