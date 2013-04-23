package com.kill3rtaco.macrohelper.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.macrohelper.config.GeneralHelpConfig;
import com.kill3rtaco.macrohelper.util.GeneralHelpObject;
import com.kill3rtaco.util.PropertiesUtils;
import com.kill3rtaco.util.StringUtils;

public class PanelGeneralHelp extends JPanel implements ActionListener {

	private GeneralHelpConfig config;
	private static final long serialVersionUID = -2299522295477865156L;
	private JRadioButton statements, controlFlow, variables, vGeneral, vEvent, vInput, vPlayer, vWorld, parameters, events;
	private JComboBox statementList, vgList, veList, viList, vpList, vwList, parameterList, eventList;
	private JTextArea helpTextArea;
	private ButtonGroup helpGroup, vGroup;

	public PanelGeneralHelp() {
		config = new GeneralHelpConfig();
		setLayout(null);
		makePanel();
	}
	
	private void makePanel(){
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(null);
		choicePanel.setBounds(5, 5, 475, 440);
		choicePanel.setBorder(BorderFactory.createTitledBorder("View help on..."));
		add(choicePanel);
		
		helpGroup = new ButtonGroup();
		controlFlow = new JRadioButton("Control flow commands");
		controlFlow.setBounds(10, 20, 200, 20);
		controlFlow.addActionListener(this);
		helpGroup.add(controlFlow);
		choicePanel.add(controlFlow);
		events = new JRadioButton("Events");
		events.setBounds(controlFlow.getX(), controlFlow.getY() + controlFlow.getHeight() + 5, 100, 20);
		events.setToolTipText("Events are fired when a particular action has occured. For example, the onPlayerJoined event is fired when a player joins the server.");
		events.addActionListener(this);
		helpGroup.add(events);
		choicePanel.add(events);
		eventList = new JComboBox(getData("/events.txt"));
		eventList.setBounds(events.getX() + events.getWidth() + 25, events.getY(), 330, 20);
		eventList.addActionListener(this);
		choicePanel.add(eventList);
		parameters = new JRadioButton("Parameters");
		parameters.setBounds(events.getX(), events.getY() + events.getHeight() + 5, 115, 20);
		parameters.setToolTipText("Parameters are like variables. The value of the variable is determined by user action.");
		parameters.addActionListener(this);
		helpGroup.add(parameters);
		choicePanel.add(parameters);
		parameterList = new JComboBox(getData("/parameters.txt"));
		parameterList.setBounds(eventList.getX(), parameters.getY(), eventList.getWidth(), eventList.getHeight());
		parameterList.addActionListener(this);
		choicePanel.add(parameterList);
		statements = new JRadioButton("Statement");
		statements.setBounds(parameters.getX(), parameters.getY() + parameters.getHeight() + 5, 100, 20);
		statements.setToolTipText("A statement is a single line of code. Example:\n LOG(\"This is a statement\");");
		statements.addActionListener(this);
		helpGroup.add(statements);
		choicePanel.add(statements);
		statementList = new JComboBox(getData("/statements.txt"));
		statementList.setBounds(parameterList.getX(), statements.getY(), parameterList.getWidth(), parameterList.getHeight());
		statementList.setEditable(false);
		statementList.addActionListener(this);
		choicePanel.add(statementList);
		variables = new JRadioButton("Variable");
		variables.setBounds(statements.getX(), statements.getY() + statements.getHeight() + 5, 100, 20);
		variables.setToolTipText("A variable is a special word or letter that is replaced with a value (like in math).");
		variables.addActionListener(this);
		helpGroup.add(variables);
		choicePanel.add(variables);
		vGroup = new ButtonGroup();
		vEvent = new JRadioButton("Event");
		vEvent.setBounds(variables.getX() + 25, variables.getY() + variables.getHeight() + 5, 85, 20);
		vEvent.addActionListener(this);
		vGroup.add(vEvent);
		choicePanel.add(vEvent);
		veList = new JComboBox(getData("/vevent.txt"));
		veList.setBounds(statementList.getX(), vEvent.getY(), statementList.getWidth(), statementList.getHeight());
		veList.addActionListener(this);
		choicePanel.add(veList);
		vGeneral = new JRadioButton("General");
		vGeneral.setBounds(vEvent.getX(), vEvent.getY() + vEvent.getHeight() + 5, 85, 20);
		vGeneral.addActionListener(this);
		vGroup.add(vGeneral);
		choicePanel.add(vGeneral);
		vgList = new JComboBox(getData("/vgeneral.txt"));
		vgList.setBounds(veList.getX(), vGeneral.getY(), veList.getWidth(), veList.getHeight());
		vgList.addActionListener(this);
		choicePanel.add(vgList);
		vInput = new JRadioButton("Input");
		vInput.setBounds(vGeneral.getX(), vGeneral.getY() + vGeneral.getHeight() + 5, 85, 20);
		vInput.addActionListener(this);
		vGroup.add(vInput);
		choicePanel.add(vInput);
		viList = new JComboBox(getData("/vinput.txt"));
		viList.setBounds(vgList.getX(), vInput.getY(), vgList.getWidth(), vgList.getHeight());
		viList.addActionListener(this);
		choicePanel.add(viList);
		vPlayer = new JRadioButton("Player");
		vPlayer.setBounds(vInput.getX(), vInput.getY() + vInput.getHeight() + 5, 85, 20);
		vPlayer.addActionListener(this);
		vGroup.add(vPlayer);
		choicePanel.add(vPlayer);
		vpList = new JComboBox(getData("/vplayer.txt"));
		vpList.setBounds(viList.getX(), vPlayer.getY(), viList.getWidth(), viList.getHeight());
		vpList.addActionListener(this);
		choicePanel.add(vpList);
		vWorld = new JRadioButton("World");
		vWorld.setBounds(vPlayer.getX(), vPlayer.getY() + vPlayer.getHeight() + 5, 85, 25);
		vWorld.addActionListener(this);
		vGroup.add(vWorld);
		choicePanel.add(vWorld);
		vwList = new JComboBox(getData("/vworld.txt"));
		vwList.setBounds(vpList.getX(), vWorld.getY(), vpList.getWidth(), vpList.getHeight());
		vwList.addActionListener(this);
		choicePanel.add(vwList);
		
		helpTextArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(helpTextArea);
		scrollPane.setBounds(choicePanel.getX() + choicePanel.getWidth() + 5, 5, 380, 440);
		helpTextArea.setBounds(scrollPane.getBounds());
		helpTextArea.setEditable(false);
		helpTextArea.setLineWrap(true);
		helpTextArea.setWrapStyleWord(true);
		add(scrollPane);
		
		int lastSelectedChoice = getSelectedIndex("choice");
		int lastSelectedVarChoice = getSelectedIndex("var-choice");
		int lastSelectedEvent = getSelectedIndex("event");
		int lastSelectedParameter = getSelectedIndex("parameter");
		int lastSelectedStatement = getSelectedIndex("statement");
		int lastSelectedVarEvent = getSelectedIndex("var-event");
		int lastSelectedVarGeneral = getSelectedIndex("var-general");
		int lastSelectedVarInput = getSelectedIndex("var-input");
		int lastSelectedVarPlayer = getSelectedIndex("var-player");
		int lastSelectedVarWorld = getSelectedIndex("var-world");
		helpGroup.setSelected(getButtonModelFromGroup(helpGroup, lastSelectedChoice), true);
		vGroup.setSelected(getButtonModelFromGroup(vGroup, lastSelectedVarChoice), true);
		eventList.setSelectedIndex(lastSelectedEvent);
		parameterList.setSelectedIndex(lastSelectedParameter);
		statementList.setSelectedIndex(lastSelectedStatement);
		veList.setSelectedIndex(lastSelectedVarEvent);
		vgList.setSelectedIndex(lastSelectedVarGeneral);
		viList.setSelectedIndex(lastSelectedVarInput);
		vpList.setSelectedIndex(lastSelectedVarPlayer);
		vwList.setSelectedIndex(lastSelectedVarWorld);
		event(helpGroup.getSelection());
	}
	
