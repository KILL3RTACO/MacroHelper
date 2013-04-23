package com.kill3rtaco.macrohelper.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.util.TPropertiesConfig;

public class PlayerJoinedConfig extends TPropertiesConfig {
	
	public PlayerJoinedConfig() {
		super(MacroHelper.PLAYERJOIN_PROPERTIES, "MacrHelper Options for onPlayerJoined");
	}
	
	public String getDefaultJoinString(){
		reload();
		return props.getProperty("join-string-format");
	}
	
	public void setString(String name, String string){
		props.setProperty(name, string);
		save();
	}
	
	public String getString(String playername){
		reload();
		return props.getProperty(playername);
	}
	
	public void removePlayer(String name){
		props.remove(name);
		save();
	}
	
	public ArrayList<String> getPlayerList(){
		reload();
		ArrayList<String> list = new ArrayList<String>(props.stringPropertyNames());
		list.remove("join-string-format");
		list.remove("log-every-player");
		Collections.sort(list);
		return list;
	}
	
	public Properties getProps(){
		reload();
		return props;
	}

	@Override
	public void initDefaults() {
		addDefaultValue("join-string-format", "&2+ &5%JOINEDPLAYER% &ejoined the game.");
		addDefaultValue("log-every-player", "false");
	}

}
