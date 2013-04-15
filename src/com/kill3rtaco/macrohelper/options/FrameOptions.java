package com.kill3rtaco.macrohelper.options;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class FrameOptions extends JFrame {

	private static final long serialVersionUID = -3864171799544135713L;

	public FrameOptions() {
		int x = 545, y = 275;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("MacroHelper Options");
		setSize(x, y);
		setLocation((screen.width - x)/2, (screen.height - y)/2);
		setResizable(false);
		 //tabbed pane add
		JTabbedPane tabs = new JTabbedPane();
		add(tabs);
		tabs.add(new OptionPanelPlayerJoined(this), "onPlayerJoined");
	}

}
