package com.kill3rtaco.macrohelper.config;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.util.PropertiesUtils;
import com.kill3rtaco.util.TPropertiesConfig;

public class MacroEditorConfig extends TPropertiesConfig {

	private HashMap<String, String> defaults;
	
	public MacroEditorConfig() {
		super(MacroHelper.MACRO_EDITOR_PROPERTIES, "MacroHelper Options for Macro Editor");
	}
	
	public void checkColor(String key){
		if(props.getProperty("color." + key) == null){
			props.setProperty("color." + key, defaults.get(key));
		}else{
			try{
				Color.decode(props.getProperty("color." + key));
			} catch(NumberFormatException e) {
				props.setProperty("color." + key, defaults.get(key));
			}
		}
		save();
	}
	
	public Color getDefaultColor(String key){
		if(defaults.containsKey(key)) return Color.decode(defaults.get(key));
		return null;
	}
	
	public void setColor(String key, Color color){
		String r = Integer.toHexString(color.getRed());
		String g = Integer.toHexString(color.getGreen());
		String b = Integer.toHexString(color.getBlue());
		String hex = "#" + (color.getRed() < 10 ? "0" + r : r) + "" + 
				(color.getGreen() < 10 ? "0" + g : g) + "" + 
				(color.getBlue() < 10 ? "0" + b : b);
		props.setProperty("color." + key, hex);
	}
	
	public Color getColor(String key){
		reload();
		return Color.decode(props.getProperty("color." + key));
	}
	
	public String getColorAsHtmlString(String key){
		Color color = getColor(key);
		return "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
	}
	
	public ArrayList<String> getAllKeywords(){
		ArrayList<String> keywords = new ArrayList<String>(PropertiesUtils.reloadPropertiesWithStream(getClass().getResourceAsStream(MacroHelper.RES_FOLDER + "statements.txt")).stringPropertyNames());
		ArrayList<String> add = new ArrayList<String>();
		ArrayList<String> remove = new ArrayList<String>();
		for(String s : keywords){
			remove.add(s);
			add.add(s.substring(0, s.indexOf("(")));
		}
		keywords.removeAll(remove);
		keywords.addAll(add);
		keywords.add("DO");
		keywords.add("WHILE");
		keywords.add("UNTIL");
		keywords.add("IF");
		keywords.add("IIF");
		keywords.add("ELSE");
		keywords.add("ELSEIF");
		keywords.add("ENDIF");
		return keywords;
	}
	
	public ArrayList<String> getAllVariables(){
		ArrayList<String> vars = new ArrayList<String>();
		ArrayList<String> ve = new ArrayList<String>(PropertiesUtils.reloadPropertiesWithStream(getClass().getResourceAsStream(MacroHelper.RES_FOLDER + "vevent.txt")).stringPropertyNames());
		ArrayList<String> vg = new ArrayList<String>(PropertiesUtils.reloadPropertiesWithStream(getClass().getResourceAsStream(MacroHelper.RES_FOLDER + "vgeneral.txt")).stringPropertyNames());
		ArrayList<String> vi = new ArrayList<String>(PropertiesUtils.reloadPropertiesWithStream(getClass().getResourceAsStream(MacroHelper.RES_FOLDER + "vinput.txt")).stringPropertyNames());
		ArrayList<String> vp = new ArrayList<String>(PropertiesUtils.reloadPropertiesWithStream(getClass().getResourceAsStream(MacroHelper.RES_FOLDER + "vplayer.txt")).stringPropertyNames());
		ArrayList<String> vw = new ArrayList<String>(PropertiesUtils.reloadPropertiesWithStream(getClass().getResourceAsStream(MacroHelper.RES_FOLDER + "vworld.txt")).stringPropertyNames());
		vars.addAll(ve);
		vars.addAll(vg);
		vars.addAll(vi);
		vars.addAll(vp);
		vars.addAll(vw);
		for(String s : vars){
			if(s.contains("-") || s.contains("'")){
				vars.remove(s);
				vars.add(s.replaceAll("-", " ").replaceAll("'", ":"));
			}
		}
		return vars;
	}

	@Override
	public void initDefaults() {
		defaults = new HashMap<String, String>();
		defaults.put("default", "#000000");
		defaults.put("keyword", "#7F0055");
		defaults.put("operator", "#000000");
		defaults.put("string", "#2A00FF");
		defaults.put("string-var", "#323F70");
		defaults.put("var", "#323F70");
		checkColor("default");
		checkColor("keyword");
		checkColor("operator");
		checkColor("string");
		checkColor("string-var");
		checkColor("var");
	}

}
