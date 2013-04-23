package com.kill3rtaco.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties reloadProperties(File file){
		try {
			return reloadPropertiesWithStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Properties reloadPropertiesWithStream(InputStream stream){
		try {
			Properties props = new Properties();
			props.load(stream);
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
