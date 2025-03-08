package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains TitlePanel class - Contains structure for the title area of each Panel
//Allows for dynamically creating new titles depending on what product the user is currently on
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {
	private JLabel titleLbl;
	public TitlePanel(String title, Font ARIAL, Color GREEN) {
//		Title Panel shows the user what page they have currently clicked on 
//			Title has a BorderLayout so that we can push the label to the left (west)
			setLayout(new BorderLayout());
			titleLbl = new JLabel();
			setTitle(title);
			setBackground(GREEN);
			titleLbl.setForeground(Color.WHITE);
			titleLbl.setFont(ARIAL);
//			Align the title text to the left
			add(titleLbl, BorderLayout.WEST);
		}

//	Set title calls the label.setText method to set the title to a new one
	public void setTitle(String title) {
		titleLbl.setText(title);

	}
	
}

