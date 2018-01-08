package com.hi.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 工具类，包含加密、Json、获取验证码等
 * @author 王才
 *
 */
public class Utils {
	/**
	 * 获取单例模式的Base64编码类  的静态内部类
	 * @author 王才
	 *
	 */
	private static class SingleBase64Encoder{
		private final static BASE64Encoder base64Encoder = new BASE64Encoder();
		
		private SingleBase64Encoder(){};
		/**
		 * 获取单例的Base64编码类 
		 * @return BASE64Encoder
		 */
		public static final BASE64Encoder getBase64Encoder(){
			return base64Encoder;
		}
	}
	/**
	 * 获取单例模式的Base64解码类  的静态内部类
	 * @author 王才
	 *
	 */
	private static class SingleBase64Decoder{
		private final static BASE64Decoder base64Decoder = new BASE64Decoder();
		
		private SingleBase64Decoder(){};
		/**
		 * 获取单例的Base64编码类 
		 * @return BASE64Decoder
		 */
		public static final BASE64Decoder getBase64Decoder(){
			return base64Decoder;
		}
	}
	
	/**
	 * 将对象转化为Json字符串
	 * @param object
	 * @return Json字符串
	 */
	public static String toJsonString(Object object){
		return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 向Response中写入JSON字符串
	 * @param response
	 * @param jsonString
	 * @throws IOException
	 */
	public static void write(HttpServletResponse response,String jsonString) throws IOException{
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(jsonString);
	}
	/**
	 * 获得md5加密之后的字符串
	 * @param strIn
	 * @return 32位MD5字符串
	 */
	public static String md5(String strIn){
		try {
			byte[] digest = MessageDigest.getInstance("md5").digest(strIn.getBytes());
			String string = new BigInteger(1, digest).toString(16);
			
			for (int i = 0; i < 32 - string.length(); i++) {
				string = "0" + string;
			}
			return string;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得Base64编码之后的字符串
	 * @param strIn
	 * @return Base64编码字符串
	 */
	public static String encodeBase64(String strIn){
		BASE64Encoder base64Encoder = SingleBase64Encoder.getBase64Encoder();
		return base64Encoder.encode(strIn.getBytes());
	}
	/**
	 * 将Base64字符串还原
	 * @param Base64编码字符串
	 * @return 还原后的字符串<p>还原失败返回null
	 */
	public static String decodeBase64(String strIn){
		BASE64Decoder base64Decoder = SingleBase64Decoder.getBase64Decoder();
		try {
			return base64Decoder.decodeBuffer(strIn).toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将源文件随机命名。格式为uuid+格式，即类似“uuid.jpg”的样式
	 * @param name
	 * @return 随机的文件名，格式不变，不含路径
	 */
	public static String getUUIDName(String name){
		String end = name.substring(name.lastIndexOf("."));
		System.out.println(end);
		//随机生成文件名字
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid+end;
	}
	
	
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
