package com.kill3rtaco.macrohelper.options;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kill3rtaco.macrohelper.config.MacroEditorConfig;
import com.kill3rtaco.macrohelper.panels.PanelMacroEditor;
import com.kill3rtaco.util.StringUtils;

public class OptionMacroEditor extends JPanel implements ActionListener {

	private static final long serialVersionUID = 4400493849793035226L;
	private JButton dcButton, restoreDc, kwcButton, restoreKwc, ocButton, restoreOc, scButton,
					restoreSc, vButton, restoreVc, svcButton, restoreSvc, closeButton;
	private JFrame parent;
	private MacroEditorConfig config;
	private String cs, bText;

	public OptionMacroEditor(JFrame parent) {
		this.parent = parent;
		config = new MacroEditorConfig();
		setLayout(null);
		makePanel();
	}

	private void makePanel() {
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(null);
		colorPanel.setBounds(5, 5, 450, FrameOptions.HEIGHT - FrameOptions.TOP_MARGIN - 90);
		colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
		add(colorPanel);
		
		JLabel dcLabel = new JLabel("Default:");
		dcLabel.setBounds(10, 20, 75, 20);
		dcLabel.setToolTipText("The default color.");
		colorPanel.add(dcLabel);
		setColor("default");
		dcButton = new JButton(bText);
		dcButton.setBounds(colorPanel.getWidth() - 240, dcLabel.getY(), 75, 20);
		dcButton.addActionListener(this);
		colorPanel.add(dcButton);
		restoreDc = new JButton("Restore default");
		restoreDc.setBounds(dcButton.getX() + dcButton.getWidth() + 5, dcButton.getY(), 150, 20);
		restoreDc.addActionListener(this);
		colorPanel.add(restoreDc);
		
		JLabel kwcLabel = new JLabel("Keywords:");
		kwcLabel.setBounds(dcLabel.getX(), dcLabel.getY() + dcLabel.getHeight() + 5, 75, 20);
		kwcLabel.setToolTipText("Also called Statements. These include functions like LOG();");
		colorPanel.add(kwcLabel);
		setColor("keyword");
		kwcButton = new JButton(bText);
		kwcButton.setBounds(dcButton.getX(), kwcLabel.getY(), 75, 20);
		kwcButton.addActionListener(this);
		colorPanel.add(kwcButton);
		restoreKwc = new JButton("Restore default");
		restoreKwc.setBounds(kwcButton.getX() + kwcButton.getWidth() + 5, kwcButton.getY(), 150, 20);
		restoreKwc.addActionListener(this);
		colorPanel.add(restoreKwc);
		
		JLabel ocLabel = new JLabel("Operators:");
		ocLabel.setBounds(kwcLabel.getX(), kwcLabel.getY() + kwcLabel.getHeight() + 5, 100, 20);
		ocLabel.setToolTipText("Any of the following characters: " + PanelMacroEditor.OPERATORS);
		colorPanel.add(ocLabel);
		setColor("operator");
		ocButton = new JButton(bText);
		ocButton.setBounds(kwcButton.getX(), kwcButton.getY() + kwcButton.getHeight() + 5, 75, 20);
		ocButton.addActionListener(this);
		colorPanel.add(ocButton);
		restoreOc = new JButton("Restore default");
		restoreOc.setBounds(ocButton.getX() + ocButton.getWidth() + 5, ocButton.getY(), 150, 20);
		restoreOc.addActionListener(this);
		colorPanel.add(restoreOc);
		
		JLabel scLabel = new JLabel("Strings:");
		scLabel.setBounds(ocLabel.getX(), ocLabel.getY() + ocLabel.getHeight() + 5, 75, 20);
		scLabel.setToolTipText("Words enclosed in double-quotes (\")");
		colorPanel.add(scLabel);
		setColor("string");
		scButton = new JButton(bText);
		scButton.setBounds(ocButton.getX(), scLabel.getY(), 75, 20);
		scButton.addActionListener(this);
		colorPanel.add(scButton);
		restoreSc = new JButton("Restore default");
		restoreSc.setBounds(scButton.getX() + scButton.getWidth() + 5, scButton.getY(), 150, 20);
		restoreSc.addActionListener(this);
		colorPanel.add(restoreSc);
		
		JLabel vLabel = new JLabel("Variables");
		vLabel.setBounds(scLabel.getX(), scLabel.getY() + scLabel.getHeight() + 5, 80, 20);
		vLabel.setToolTipText("Variables when used outside of strings");
		colorPanel.add(vLabel);
		setColor("var");
		vButton = new JButton(bText);
		vButton.setBounds(scButton.getX(), vLabel.getY(), 75, 20);
		vButton.addActionListener(this);
		colorPanel.add(vButton);
		restoreVc = new JButton("Restore default");
		restoreVc.setBounds(vButton.getX() + vButton.getWidth() + 5, vButton.getY(), 150, 20);
		restoreVc.addActionListener(this);
		colorPanel.add(restoreVc);
		
		JLabel svcLabel = new JLabel("Variables (inside strings)");
		svcLabel.setBounds(vLabel.getX(), vLabel.getY() + vLabel.getHeight() + 5, 200, 20);
		svcLabel.setToolTipText("Variables when used inside strings, enclosed in percent signs (%)");
		colorPanel.add(svcLabel);
		setColor("string-var");
		svcButton = new JButton(bText);
		svcButton.setBounds(vButton.getX(), svcLabel.getY(), 75, 20);
		svcButton.addActionListener(this);
		colorPanel.add(svcButton);
		restoreSvc = new JButton("Restore default");
		restoreSvc.setBounds(svcButton.getX() + svcButton.getWidth() + 5, svcButton.getY(), 150, 20);
		restoreSvc.addActionListener(this);
		colorPanel.add(restoreSvc);
		
		closeButton = new JButton("Close");
		closeButton.setBounds((FrameOptions.WIDTH - 75) / 2, FrameOptions.HEIGHT - FrameOptions.TOP_MARGIN - 25, 75, 25);
		closeButton.addActionListener(this);
		add(closeButton);
	}
	
