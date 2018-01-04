package com.hi.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ident {
	/**
	 * 获取验证码，返回一个二维数组，第一个元素为String类型的验证码字符串，第二个元素为BufferedImage类型的对象
	 * @return Object[0]   验证码String<p>
	 * 		Object[0]   BufferedImage对象
	 */
	public static Object[] getIdentImage(){
		System.out.println("/getIdentImg");
		Random rand = new Random();
		BufferedImage image = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
		Graphics g= image.getGraphics();
		g.setColor(new Color(rand.nextInt(155)+100, rand.nextInt(155)+100, rand.nextInt(155)+100));
		g.fillRect(0, 0, 68, 22);
		
		char[] c = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int index = rand.nextInt(62);
			sb.append(c[index]);
			g.setColor(new Color(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)));
			g.setFont(new Font("宋体", Font.TRUETYPE_FONT, 16));
			g.drawString(c[index]+"", i*10+4, 17);
		}
		
		System.out.println(sb.toString());
		Object[] objects = {sb.toString(),image};
		return objects;
	}
}
