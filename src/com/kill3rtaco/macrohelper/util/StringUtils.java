package com.kill3rtaco.macrohelper.util;

import java.awt.Color;

public class StringUtils {
	
	public static String minecraftToHtml(String minecraft){
		while(getFirstColorCode(minecraft) != null){
			String code = getFirstColorCode(minecraft);
			Color color = getColorForCode(code);
			String beginSpan = "<span style='color: rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")'>";
			minecraft = minecraft.replaceFirst(code, beginSpan);
			if(getFirstColorCode(minecraft) == null) minecraft += "</span>";
			else minecraft = minecraft.replaceFirst("&", "</span>&");
		}
		return minecraft;
	}
	
	public static String getFirstColorCode(String minecraft){
		String codes = "abcdef0123456789";
		for(int i=0; i<minecraft.length(); i++){
			if(minecraft.charAt(i) == '&'){
				if(i == codes.length() - 1) return null;
				if(minecraft.length() > 1 && i < minecraft.length() - 1 && codes.contains(minecraft.charAt(i + 1) + "")) return "&" + minecraft.charAt(i + 1);
			}
		}
		return null;
	}
	
	public static Color getColorForCode(String code){
		if(code.equalsIgnoreCase("&0"))
			return new Color(0, 0, 0);
		else if(code.equalsIgnoreCase("&1"))
			return new Color(0, 0, 170);
		else if(code.equalsIgnoreCase("&2"))
			return new Color(0, 170, 0);
		else if(code.equalsIgnoreCase("&3"))
			return new Color(0, 170, 170);
		else if(code.equalsIgnoreCase("&4"))
			return new Color(170, 0, 0);
		else if(code.equalsIgnoreCase("&5"))
			return new Color(170, 0, 170);
		else if(code.equalsIgnoreCase("&6"))
			return new Color(255, 170, 0);
		else if(code.equalsIgnoreCase("&7"))
			return new Color(170, 170, 170);
		else if(code.equalsIgnoreCase("&8"))
			return new Color(85, 85, 85);
		else if(code.equalsIgnoreCase("&9"))
			return new Color(85, 85, 255);
		else if(code.equalsIgnoreCase("&a"))
			return new Color(85, 255, 85);
		else if(code.equalsIgnoreCase("&b"))
			return new Color(85, 255, 255);
		else if(code.equalsIgnoreCase("&c"))
			return new Color(255, 85, 85);
		else if(code.equalsIgnoreCase("&d"))
			return new Color(255, 85, 255);
		else if(code.equalsIgnoreCase("&e"))
			return new Color(255, 255, 85);
		else if(code.equalsIgnoreCase("&f"))
			return new Color(255, 255, 255);
		return null;
	}

}
