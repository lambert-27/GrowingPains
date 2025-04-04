package view;

import java.awt.Color;

import javax.swing.JButton;

public class GrowingButton {
	/**
	 * Create a button method, encapsulate common code
	 * 
	 * @param name the the displayed on the button
	 * @return the JButton created
	 */
	public static JButton createButton(String name) {
		JButton btn = new JButton(name);
//		Set the imageIcon
		btn.setForeground(Color.WHITE);
		btn.setFont(GrowingPains.getArialFont());
		btn.setBackground(GrowingPains.getColor());
		btn.setBorderPainted(false);
		
		return btn;
	}
}
