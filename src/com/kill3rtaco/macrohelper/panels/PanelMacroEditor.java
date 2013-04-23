package com.kill3rtaco.macrohelper.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.macrohelper.config.MacroEditorConfig;
import com.kill3rtaco.macrohelper.util.METimer;
import com.kill3rtaco.util.StringUtils;

public class PanelMacroEditor extends JPanel implements ActionListener, DocumentListener {

	private JTextPane textArea;
	private StyledDocument doc;
	private JButton save, saveAs, open;
	private File currentFile;
	private static final long serialVersionUID = -8182670043993570583L;
	public long lastEdited;
	public boolean needsHighlight;
	public METimer timer;
	private MacroEditorConfig config;
	public static final String OPERATORS = "$()=;,";

	public PanelMacroEditor() {
		config = new MacroEditorConfig();
		setLayout(null);
		makePanel();
	}
	
	private void makePanel(){
		doc = (StyledDocument) new DefaultStyledDocument();
		textArea = new JTextPane(doc);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(5, 5, MacroHelper.WIDTH - 15, MacroHelper.HEIGHT - MacroHelper.TOP_MARGIN - 15 - 30);
//		textArea.setText("LOG"); lastEdited = System.currentTimeMillis(); needsHighlight = true;
		textArea.setBounds(scrollPane.getBounds());
		textArea.getDocument().addDocumentListener(this);
//		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		add(scrollPane);
		
		open = new JButton("Open...");
		open.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 5, 100, 25);
		open.addActionListener(this);
		add(open);
		
		save = new JButton("Save");
		save.setBounds(open.getX() + open.getWidth() + 5, open.getY(), 100, 25);
		save.addActionListener(this);
		save.setEnabled(false);
		add(save);
		
		saveAs = new JButton("Save as...");
		saveAs.setBounds(save.getX() + save.getWidth() + 5, save.getY(), 125, 25);
		saveAs.addActionListener(this);
		saveAs.setEnabled(false);
		add(saveAs);
		
		timer = new METimer(this);
		timer.start();
	}
	
	public void highlightSyntax(){
		ArrayList<String> keywords = config.getAllKeywords();
		ArrayList<String> vars = config.getAllVariables();
		
		Style defaultStyle = textArea.addStyle("default", null);
		StyleConstants.setForeground(defaultStyle, config.getColor("default"));
		
		Style keyword = textArea.addStyle("keyword", null);
		StyleConstants.setForeground(keyword, config.getColor("keyword"));
		
		Style operator = textArea.addStyle("operators", null);
		StyleConstants.setForeground(operator, config.getColor("operator"));
		
		Style string = textArea.addStyle("string", null);
		StyleConstants.setForeground(string, config.getColor("string"));

		Style stringVar = textArea.addStyle("string-var", null);
		StyleConstants.setForeground(stringVar, config.getColor("string-var"));
		
		Style var = textArea.addStyle("var", null);
		StyleConstants.setForeground(var, config.getColor("var"));
	
		doc.setCharacterAttributes(0, textArea.getText().length(), defaultStyle, true); //set everything back to black
		
		String text = textArea.getText();
		//begin syntax highlighting
		String[] split = text.split("(\n|\\s)");
		int position = 0;
		
		//first pass, keywords and variables
		for(String s : split){
			for(String kw : keywords){
				if(s.toLowerCase().startsWith(kw.toLowerCase()) 
						&& ((s.length() > kw.length() 
						&& (s.charAt(kw.length()) == '(' || s.charAt(kw.length()) == ';')) 
						|| s.length() == kw.length())){
					doc.setCharacterAttributes(position, kw.length(), keyword, true);
					break;
				}
			}
			
			for(String str : vars){
				if(!s.contains("%") && StringUtils.containsIgnoreCase(s, str)){
					int index = StringUtils.indexOfIgnoreCase(s, str);
					int end = index + str.length();
//					System.out.println(index + ", " + end);
					if(index == 0 || (((s.charAt(index - 1) == '('))
							&& end == s.length() || (s.charAt(end) == ')')))
					doc.setCharacterAttributes(position + index, str.length(), var, true);
					break;
				}
			}
			
			position += s.length() + 1; //next token
		}
		
		//second pass, operators
		for(int i=0; i<text.length(); i++){
			for(int j=0; j<OPERATORS.length(); j++){
				if(text.charAt(i) == OPERATORS.charAt(j)){
					doc.setCharacterAttributes(i, 1, operator, true);
					break;
				}
			}
		}
		
		//third pass, strings
		for(int i=0; i<text.length(); i++){
			if(text.charAt(i) == '"'){
				int length = StringUtils.lengthUntilNextDoubleQuote(text, i);
				doc.setCharacterAttributes(i, length, string, true);
				i += length - 1;
			}
		}
		
		//fourth pass, variables inside strings
		boolean inString = false;
		for(int i=0; i<text.length(); i++){
			if(text.charAt(i) == '"') inString = !inString;
			if(inString && text.charAt(i) == '%'){
				int length = StringUtils.lengthUntilNextPercent(text, i);
				if(length == -1 || length <= 2) continue;
				String potential = text.substring(i + 1, i + length - 1);
				for(String s : vars){
					if(potential.equalsIgnoreCase(s)){
						doc.setCharacterAttributes(i, length, stringVar, true);
						break;
					}
				}
				i += length - 1;
			}
		}
		needsHighlight = false;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		textUpdate(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		textUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		textUpdate(e);
	}
	
	private void textUpdate(DocumentEvent event){
		lastEdited = System.currentTimeMillis();
		needsHighlight = true;
		save.setEnabled(true);
		saveAs.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == open){
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
			int val = fc.showOpenDialog(textArea);
			if(val == JFileChooser.APPROVE_OPTION){
				currentFile = fc.getSelectedFile();
				String text = StringUtils.getContents(currentFile);
				textArea.setText(text);
			}
		}else if(event.getSource() == save){
			if(currentFile != null){
				save(textArea.getText(), currentFile);
			}else{
				saveAs();
			}
		}else if(event.getSource() == saveAs){
			saveAs();
		}
	}

	private void save(String text, File file){
		try {
			if(file.exists()) file.delete();
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.append(text);
			writer.close();
			currentFile = file;
			save.setEnabled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveAs(){
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
		int val = fc.showSaveDialog(textArea);
		if(val == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			if(!file.getName().matches(".*\\.txt")) file = new File(file.getAbsolutePath() + ".txt");
			save(textArea.getText(), file);
		}
	}
	
}
