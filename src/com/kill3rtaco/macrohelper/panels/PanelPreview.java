package com.kill3rtaco.macrohelper.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.kill3rtaco.util.StringUtils;

public class PanelPreview extends JPanel implements ActionListener {

	private static final long serialVersionUID = -4609058847929457932L;
	private String text, variable, replace;
	private JTextPane messagePreview;
	private JButton bgBlack, bgWhite;
	
	public PanelPreview(String text, String variable, String replace) {
		this.text = text;
		this.variable = variable;
		this.replace = replace;
	}
	
	//to be called AFTER setBounds()
	public void makePanel(){
		setLayout(null);
		messagePreview = new JTextPane();
		messagePreview.setEditable(false);
		messagePreview.setBackground(Color.BLACK);
		messagePreview.setBounds(0, 0, getWidth(), 40);
		messagePreview.setContentType("text/html");
		messagePreview.setText(StringUtils.minecraftToHtml(text.replaceAll(variable, replace)));
		messagePreview.setBorder(BorderFactory.createLoweredBevelBorder());
		add(messagePreview);
		bgBlack = new JButton("Black Background");
		bgBlack.setBounds(messagePreview.getX() + ((messagePreview.getWidth() - 355) / 2), messagePreview.getY() + messagePreview.getHeight() + 5, 175, 20);
		bgBlack.addActionListener(this);
		add(bgBlack);
		bgWhite = new JButton("White Background");
		bgWhite.setBounds(bgBlack.getX() + bgBlack.getWidth() + 5, bgBlack.getY(), 175, 20);
		bgWhite.addActionListener(this);
		add(bgWhite);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == bgBlack){
			messagePreview.setBackground(Color.BLACK);
		}else if(event.getSource() == bgWhite){
			messagePreview.setBackground(Color.WHITE);
		}
	}
	
	public void update(String text, String variable, String replace){
		if(text.length() >= 2){
			if(StringUtils.getColorForCode(text.charAt(0) + "" + text.charAt(1)) == null) text = "&f" + text;
		}else{
			text = "&f" + text;
		}
		messagePreview.setText(StringUtils.minecraftToHtml(text.replaceAll(variable, replace)));
	}

}
