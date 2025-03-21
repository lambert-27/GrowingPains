package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains TitlePanel class - Contains structure for the title area of each Panel
//Allows for dynamically creating new titles depending on what product the user is currently on
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * The TitlePanel contains the common code for displaying a title on each panel
 * allowing for each product etc to have a Panel title of their own
 * 
 * Holds a JLabel representing the title
 */
public class TitlePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel titleLbl;
	
	/**
	 * Constructs a new JPanel representing the title
	 * 
	 * @param title the title displayed on the panel
	 * @param GREEN the colour used
	 * @param cart the cart containing the list of products to be displayed
	 * @return the title panel
	 */
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

	/**
	 * 	Calls the label.setText() method to set the title to a new one
	 */
	public void setTitle(String title) {
		titleLbl.setText(title);

	}
	
}

