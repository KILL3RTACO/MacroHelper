package com.kill3rtaco.macrohelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.kill3rtaco.macrohelper.options.FrameOptions;

public class MacroHelperMenu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 7762791468896305720L;
	private JMenuItem options, exit;

	public MacroHelperMenu() {
		JMenu main = new JMenu("MacroHelper");
		add(main);
		options = new JMenuItem("Options...");
		options.addActionListener(this);
		main.add(options);
		main.addSeparator();
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		main.add(exit);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == options){
			FrameOptions window = new FrameOptions();
			window.setVisible(true);
		}else if(event.getSource() == exit){
			System.exit(0);
		}
	}

}
