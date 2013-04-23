package com.kill3rtaco.macrohelper.config;

import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.util.TPropertiesConfig;

public class MacroHelperConfig extends TPropertiesConfig {

	private int lastIndex;
	
	public MacroHelperConfig() {
		super(MacroHelper.PROPERTIES, "MacroHelper Options");
	}

	@Override
	public void initDefaults() {
		addDefaultValue("last-selected-index", "0");
		try{
			lastIndex = Integer.parseInt(props.getProperty("last-selected-index"));
		} catch (NumberFormatException e){
			props.setProperty("last-selected-index", "0");
		}
	}
	
	public void setLastIndex(int index){
		props.setProperty("last-selected-index", index + "");
		save();
	}
	
	public int getLastIndex(){
		reload();
		return lastIndex;
	}

}
