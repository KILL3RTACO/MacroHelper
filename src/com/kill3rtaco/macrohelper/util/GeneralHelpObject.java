package com.kill3rtaco.macrohelper.util;

import javax.swing.ButtonModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class GeneralHelpObject {

	private ButtonModel button;
	private JComboBox comboBox;
	private String textFile, configKey;
	private int index;
	private JComponent[] enabled;
	
	public GeneralHelpObject(ButtonModel button, JComboBox comboBox, String textFile, String configKey, int index, JComponent... enabled) {
		this.button = button;
		this.comboBox = comboBox;
		this.textFile = textFile;
		this.configKey = configKey;
		this.index = index;
		this.enabled = enabled;
	}
	
	public ButtonModel getButtonModel(){
		return button;
	}
	
	public JComboBox getComboBox(){
		return comboBox;
	}
	
	public String getTextFile(){
		return textFile;
	}
	
	public String getConfigKey(){
		return configKey;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setComponentsEnabled(){
		comboBox.setEnabled(true);
		for(JComponent c : enabled){
			c.setEnabled(true);
		}
	}

}
