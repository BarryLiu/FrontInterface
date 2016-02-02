package com.example.expressiondemo;

import java.util.HashMap;
import java.util.Map;

public class EmojExpressionLib extends AExpressionLib {

	public static int[] expressions = new int[] { R.drawable.emo00,
			R.drawable.emo01, R.drawable.emo02, R.drawable.emo03, R.drawable.emo04,
			R.drawable.emo05, R.drawable.emo06, R.drawable.emo07, R.drawable.emo08,
			R.drawable.emo09, R.drawable.emo10, R.drawable.emo11, R.drawable.emo12,
			R.drawable.emo13, R.drawable.emo14, R.drawable.emo15, R.drawable.emo16,
			R.drawable.emo17, R.drawable.emo18, R.drawable.emo19, R.drawable.emo20,
			R.drawable.emo21, R.drawable.emo22, R.drawable.emo23, R.drawable.emo24,
			R.drawable.emo25, R.drawable.emo26, R.drawable.emo27, R.drawable.emo28,
			R.drawable.emo29, R.drawable.emo30, R.drawable.emo31, R.drawable.emo32,
			R.drawable.emo33, R.drawable.emo34, R.drawable.emo35, R.drawable.emo36,
			R.drawable.emo37, R.drawable.emo38, R.drawable.emo39, R.drawable.emo40,
			R.drawable.emo41, R.drawable.emo42, R.drawable.emo43, R.drawable.emo44,
			R.drawable.emo45, R.drawable.emo46, R.drawable.emo47 ,R.drawable.emo48,
			R.drawable.emo49, R.drawable.emo50, R.drawable.emo51, R.drawable.emo52,
			R.drawable.emo53, R.drawable.emo54, R.drawable.emo55, R.drawable.emo56,
			R.drawable.emo57, R.drawable.emo58, R.drawable.emo59, R.drawable.emo60,
			R.drawable.emo61, R.drawable.emo62, R.drawable.emo63, R.drawable.emo64,
			R.drawable.emo65, R.drawable.emo66, R.drawable.emo67, R.drawable.emo68,
			R.drawable.emo69, R.drawable.emo70, R.drawable.emo71  };

	/**
	 * 本地表情名
	 */
	public static String[] expressionNames = new String[] { "[emo00]",
			"[emo01]", "[emo02]", "[emo03]", "[emo04]", "[emo05]", "[emo06]",
			"[emo07]", "[emo08]", "[emo09]", "[emo10]", "[emo11]", "emo12]",
			"[emo13]", "[emo14]", "[emo15]", "[emo16]", "[emo17]", "[emo18]",
			"[emo19]", "[emo20]", "[emo21]", "[emo22]", "[emo23]", "[emo24]",
			"[emo25]", "[emo26]", "[emo27]", "[emo28]", "[emo29]", "[emo30]",
			"[emo31]", "[emo32]", "[emo33]", "[emo34]", "[emo35]", "[emo36]",
			"[emo37]", "[emo38]", "[emo39]", "[emo40]", "[emo41]", "[emo42]",
			"[emo43]", "[emo44]", "[emo45]", "[emo46]", "[emo47]", "[emo48]",
			"[emo49]", "[emo50]", "[emo51]", "[emo52]", "[emo53]", "[emo54]",
			"[emo55]", "[emo56]", "[emo57]", "[emo58]", "[emo59]", "[emo60]",
			"[emo61]", "[emo62]", "[emo63]", "[emo64]", "[emo65]", "[emo66]",
			"[emo67]", "[emo68]", "[emo69]", "[emo70]", "[emo71]"  };
	
	public EmojExpressionLib() {
		Map<String, Integer> expressions = new HashMap<String, Integer>();
		for(int i=0; i<EmojExpressionLib.expressions.length; i++) {
			expressions.put(EmojExpressionLib.expressionNames[i]
					, EmojExpressionLib.expressions[i]);
		}
		
		super.setPackageName("Emoji");
		super.setPackageIcon(R.drawable.ic_emoj_expression_n);
		super.setExpressions(expressions);
	}

	@Override
	public String getMatchReg() {
		return "\\[emo.{2}\\]";
	}

}