	private void restoreDefault(String key, JButton button){
		config.setColor(key, config.getDefaultColor(key));
		setColor(key);
		button.setText(bText);
	}
	
	private void setColor(String key){
		String start = StringUtils.START_HTML;
		String end = StringUtils.END_HTML;
		cs = config.getColorAsHtmlString(key);
		bText = start + "<p style='color: " + cs + "; background-color: " + cs + "'>colour</p>" + end;
	}
	
	private void chooseColor(String key, JButton button){
		Color color = JColorChooser.showDialog(parent, "Choose a color", config.getColor(key));
		if(color != null){
			config.setColor(key, color);
			setColor(key);
			button.setText(bText);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == closeButton){
			parent.setVisible(false);
		}else if(event.getSource() == dcButton){
			chooseColor("default", dcButton);
		}else if(event.getSource() == kwcButton){
			chooseColor("keyword", kwcButton);
		}else if(event.getSource() == ocButton){
			chooseColor("operator", ocButton);
		}else if(event.getSource() == scButton){
			chooseColor("string", scButton);
		}else if(event.getSource() == vButton){
			chooseColor("var", vButton);
		}else if(event.getSource() == svcButton){
			chooseColor("string-var", svcButton);
		}else if(event.getSource() == restoreDc){
			restoreDefault("default", dcButton);
		}else if(event.getSource() == restoreKwc){
			restoreDefault("keyword", kwcButton);
		}else if(event.getSource() == restoreOc){
			restoreDefault("operator", ocButton);
		}else if(event.getSource() == restoreSc){
			restoreDefault("string", scButton);
		}else if(event.getSource() == restoreVc){
			restoreDefault("var", vButton);
		}else if(event.getSource() == restoreSvc){
			restoreDefault("string-var", svcButton);
		}
	}

}
