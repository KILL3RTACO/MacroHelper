package com.kill3rtaco.macrohelper.config;

import com.kill3rtaco.macrohelper.MacroHelper;
import com.kill3rtaco.util.TPropertiesConfig;

public class GeneralHelpConfig extends TPropertiesConfig {

	public GeneralHelpConfig() {
		super(MacroHelper.GENERAL_HELP_PROPERTIES, "MacroHelper Options for General Help");
	}

	@Override
	public void initDefaults() {
		addDefaultValue("last-selected-choice", "0");
		addDefaultValue("last-selected-var-choice", "0");
		addDefaultValue("last-selected-event", "0");
		addDefaultValue("last-selected-parameter", "0");
		addDefaultValue("last-selected-statement", "0");
		addDefaultValue("last-selected-var-event", "0");
		addDefaultValue("last-selected-var-general", "0");
		addDefaultValue("last-selected-var-input", "0");
		addDefaultValue("last-selected-var-player", "0");
		addDefaultValue("last-selected-var-event", "0");
		addDefaultValue("last-selected-var-world", "0");
	}
	
	public void setSelectedIndex(String key, int index){
		props.setProperty("last-selected-" + key, index + "");
		save();
	}

}
