package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains BrowsePanel class - Contains structure for BrowsePanel area of app
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.Catalogue;
import model.DisplayItem;
import model.Item;

public class BrowsePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel gridPanel;

	
	public BrowsePanel(Font ARIAL, Color GREEN, Catalogue catalogue, CardLayout cardLayout, JPanel mainContent, ProductPanel p) throws SQLException {
			setLayout(new BorderLayout());
//			Grid for products, with a horizontal gap between each image of 10px
			gridPanel = new JPanel(new GridLayout(0, 4, 10, 10));
//			Add the title to the NORTH 
			add(new TitlePanel("Browse", ARIAL, GREEN), BorderLayout.NORTH);
			
//			Disects each Item in the List of products to get all info about the product
			getProducts(catalogue.displayCatalogue(), cardLayout, mainContent, ARIAL, GREEN, p);
			
//			Allows the catalogue to be a scrollable page
			JScrollPane scrollPane = new JScrollPane(gridPanel);
//			Sets the increment value for when scrolling w/ mouse to a custom amount (Enables faster scrolling higher the number)
			scrollPane.getVerticalScrollBar().setUnitIncrement(8);
			add(scrollPane, BorderLayout.CENTER);
			
					
		}
		
//		Method to get and display all products
		public void getProducts(List<DisplayItem> products, CardLayout cardLayout, JPanel mainContent, Font ARIAL, Color GREEN, ProductPanel p) {
			Font productFont = new Font("Arial", Font.PLAIN, 24);
//			For each Item in the List of Items
			for (Item product : products){
				String image_path = product.getImgPath();
				ImageIcon icon = new ImageIcon(getClass().getResource(image_path));
				JLabel imgLabel = new JLabel(icon);
				
//				Add event for clicking on the image of a product, so user can visit a detailed screen of that product
				imgLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
//						//Set the title of the product to that of the currently selected product by calling product.getItemName
						p.setTitle(product.getItemName());
						p.setImage(product.getImgPath());
						p.setDescription(product.getDescription());
						p.setPrice(product.getPrice());
//						Once the title is set, switch the cardLayout to the "Procuct" card, with the new heading matching the product
						cardLayout.show(mainContent, "Product");
					}
					
				});
				
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
		

		
		
}

