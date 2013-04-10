package com.kill3rtaco.macrohelper.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kill3rtaco.macrohelper.MacroHelper;

public class OptionPanelPlayerJoined extends JPanel implements ActionListener {

	private Properties props;
	private JTextField djsMessage;
	private JCheckBox logDefault;
	private JButton saveButton;
	private static final long serialVersionUID = -5021009805573748042L;

	public OptionPanelPlayerJoined() {
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
		saveButton.setBounds((545 - 75) / 2, logDefault.getY() + logDefault.getHeight() + 135, 75, 25);
		saveButton.addActionListener(this);
		add(saveButton);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == saveButton){
			props.setProperty("join-string-format", djsMessage.getText());
			props.setProperty("log-every-player", logDefault.isSelected() + "");
			try {
				props.store(new FileOutputStream(MacroHelper.PLAYERJOIN_PROPERTIES), "MacroHelper Options for onPlayerJoin");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
