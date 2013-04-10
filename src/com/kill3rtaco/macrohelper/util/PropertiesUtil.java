package com.kill3rtaco.macrohelper.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	public static Properties reloadProperties(File file){
		try {
			Properties props = new Properties();
			props.load(new FileInputStream(file));
			return props;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveProperties(Properties props, File file, String comments){
		try {
			props.store(new FileOutputStream(file), comments);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