	private int getSelectedIndex(String key){
		try{
			int index = Integer.parseInt(config.getProps().getProperty("last-selected-" + key));
			if(key.equalsIgnoreCase("choice")){
				if(index > helpGroup.getButtonCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}else if(key.equalsIgnoreCase("var-choice")){
				if(index > vGroup.getButtonCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}else if(key.equalsIgnoreCase("event")){
				if(index > eventList.getItemCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}else if(key.equalsIgnoreCase("parameter")){
				if(index > parameterList.getItemCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}else if(key.equalsIgnoreCase("statement")){
				if(index > statementList.getItemCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}else if(key.equalsIgnoreCase("var-general")){
				if(index > vgList.getItemCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}else if(key.equalsIgnoreCase("var-input")){
				if(index > viList.getItemCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}else if(key.equalsIgnoreCase("var-player")){
				if(index > vpList.getItemCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}else if(key.equalsIgnoreCase("var-world")){
				if(index > vwList.getItemCount()){
					config.setSelectedIndex(key, 0);
					index = 0;
				}
			}
			return index;
		} catch (NumberFormatException e) {
			config.setSelectedIndex(key, 0);
			return 0;
		}
	}
	
	private Object[] getData(String file){
		String resFolder = "/com/kill3rtaco/macrohelper/res";
		Properties properties = PropertiesUtils.reloadPropertiesWithStream(getClass().getResourceAsStream(resFolder + file));
		ArrayList<String> list = new ArrayList<String>(properties.stringPropertyNames());
		Collections.sort(list);
		for(int i=0; i<list.size(); i++){
			if(list.get(i).contains("-") || list.get(i).contains("'")){
				String old = list.get(i);
				list.remove(i);
				list.add(i, old.replaceAll("-", " ").replaceAll("'", ":"));
			}
		}
		return list.toArray();
	}
	
	private ButtonModel getButtonModelFromGroup(ButtonGroup group, int index){
		int i = 0;
		Enumeration<AbstractButton> buttons = group.getElements();
		while(buttons.hasMoreElements()){
			i++;
			ButtonModel model = buttons.nextElement().getModel();
			if(i - 1 == index) return model;
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JRadioButton){
			event(((JRadioButton) event.getSource()).getModel());
		}else{			
			event(event.getSource());
		}
	}
	
	private void event(Object source){
		ArrayList<GeneralHelpObject> list = new ArrayList<GeneralHelpObject>();
		list.add(new GeneralHelpObject(events.getModel(), eventList, "/events.txt", "choice", 1));
		list.add(new GeneralHelpObject(parameters.getModel(), parameterList, "/parameters.txt", "choice", 2));
		list.add(new GeneralHelpObject(statements.getModel(), statementList, "/statements.txt", "choice", 3));
		list.add(new GeneralHelpObject(vEvent.getModel(), veList, "/vevent.txt", "var-choice", 0, vGeneral, vEvent, vInput, vPlayer, vWorld));
		list.add(new GeneralHelpObject(vGeneral.getModel(), vgList, "/vgeneral.txt", "var-choice", 1, vGeneral, vEvent, vInput, vPlayer, vWorld));
		list.add(new GeneralHelpObject(vInput.getModel(), viList, "/vinput.txt", "var-choice", 2, vGeneral, vEvent, vInput, vPlayer, vWorld));
		list.add(new GeneralHelpObject(vPlayer.getModel(), vpList, "/vplayer.txt", "var-choice", 3, vGeneral, vEvent, vInput, vPlayer, vWorld));
		list.add(new GeneralHelpObject(vWorld.getModel(), vwList, "/vworld.txt", "var-choice", 4, vGeneral, vEvent, vInput, vPlayer, vWorld));
		if(source instanceof ButtonModel){
			ButtonModel model = (ButtonModel) source;
			if(model == controlFlow.getModel()){
				InputStream is = getClass().getResourceAsStream(MacroHelper.RES_FOLDER + "blocks.txt");
				helpTextArea.setText(StringUtils.getContents(is));
				helpTextArea.setCaretPosition(0);
				disableComponents();
				config.setSelectedIndex("choice", 0);
			}else if(model == variables.getModel()){
				setEnabledComponents(vGeneral, vInput, vPlayer, vWorld);
				config.setSelectedIndex("choice", 4);
				event(vGroup.getSelection());
			}else{
				for(GeneralHelpObject o : list){
					if(o.getButtonModel() == model){
						setHelpText(o.getTextFile(), o.getComboBox());
						disableComponents();
						o.setComponentsEnabled();
						config.setSelectedIndex(o.getConfigKey(), o.getIndex());
						break;
					}
				}
			}
		}else if(source == eventList){
			setHelpText("/events.txt", eventList);
			config.setSelectedIndex("event", eventList.getSelectedIndex());
		}else if(source == parameterList){
			setHelpText("/parameters.txt", parameterList);
			config.setSelectedIndex("parameter", parameterList.getSelectedIndex());
		}else if(source == statementList){
			setHelpText("/statements.txt", statementList);
			config.setSelectedIndex("statement", statementList.getSelectedIndex());
		}else if(source == veList){
			setHelpText("/vevent.txt", veList);
			config.setSelectedIndex("var-event", veList.getSelectedIndex());
		}else if(source == vgList){
			setHelpText("/vgeneral.txt", vgList);
			config.setSelectedIndex("var-general", vgList.getSelectedIndex());
		}else if(source == viList){
			setHelpText("/vinput.txt", viList);
			config.setSelectedIndex("var-input", viList.getSelectedIndex());
		}else if(source == vpList){
			setHelpText("/vplayer.txt", vpList);
			config.setSelectedIndex("var-player", vpList.getSelectedIndex());
		}else if(source == vwList){
			setHelpText("/vworld.txt", vwList);
			config.setSelectedIndex("var-world", vwList.getSelectedIndex());
		}
	}
	
	private void setHelpText(String file, JComboBox comboBox){
		String resFolder = "/com/kill3rtaco/macrohelper/res";
		Properties p = PropertiesUtils.reloadPropertiesWithStream(getClass().getResourceAsStream(resFolder + file));
		helpTextArea.setText(p.getProperty((comboBox.getSelectedItem() + "").replaceAll(" ", "-").replaceAll(":", "'")));
	}
	
	private void disableComponents(){
		statementList.setEnabled(false);
		vGeneral.setEnabled(false);
		vgList.setEnabled(false);
		vEvent.setEnabled(false);
		veList.setEnabled(false);
		vInput.setEnabled(false);
		viList.setEnabled(false);
		vPlayer.setEnabled(false);
		vpList.setEnabled(false);
		vWorld.setEnabled(false);
		vwList.setEnabled(false);
		parameterList.setEnabled(false);
		eventList.setEnabled(false);
	}
	
	private void setEnabledComponents(JComponent... components){
		disableComponents();
		for(JComponent c : components){
			c.setEnabled(true);
		}
	}

}
