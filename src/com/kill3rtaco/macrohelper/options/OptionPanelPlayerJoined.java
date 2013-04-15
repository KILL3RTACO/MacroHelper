package com.kill3rtaco.macrohelper.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.macrohelper.util.PropertiesUtils;

public class OptionPanelPlayerJoined extends JPanel implements ActionListener {

	private JFrame parent;
	private Properties props;
	private JTextField djsMessage;
	private JCheckBox logDefault;
	private JButton saveButton, closeButton;
	private static final long serialVersionUID = -5021009805573748042L;

	public OptionPanelPlayerJoined(JFrame parent) {
		this.parent = parent;
		try {
			props = new Properties();
			props.load(new FileInputStream(MacroHelper.PLAYERJOIN_PROPERTIES));
		 } catch (IOException e) {
			e.printStackTrace();
		 }
		 makePanel();
	}
	
	private void makePanel(){
		setLayout(null);
		JLabel djsLabel = new JLabel("Default onPlayerJoin message:");
		djsLabel.setBounds(5,  5, 220, 20);
		add(djsLabel);
		djsMessage = new JTextField(props.getProperty("join-string-format"));
		djsMessage.setBounds(djsLabel.getX() + djsLabel.getWidth() + 5, djsLabel.getY() + 1, 300, 20);
		add(djsMessage);
		logDefault = new JCheckBox("Notify me when any player joins the server", Boolean.parseBoolean(props.getProperty("log-every-player")));
		logDefault.setBounds(djsLabel.getX(), djsLabel.getY() + djsLabel.getHeight() + 5, 330, 20);
		add(logDefault);
		saveButton = new JButton("Save");
		saveButton.setBounds((545 - 155) / 2, logDefault.getY() + logDefault.getHeight() + 135, 75, 25);
		saveButton.addActionListener(this);
		add(saveButton);
		closeButton = new JButton("Close");
		closeButton.setBounds(saveButton.getX() + saveButton.getWidth() + 5, saveButton.getY(), 75, 25);
		closeButton.addActionListener(this);
		add(closeButton);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == saveButton){
			props.setProperty("join-string-format", djsMessage.getText());
			props.setProperty("log-every-player", logDefault.isSelected() + "");
			PropertiesUtils.saveProperties(props, MacroHelper.PLAYERJOIN_PROPERTIES, "MacroHelper Options for onPlayerJoin");
		}else if(event.getSource() == closeButton){
			parent.setVisible(false);
		}
	}

}
