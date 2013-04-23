package com.kill3rtaco.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public abstract class TPropertiesConfig {

	protected Properties props;
	protected File propsFile;
	protected String comment;
	
	public TPropertiesConfig(File propsFile, String comment){
		this.props = new Properties();
		this.propsFile = propsFile;
		this.comment = comment;
		reload();
	}
	
	public void addDefaultValue(String key, String value){
		if(props.getProperty(key) == null)
			props.setProperty(key, value);
	}
	
	public void reload(){
		try {
			if(!propsFile.exists())
				propsFile.getParentFile().mkdirs();
			propsFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		props = PropertiesUtils.reloadProperties(propsFile);
		initDefaults();
		save();
		props = PropertiesUtils.reloadProperties(propsFile);
	}
	
	public abstract void initDefaults();
	
	public void save(){
		PropertiesUtils.saveProperties(props, propsFile, comment);
	}
	
	public Properties getProps(){
		reload();
		return props;
	}

}
