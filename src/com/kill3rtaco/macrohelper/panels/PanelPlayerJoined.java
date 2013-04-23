package com.kill3rtaco.macrohelper.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.macrohelper.config.PlayerJoinedConfig;
import com.kill3rtaco.macrohelper.writer.PlayerJoinWriter;

public class PanelPlayerJoined extends JPanel implements ActionListener, DocumentListener, ListSelectionListener {

	private static final long serialVersionUID = -1241939689577548026L;
	private JButton bAddPlayer, bDeletePlayer, bSaveMessage, bWriteMacro;
	private JTextField nameTb, messageTbLeft, messageTbRight;
	private PanelPreview previewLeft, previewRight;
	private JList playerList;
	private PlayerJoinedConfig config;

	public PanelPlayerJoined() {
		config = new PlayerJoinedConfig();
		setLayout(null);
		makePanel();
	}
	
	private void makePanel(){
		JPanel addPlayer = new JPanel();
		addPlayer.setLayout(null);
		addPlayer.setBounds(5, 5, 425, MacroHelper.HEIGHT - MacroHelper.TOP_MARGIN - 275);
		addPlayer.setBorder(BorderFactory.createTitledBorder("Add player"));
		add(addPlayer);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(10, 20, 45, 20);
		addPlayer.add(nameLabel);
		nameTb = new JTextField("player");
		nameTb.setBounds(nameLabel.getX() + nameLabel.getWidth() + 35, nameLabel.getY() + 1, 290, 20);
		nameTb.getDocument().addDocumentListener(this);
		nameTb.addActionListener(this);
		addPlayer.add(nameTb);
		JLabel messageLabel = new JLabel("Message:");
		messageLabel.setBounds(nameLabel.getX(), nameLabel.getY() + nameLabel.getHeight() + 5, 100, 20);
		addPlayer.add(messageLabel);
		messageTbLeft = new JTextField(config.getDefaultJoinString());
		messageTbLeft.setBounds(nameTb.getX(), messageLabel.getY() + 1, nameTb.getWidth(), nameTb.getHeight());
		messageTbLeft.getDocument().addDocumentListener(this);
		messageTbLeft.addActionListener(this);
		addPlayer.add(messageTbLeft);
		bAddPlayer = new JButton("Add");
		bAddPlayer.setBounds((addPlayer.getWidth() - 75) / 2, messageTbLeft.getY() + messageTbLeft.getHeight() + 5, 75, 20);
		bAddPlayer.addActionListener(this);
		addPlayer.add(bAddPlayer);
		previewLeft = new PanelPreview(messageTbLeft.getText(), "%JOINEDPLAYER%", nameTb.getText());
		previewLeft.setBounds(messageLabel.getX(), bAddPlayer.getY() + bAddPlayer.getHeight() + 5, addPlayer.getWidth() - (messageLabel.getX() * 2), 65);
		previewLeft.makePanel();
		addPlayer.add(previewLeft);
		
		JPanel addedPlayers = new JPanel();
		addedPlayers.setLayout(null);
		addedPlayers.setBounds(addPlayer.getX() + addPlayer.getWidth() + 5, 5, 425, MacroHelper.HEIGHT - MacroHelper.TOP_MARGIN - 5);
		addedPlayers.setBorder(BorderFactory.createTitledBorder("Players"));
		add(addedPlayers);
		int offset = (addedPlayers.getWidth() - nameTb.getWidth()) / 2;
		playerList = new JList();
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(playerList);
		scrollPane.setBounds(offset, 20, nameTb.getWidth(), addedPlayers.getHeight() - 200);
		playerList.setBounds(scrollPane.getBounds());
		playerList.addListSelectionListener(this);
		updateList();
		addedPlayers.add(scrollPane);
		JLabel messageLabelRight = new JLabel("Message:");
		messageLabelRight.setBounds(10, addedPlayers.getHeight() - 175, 75, 20);
		addedPlayers.add(messageLabelRight);
		messageTbRight = new JTextField();
		messageTbRight.setBounds(messageLabelRight.getX() + messageLabelRight.getWidth() + 5, messageLabelRight.getY(), 290, 20);
		messageTbRight.getDocument().addDocumentListener(this);
		addedPlayers.add(messageTbRight);
		bSaveMessage = new JButton("Save");
		int width = 85;
		bSaveMessage.setBounds((addedPlayers.getWidth() - (width * 2) + 5) /2, messageTbRight.getY() + messageTbRight.getHeight() + 5, width, 20);
		bSaveMessage.setEnabled(false);
		bSaveMessage.addActionListener(this);
		addedPlayers.add(bSaveMessage);
		bDeletePlayer = new JButton("Delete");
		bDeletePlayer.setBounds(bSaveMessage.getX() + bSaveMessage.getWidth() + 5, bSaveMessage.getY(), width, 20);
		bDeletePlayer.setEnabled(false);
		bDeletePlayer.addActionListener(this);
		addedPlayers.add(bDeletePlayer);
		previewRight = new PanelPreview("", "", "");
		previewRight.setBounds(messageLabelRight.getX(), messageLabelRight.getY() + messageLabelRight.getHeight() + 30, addedPlayers.getWidth() - (messageLabelRight.getX() * 2), 65);
		previewRight.makePanel();
		addedPlayers.add(previewRight);
		bWriteMacro = new JButton("Write macro");
		bWriteMacro.setBounds(previewRight.getX(), addedPlayers.getHeight() - 25, addedPlayers.getWidth() - 20, 20);
		bWriteMacro.addActionListener(this);
		addedPlayers.add(bWriteMacro);
	}
	
