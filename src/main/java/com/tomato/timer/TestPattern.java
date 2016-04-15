package com.tomato.timer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
	static Pattern qqNumber = Pattern.compile("[\\d]{1,}");

	public static void main(String[] args) {
		Matcher matcher = qqNumber.matcher("11111111111");
		if (matcher.find()) {
			System.out.println(matcher.group());
		}
	}
}
