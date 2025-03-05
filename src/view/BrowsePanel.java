package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains BrowsePanel class - Contains structure for BrowsePanel area of app
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.DisplayItem;
import model.Item;

public class BrowsePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel gridPanel;
	
	public BrowsePanel(Font ARIAL, Color GREEN, List<DisplayItem> products) {
			setLayout(new BorderLayout());
//			Grid for products, with a horizontal gap between each image of 10px
			gridPanel = new JPanel(new GridLayout(0, 4, 10, 10));
//			Add the title to the NORTH 
			add(titlePanel("Browse", ARIAL, GREEN), BorderLayout.NORTH);
			
			getProducts(products);
			
//			Allows the catalogue to be a scrollable page
			JScrollPane scrollPane = new JScrollPane(gridPanel);
//			Sets the increment value for when scrolling w/ mouse to a custom amount (Enables faster scrolling higher the number)
			scrollPane.getVerticalScrollBar().setUnitIncrement(8);
			add(scrollPane, BorderLayout.CENTER);
					
		}
		
//		Method to get and display all products
		public void getProducts(List<DisplayItem> products) {
			Font productFont = new Font("Arial", Font.PLAIN, 24);
//			For each Item in the List of Items
			for (Item product : products){
				String image_path = product.getImgPath();
				ImageIcon icon = new ImageIcon(getClass().getResource(image_path));
				JLabel imgLabel = new JLabel(icon);
				JLabel nameLabel = new JLabel(product.getItemName(), SwingConstants.CENTER);
				JLabel priceLabel = new JLabel("€" + product.getPrice(), SwingConstants.CENTER);
				priceLabel.setFont(productFont);
				nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
				//Place each product into its own container, as we want to display the image, 
				//product name and price
				JPanel productPanel = new JPanel(new BorderLayout());
				JPanel infoPanel = new JPanel(new BorderLayout());

//				Container just for the text info
				infoPanel.add(nameLabel, BorderLayout.NORTH);
				infoPanel.add(priceLabel, BorderLayout.CENTER);
				
//				Container for the image AND the text
				productPanel.add(imgLabel, BorderLayout.NORTH);
				productPanel.add(infoPanel, BorderLayout.CENTER);
				
				gridPanel.add(productPanel);
			}
		}
		
//		Title Panel shows the user what page they have currently clicked on 
		public JPanel titlePanel(String title, Font ARIAL, Color GREEN) {
//			Title has a BorderLayout so that we can push thte label to the left (west)
			JPanel titlePanel = new JPanel(new BorderLayout());
			titlePanel.setBackground(GREEN);
			JLabel titleLbl = new JLabel(title);
			titleLbl.setForeground(Color.WHITE);
			titleLbl.setFont(ARIAL);
//			Align the title text to the left
			titlePanel.add(titleLbl, BorderLayout.WEST);
			
			return titlePanel;
		}
		
		
}

