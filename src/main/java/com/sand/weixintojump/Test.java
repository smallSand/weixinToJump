package com.sand.weixintojump;

import java.util.Random;

public class Test {

	public static void main(String[] args) throws Exception {
		//System.out.println("adb shell input swipe 300 500 300 500 " + Math.round(179 * 1.35));
		//Runtime.getRuntime().exec("adb shell input swipe 300 500 300 500 " + Math.round(179 * 1.35));
		int max=500;
        int min=300;
        Random random = new Random();
        int x = random.nextInt(max)%(max-min+1) + min;
        int y = random.nextInt(max)%(max-min+1) + min;
        StringBuilder s = new StringBuilder();
        s.append(x + " ").append(y + " ").append(x + " ").append(y + " ");
        System.out.println(s);
	}

}
