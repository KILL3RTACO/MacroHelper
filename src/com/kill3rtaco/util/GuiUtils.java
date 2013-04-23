package com.kill3rtaco.util;

import java.awt.Toolkit;

import javax.swing.KeyStroke;

public class GuiUtils {

	//if on mac, use the command key
	public static KeyStroke getMenuKeyStroke(int key){
		return KeyStroke.getKeyStroke(key, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
	}

}
