package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains View class - Contains structure for main area of app

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GrowingPains extends JFrame{
//	ImageIcon holds the path to the image for our icon
//	getClass -> retrieve a reference to the Class obj that represents the GrowingPains class
//	getResource() -> returns the location of the image as a URL
	private final ImageIcon ICON = new ImageIcon(getClass().getResource("growing_pains.png"));
//	Custom green colour 
	private final Color GREEN = new Color(24, 65, 15);
//	Common font used for buttons and headings
	private final Font ARIAL = new Font("Arial", Font.PLAIN, 20);
	//TODO Add an EXIT button, which is pushed to the bottom of the sidebar
	public GrowingPains() {
//		Invokes the superclass constructor (JFrame), passing in a String as the title
		super("Growing Pains");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1700, 900);
//		Set the location of the window relative to the screen, null = centered
		setLocationRelativeTo(null);
//		Set the icon image by calling ICON.getImage as setIconImage takes in an Image as an argument
		setIconImage(ICON.getImage());

//		Methods to create respective panels for GUI
		topBar();
		sideBar();
		mainContent();
//		Sets all elements within this container to visible
		setVisible(true);
	}
//	TopBar Panel
	public void topBar() {
		JPanel topBar = new JPanel();
//		Sets the background to a white
		topBar.setBackground(Color.WHITE);
//		Places the panel at the NORTH (TOP) of the BorderLayout
		add(topBar, BorderLayout.NORTH);
//		The layout being used within this panel is a FLOW LAYOUT, aligning the items to the LEFT
		topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel titleLbl = new JLabel("Growing Pains");
//		Give an icon to the label
		titleLbl.setIcon(new ImageIcon(getClass().getResource("growing pains_1.png")));
//		Set font and fontsize
		titleLbl.setFont(new Font("Arial", Font.PLAIN, 33));
		topBar.add(titleLbl);
	}
//	SideBar Panel
	public void sideBar() {
		JPanel sideBar = new JPanel();
//		Set sidebar to custom green
		sideBar.setBackground(GREEN);
//		Set the sideBar panel to the WEST of the BorderLayout
		add(sideBar, BorderLayout.WEST);
//		Set the layout within this panel to be a BOX layout and stack the items along the Y-AXIS (vertical stack)
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

//		BROWSE BUTTON
		JButton browseBtn = createButton("Browse", "pot.png");
		sideBar.add(browseBtn);

//		CART BUTTON
		JButton cartBtn = createButton("Cart", "checkout.png");
		sideBar.add(cartBtn);
		
//		REMINDER BUTTON
		JButton remindersBtn = createButton("Reminders", "reminder.png");
		sideBar.add(remindersBtn);
		
	}
	
//	Method that encapsulates all common code for creating a button, takes in 2 paramaters
//	one for the name, the other containing the name of the ImageIcon
	public JButton createButton(String name, String icon) {
		
		JButton btn = new JButton(name);
//		Set the imageIcon
		btn.setIcon(new ImageIcon(getClass().getResource(icon)));
		btn.setForeground(Color.WHITE);
		btn.setFont(ARIAL);
//		Remove border and set bg for transparent effect
		btn.setBorderPainted(false);
		btn.setBackground(GREEN);

		return btn;
	}
//	MainContent Panel
	public void mainContent() {
//		mainContent establish the main container for this content area, with a CARDLayout
//		This allows us to have a series of JPanels stacked ontop of one another like a deck of cards
		JPanel mainContent = new JPanel(new CardLayout());
		getContentPane().add(mainContent, BorderLayout.CENTER);
		
		mainContent.add(welcomePanel());
		mainContent.add(browsePanel());
		mainContent.add(cartPanel());
		mainContent.add(reminderPanel());

	}
	
//	Creates the welcomePanel splash screen
	public JPanel welcomePanel() {
		JPanel welcomePanel = new JPanel(new BorderLayout());
		
//		Container for the title
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(GREEN);
//		Add the title to the panel
		JLabel titleLbl = new JLabel(" ");
		titlePanel.add(titleLbl);

//		Home screen has a background - acting like a splash screen
		JLabel bckgrndLbl = new JLabel((new ImageIcon(getClass().getResource("bg.png"))));
//		
		bckgrndLbl.setHorizontalAlignment(SwingConstants.CENTER);
//		Add the backgroundLbl to the center, the titleLabel to the NORTH
		welcomePanel.add(bckgrndLbl, BorderLayout.CENTER);
		welcomePanel.add(titlePanel, BorderLayout.NORTH);
		
		return welcomePanel;
	}
	
//	Creates and returns the browsePanel
	public JPanel browsePanel() {
		JPanel browsePanel = new JPanel(new BorderLayout());
		
//		Title has a BorderLayout so that we can push thte label to the left (west)
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(GREEN);
		JLabel titleLbl = new JLabel("Browse");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(ARIAL);
//		Align the title text to the left
		titlePanel.add(titleLbl, BorderLayout.WEST);
		
//		Add the title to the NORTH 
		browsePanel.add(titlePanel, BorderLayout.NORTH);
		
		return browsePanel;
	}
	
//	Creates and returns the browsePanel
	public JPanel cartPanel() {
		JPanel cartPanel = new JPanel(new BorderLayout());
		
//		Title has a BorderLayout so that we can push thte label to the left (west)
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(GREEN);
		JLabel titleLbl = new JLabel("Cart");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(ARIAL);
//		Align the title text to the left
		titlePanel.add(titleLbl, BorderLayout.WEST);
		
//		Add the title to the NORTH 
		cartPanel.add(titlePanel, BorderLayout.NORTH);
		
		return cartPanel;
	}
	
//	Creates and returns the browsePanel
	public JPanel reminderPanel() {
		JPanel reminderPanel = new JPanel(new BorderLayout());
		
//		Title has a BorderLayout so that we can push thte label to the left (west)
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(GREEN);
		JLabel titleLbl = new JLabel("Reminders");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(ARIAL);
//		Align the title text to the left
		titlePanel.add(titleLbl, BorderLayout.WEST);
		
//		Add the title to the NORTH 
		reminderPanel.add(titlePanel, BorderLayout.NORTH);
		
		return reminderPanel;
	}
}