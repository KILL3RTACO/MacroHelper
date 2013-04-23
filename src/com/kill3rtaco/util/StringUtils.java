package com.kill3rtaco.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class StringUtils {
	
	public static final String START_HTML = "<html><body>";
	public static final String END_HTML = "</html></body>";
	
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
	
	public static String makeStringFromArray(String[] args){
		String result = args[0];
		args = removeFirstArg(args);
		for(String s : args){
			result += " " + s;
		}
		return result;
	}
	
	public static String[] removeArgs(String[] array, int startIndex){
	    if (array.length == 0)
	      return array;
	    if (array.length < startIndex)
	      return new String[0];
	    
	    String[] newSplit = new String[array.length - startIndex];
	    System.arraycopy(array, startIndex, newSplit, 0, array.length - startIndex);
	    return newSplit;
	}

	public static String[] removeFirstArg(String[] array){
	    return removeArgs(array, 1);
	}
	
	public static int lengthUntilNextDoubleQuote(String text, int first){
		return lengthUntilNext("\"", text, first);
	}
	
	public static int lengthUntilNextPercent(String text, int first){
		if(first == text.length() - 1) return -1;
		String str = text.substring(first + 1);
		if(!str.contains("%")) return -1;
		return str.indexOf("%") + 2;
	}
	
	public static int lengthUntilNext(String s, String text, int first){
		if(first == text.length() - 1) return 1;
		String str = text.substring(first + 1);
		if(!str.contains(s)) return str.length() + 1;
		return str.indexOf(s) + 2;
	}
	
	public static boolean containsIgnoreCase(String text, String s){
		if(text.contains(s)) return true;
		return text.toLowerCase().contains(s.toLowerCase());
	}
	
	public static int indexOfIgnoreCase(String text, String s){
		return text.toLowerCase().indexOf(s.toLowerCase());
	}
	
	public static String getContents(File file){
		try {
			return getContents(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			return "";
		}
	}
	
	public static String getContents(InputStream stream){
		String string = "";
		Scanner x = new Scanner(stream);
		while(x.hasNextLine()){
			string += x.nextLine() + "\n";
		}
		return string;
	}

}
