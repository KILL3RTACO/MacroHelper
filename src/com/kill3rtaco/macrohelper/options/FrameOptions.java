package com.kill3rtaco.macrohelper.options;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.util.PropertiesUtils;

public class FrameOptions extends JFrame implements ChangeListener {

	private File propsFile;
	private Properties props;
	private JTabbedPane tabs;
	private static final long serialVersionUID = -3864171799544135713L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 325;
	public static final int TOP_MARGIN = 60;

	public FrameOptions() {
		propsFile = new File(MacroHelper.currentPath + "/config/config.txt");
		int lastIndex = 0;
		try {
			if(!propsFile.exists())
				propsFile.getParentFile().mkdirs();
			propsFile.createNewFile();
			props =  PropertiesUtils.reloadProperties(propsFile);
			if(props.getProperty("last-selected-option-index") == null)
				props.setProperty("last-selected-option-index", "0");
			try{
				lastIndex = Integer.parseInt(props.getProperty("last-selected-option-index"));
			} catch (NumberFormatException e){
				props.setProperty("last-selected-option-index", "0");
			}
			PropertiesUtils.saveProperties(props, propsFile, "MacroHelper Options for onPlayerJoined");
		} catch (IOException e) {
			 e.printStackTrace();
		}
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("MacroHelper Options");
		setSize(WIDTH, HEIGHT);
		setLocation((screen.width - WIDTH)/2, (screen.height - HEIGHT)/2);
		setResizable(false);
		tabs = new JTabbedPane();
		tabs.addTab("Macro Editor", new OptionMacroEditor(this));
		tabs.addTab("onPlayerJoined", new OptionPanelPlayerJoined(this));
		tabs.addChangeListener(this);
		tabs.setSelectedIndex(lastIndex);
		add(tabs);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if(event.getSource() == tabs){
			props.setProperty("last-selected-option-index", tabs.getSelectedIndex() + "");
			PropertiesUtils.saveProperties(props, propsFile, "MacroHelper Options");
		}
	}

}
