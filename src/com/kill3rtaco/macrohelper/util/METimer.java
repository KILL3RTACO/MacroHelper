package com.kill3rtaco.macrohelper.util;

import com.kill3rtaco.macrohelper.panels.PanelMacroEditor;

public class METimer extends Thread {

	private PanelMacroEditor editor;
	
	public METimer(PanelMacroEditor editor) {
		this.editor = editor;
	}

	@Override
	public void run() {
		long interval = 50;
		while(true){
			long now = System.currentTimeMillis();
//			System.out.println(now);
			if(editor.needsHighlight){
				if(now - editor.lastEdited >= interval){
					editor.highlightSyntax();
				}
			}
			try {
				sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
