package com.kill3rtaco.macrohelper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.kill3rtaco.macrohelper.panels.PanelPlayerJoined;

public class MacroHelper extends JFrame {

	private static final long serialVersionUID = -1354277099899111515L;
	public static final String version = "1.0.1";
	private static String currentPath = new File(MacroHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath();
	public static final File PLAYERJOIN_PROPERTIES = new File(currentPath + "/config/onPlayerJoin.txt");
	public static final String SCRIPTS_FOLDER = currentPath + "/scripts/";

	public MacroHelper() {
		setJMenuBar(new MacroHelperMenu());
		JTabbedPane tabs = new JTabbedPane();
		tabs.add(new PanelPlayerJoined(), "onPlayerJoin");
		add(tabs);
	}
	
	public static void main(String[] args){
		int x = 800, y = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		MacroHelper window = new MacroHelper();
		window.setTitle("MacroHelper " + version);
		window.setSize(x, y);
		window.setLocation((screen.width - x)/2, (screen.height - y)/2);
		window.setResizable(false);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