	private void updateList(){
		ArrayList<String> list = config.getPlayerList();
		String[] data = new String[list.size()];
		int count = 0;
		for(String s : list){
			data[count++] = s;
		}
		playerList.setListData(data);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == bAddPlayer || event.getSource() == nameTb || event.getSource() == messageTbLeft){
			config.setString(nameTb.getText(), messageTbLeft.getText());
			updateList();
		}else if(event.getSource() == bDeletePlayer){
			config.removePlayer(playerList.getSelectedValue() + "");
			updateList();
		}else if(event.getSource() == bSaveMessage){
			bSaveMessage.setEnabled(false);
			config.setString(playerList.getSelectedValue() + "", messageTbRight.getText());
		}else if(event.getSource() == bWriteMacro){
			new PlayerJoinWriter(config.getProps()).write();
			
		}
	}

	@Override
	public void changedUpdate(DocumentEvent event) {
		//not fired
	}

	@Override
	public void insertUpdate(DocumentEvent event) {
		if(event.getDocument() == nameTb.getDocument() || event.getDocument() == messageTbLeft.getDocument()){
			previewLeft.update(messageTbLeft.getText(), "%JOINEDPLAYER%", nameTb.getText());
			
			if(nameTb.getText().equalsIgnoreCase("join-format-string"))
				bAddPlayer.setEnabled(false);
			else bAddPlayer.setEnabled(true);
		}else if(event.getDocument() == messageTbRight.getDocument() && playerList.getSelectedValue() != null){
			if(playerList.getSelectedValue() != null){
				String name = playerList.getSelectedValue() + "";
				previewRight.update(messageTbRight.getText(), "%JOINEDPLAYER%", name);
				bSaveMessage.setEnabled(true);
			}else{
				bSaveMessage.setEnabled(false);
				bDeletePlayer.setEnabled(false);
				previewRight.update("", "", "");
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent event) {
		if(event.getDocument() == nameTb.getDocument() || event.getDocument() == messageTbLeft.getDocument()){
			previewLeft.update(messageTbLeft.getText(), "%JOINEDPLAYER%", nameTb.getText());
			
			if(nameTb.getText().equalsIgnoreCase("join-format-string") || nameTb.getText().equalsIgnoreCase("log-every-player"))
				bAddPlayer.setEnabled(false);
			else bAddPlayer.setEnabled(true);
		}else if(event.getDocument() == messageTbRight.getDocument()){
			if(playerList.getSelectedValue() != null){
				String name = playerList.getSelectedValue() + "";
				previewRight.update(messageTbRight.getText(), "%JOINEDPLAYER%", name);
				bSaveMessage.setEnabled(true);
			}else{
				bSaveMessage.setEnabled(false);
				bDeletePlayer.setEnabled(false);
				previewRight.update("", "", "");
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if(event.getSource() == playerList){
			if(playerList.getSelectedValue() != null){
				String name = playerList.getSelectedValue() + "";
				messageTbRight.setText(config.getString(name));
				previewRight.update(messageTbRight.getText(), "%JOINEDPLAYER%", name);
				bSaveMessage.setEnabled(false);
				bDeletePlayer.setEnabled(true);
			}else{
				messageTbRight.setText("");
				previewRight.update("", "", "");
			}
		}
	}

}
