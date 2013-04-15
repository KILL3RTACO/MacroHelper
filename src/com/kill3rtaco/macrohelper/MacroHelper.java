package com.kill3rtaco.macrohelper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.kill3rtaco.macrohelper.panels.PanelGeneralHelp;
import com.kill3rtaco.macrohelper.panels.PanelPlayerJoined;
import com.kill3rtaco.macrohelper.util.PropertiesUtils;

public class MacroHelper extends JFrame implements ChangeListener {

	private File propsFile;
	private Properties props;
	private JTabbedPane tabs;
	private static final long serialVersionUID = -1354277099899111515L;
	public static final String version = "1.1.0";
	private static String currentPath = new File(MacroHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath();
	public static final File PLAYERJOIN_PROPERTIES = new File(currentPath + "/config/onPlayerJoin.txt");
	public static final File GENERAL_HELP_PROPERTIES = new File(currentPath + "/config/general-help.txt");
	public static final String SCRIPTS_FOLDER = currentPath + "/scripts/";

	public MacroHelper() {
		propsFile = new File(currentPath + "/config/config.txt");
		int lastIndex = 0;
		try {
			if(!propsFile.exists())
				propsFile.getParentFile().mkdirs();
			 	propsFile.createNewFile();
			props =  PropertiesUtils.reloadProperties(propsFile);
			if(props.getProperty("last-selected-index") == null)
				 props.setProperty("last-selected-index", "0");
			try{
				lastIndex = Integer.parseInt(props.getProperty("last-selected-index"));
			} catch (NumberFormatException e){
				
			}
			PropertiesUtils.saveProperties(props, propsFile, "MacroHelper Options for onPlayerJoined");
		} catch (IOException e) {
			 e.printStackTrace();
		}
		setJMenuBar(new MacroHelperMenu());
		tabs = new JTabbedPane();
		tabs.addTab("General Help", new PanelGeneralHelp());
		tabs.addTab("onPlayerJoin", new PanelPlayerJoined());
		if(lastIndex > tabs.getTabCount()) lastIndex = 0;
		tabs.setSelectedIndex(lastIndex);
		tabs.addChangeListener(this);
		add(tabs);
	}
	
	public static void main(String[] args){
		int x = 875, y = 525;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		MacroHelper window = new MacroHelper();
		window.setTitle("MacroHelper " + version);
		window.setSize(x, y);
		window.setLocation((screen.width - x)/2, (screen.height - y)/2);
		window.setResizable(false);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if(event.getSource() == tabs){
			props.setProperty("last-selected-index", tabs.getSelectedIndex() + "");
			PropertiesUtils.saveProperties(props, propsFile, "MacroHelper Options");
		}
	}

}
