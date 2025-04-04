package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains BrowsePanel class - Contains structure for BrowsePanel area of app
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import crud.AccessoryCrud;
import crud.PlantCrud;
import model.Catalogue;
import model.DisplayItem;
import model.Item;

/**
 * The BrowsePanel class represents the Browse Panel in the GrowingPains application
 * It displays a grid of products from the Catalogue, allwoing the user
 * to click on any product to view detailed info about it
 * 
 * The BrowsePanel class extends JPanel, it serves as a container for a second
 * panel- gridPanel which uses a GridLayout. When a Product is clicked, 
 * an event is created where it switches to the ProductPanel- a panel
 * detailing more info about an item
 */
public class BrowsePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel gridPanel;
	//Use of JComboBoxes for getting card expiry information
	private JComboBox<String> filterList;
	private String[] filters = {"Select a Filter", "Plant", "Accessory"};
	private JButton filterBtn;
	private JPanel filterPanel;

	/**
	 * Constructs a new BrowsePanel, initialising the layout, title and other functionality like scroll speed
	 * 
	 * @param catalogue the catalogue containing the list of products to be displayed
	 * @param p the ProductPanel that displays product details
	 * @throws SQLException Error for DB Operations
	 */
	public BrowsePanel(Catalogue catalogue, ProductPanel p) throws SQLException {
			setLayout(new BorderLayout());
//			Grid for products, with a horizontal gap between each image of 10px
//			note, rows set to 0 to dynamically let the rows adjust depending on catalogue
			gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
//			Add the title to the NORTH 
			add(new TitlePanel("Browse"), BorderLayout.NORTH);
			
			addFilterComponents(catalogue, p);

//			Disects each Item in the List of products to get all info about the product
			getProducts(catalogue.displayCatalogue(), p);
			
//			Allows the catalogue to be a scrollable page
			JScrollPane scrollPane = new JScrollPane(gridPanel);
//			Sets the increment value for when scrolling w/ mouse to a custom amount (Enables faster scrolling higher the number)
			scrollPane.getVerticalScrollBar().setUnitIncrement(8);
			add(scrollPane, BorderLayout.CENTER);		
			
		}
		
		/**
		 * Retrieves a list of all products and adds them to the grid for display
		 * 
		 * Iterates through each product usng a for each loop, creating the relevant details
		 * about each product in the catalogue
		 * 
		 * @param products a List of all products in the cart
		 * @param p the ProductPanel that displays product details
		 */
		public void getProducts(List<DisplayItem> products, ProductPanel p) {
			Font productFont = new Font("Arial", Font.PLAIN, 24);
//			For each Item in the List of Items
			for (Item product : products){
				String image_path = product.getImgPath();
				ImageIcon icon = new ImageIcon(getClass().getResource(image_path));
				Image img = icon.getImage().getScaledInstance(250,  250,  Image.SCALE_SMOOTH);
				JLabel imgLabel = new JLabel(new ImageIcon(img));
				
//				Add event for clicking on the image of a product, so user can visit a detailed screen of that product
				imgLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
//						//Set the title of the product to that of the currently selected product by calling product.getItemName
						p.setTitle(product.getItemName());
						p.setImage(product.getImgPath());
						p.setDescription(product.getDescription());
						p.setPrice(product.getPrice());
						p.setItem(product);
//						Once the title is set, switch the cardLayout to the "Procuct" card, with the new heading matching the product
						GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Product");
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
		/**
		 * Method which adds the filter JComboBox and the filter JButton to the browsePanel
		 */
		public void addFilterComponents(Catalogue catalogue, ProductPanel p) {
			// Inside the BrowsePanel constructor
			filterList = new JComboBox<String>(filters);
			filterBtn = GrowingButton.createButton("Apply Filter");
			//Increase the size of the filter box
			filterList.setPreferredSize(new Dimension(150, 30));

			//filterPanel used to wrap the filter components in a FlowLayout, pushed to the right (in keeping with other JPanels with buttons)
			filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			filterPanel.add(filterList);

			filterBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					List<DisplayItem> filteredCatalogue = new ArrayList<DisplayItem>();
					try {
						if(filterList.getSelectedItem().toString().equals("Plant")){
							PlantCrud crud = new PlantCrud();
							filteredCatalogue = crud.getAllPlants();
						}
						else if(filterList.getSelectedItem().toString().equals("Accessory")) {
							AccessoryCrud crud = new AccessoryCrud();
							filteredCatalogue = crud.getAllAccessories();
						}
						else
							filteredCatalogue = catalogue.displayCatalogue();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						gridPanel.removeAll();
						getProducts(filteredCatalogue, p);
						revalidate();
						repaint();
					}

				});
			
			filterPanel.add(filterBtn);
			
			// Add the filterPanel to the WEST of main Browse Panel
			add(filterPanel, BorderLayout.SOUTH);
		}
		
		
}

