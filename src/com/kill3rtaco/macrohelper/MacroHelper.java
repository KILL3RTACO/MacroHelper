package com.kill3rtaco.macrohelper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.kill3rtaco.macrohelper.config.MacroHelperConfig;
import com.kill3rtaco.macrohelper.panels.PanelGeneralHelp;
import com.kill3rtaco.macrohelper.panels.PanelMacroEditor;
import com.kill3rtaco.macrohelper.panels.PanelPlayerJoined;

public class MacroHelper extends JFrame implements ChangeListener {

	private JTabbedPane tabs;
	private static final long serialVersionUID = -1354277099899111515L;
	public static final String version = "1.2.2";
	public static final String currentPath = new File(MacroHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath();
	public static File PROPERTIES = new File(currentPath + "/config/config.txt");
	public static final File GENERAL_HELP_PROPERTIES = new File(currentPath + "/config/general-help.txt");
	public static final File MACRO_EDITOR_PROPERTIES = new File(currentPath + "/config/macro-editor.txt");
	public static final File PLAYERJOIN_PROPERTIES = new File(currentPath + "/config/onPlayerJoin.txt");
	public static final String SCRIPTS_FOLDER = currentPath + "/scripts/";
	public static final String RES_FOLDER = "/com/kill3rtaco/macrohelper/res/";
	public static final int WIDTH = 875;
	public static final int HEIGHT = 525;
	public static final int TOP_MARGIN = 80;
	public static MacroHelperConfig config;

	public MacroHelper() {
		config = new MacroHelperConfig();
		setJMenuBar(new MacroHelperMenu());
		tabs = new JTabbedPane();
		tabs.addTab("General Help", new PanelGeneralHelp());
		tabs.addTab("Macro Editor", new PanelMacroEditor());
		tabs.addTab("onPlayerJoin", new PanelPlayerJoined());
		if(config.getLastIndex() > tabs.getTabCount()) config.setLastIndex(0);
		tabs.setSelectedIndex(config.getLastIndex());
		tabs.addChangeListener(this);
		add(tabs);
	}
	
	public static void main(String[] args){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		MacroHelper window = new MacroHelper();
		window.setTitle("MacroHelper " + version);
		window.setSize(WIDTH, HEIGHT);
		window.setLocation((screen.width - WIDTH)/2, (screen.height - HEIGHT)/2);
		window.setResizable(false);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if(event.getSource() == tabs){
			config.setLastIndex(tabs.getSelectedIndex());
		}
	}

}
