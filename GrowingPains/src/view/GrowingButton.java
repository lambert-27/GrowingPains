package view;

import java.awt.Color;

import javax.swing.JButton;
/**
 * Class which holds the method for creating and styling a button according to the design requirements for the system
 */
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
