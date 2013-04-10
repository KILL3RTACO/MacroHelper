package com.kill3rtaco.macrohelper.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

public class PlayerJoinWriter extends AbstractWriter {

	public PlayerJoinWriter(Properties props) {
		super("onPlayerJoined.txt", props);
	}

	@Override
	public void write() {
		ArrayList<String> players = new ArrayList<String>(props.stringPropertyNames());
		players.remove("join-string-format");
		players.remove("log-every-player");
		Collections.sort(players, new Comparator<String>(){
			public int compare(String s1, String s2) {
				return s1.toLowerCase().compareTo(s2.toLowerCase());
			}
		});
		
		if(scriptFile.exists()) scriptFile.delete();
		try {
			scriptFile.createNewFile();
			FileWriter writer = new FileWriter(scriptFile);
			writer.append("$${\n");
			for(int i=0; i<players.size(); i++){
				if(i > 0) writer.append("ELSE; ");
				writer.append("IF(JOINEDPLAYER = \"" + players.get(i) + "\");\n");
				writer.append("\tLOG(\"" + props.getProperty(players.get(i)) + "\");\n");
			}
			if(props.getProperty("log-every-player").equalsIgnoreCase("true"))
				writer.append("ELSE; \n\tLOG(\"" + props.getProperty("join-string-format") + "\");\n");
			for(int i=0; i<players.size(); i++){
				writer.append("ENDIF;\n");
			}
			writer.append("}$$");
			writer.close();
			showMacroCreatedMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
