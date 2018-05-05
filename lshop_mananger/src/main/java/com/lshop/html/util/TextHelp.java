package com.lshop.html.util;

import java.util.Locale;

import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class TextHelp {
         public  String getText(String text) {
		     return	LocalizedTextUtil.findDefaultText(text, Locale.getDefault());
		}
}
