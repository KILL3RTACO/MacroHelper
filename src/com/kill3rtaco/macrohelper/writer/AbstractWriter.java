package com.kill3rtaco.macrohelper.writer;

import java.io.File;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.kill3rtaco.macrohelper.MacroHelper;

public abstract class AbstractWriter {

	protected String filename;
	protected File scriptFile;
	protected Properties props;
	
	public AbstractWriter(String filename, Properties props) {
		this.filename = filename;
		this.props = props;
		this.scriptFile = new File(MacroHelper.SCRIPTS_FOLDER + filename);
		if(!scriptFile.exists()) scriptFile.mkdirs();
	}
	
	public abstract void write();
	
	protected void showMacroCreatedMessage(){
		JOptionPane.showMessageDialog(null, "Macro \"" + filename + "\" has been created in scripts folder", "Macro created!", JOptionPane.PLAIN_MESSAGE);
	}

}
