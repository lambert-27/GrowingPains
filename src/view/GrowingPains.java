package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains View class - Contains structure for main area of app

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GrowingPains extends JFrame{
//	ImageIcon holds the path to the image for our icon
//	getClass -> retrieve a reference to the Class obj that represents the GrowingPains class
//	getResource() -> returns the location of the image as a URL
	private final ImageIcon ICON = new ImageIcon(getClass().getResource("growing_pains.png"));
	private final Color GREEN = new Color(24, 65, 15);
	//TODO Add an EXIT button, which is pushed to the bottom of the sidebar
	public GrowingPains() {
		super("Growing Pains");
//		Calls super calls methods (JFrame)
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Both the X and Y are set to be the maximum possible
		setSize(1280, 720);

//		Set the location of the window relative to the screen, null = centered
		setLocationRelativeTo(null);
//		Set the icon image by calling ICON.getImage as setIconImage takes in an Image as an argument
		setIconImage(ICON.getImage());

		topBar();
		sideBar();
		mainContent();
//		Sets all elements within this container to visible
		setVisible(true);
	}
//	TopBar Panel
	public void topBar() {
//		Sets the main content area to a border layout
		getContentPane().setLayout(new BorderLayout());
		JPanel topBar = new JPanel();
//		Sets the background to a white
		topBar.setBackground(Color.WHITE);
//		Places the panel at the NORTH (TOP) of the BorderLayout
		getContentPane().add(topBar, BorderLayout.NORTH);
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
//		Set sidebar to a custom colour (dark grey)
		sideBar.setBackground(GREEN);
//		Set the sideBar panel to the WEST of the BorderLayout
		getContentPane().add(sideBar, BorderLayout.WEST);
//		Set the layout within this panel to be a BOX layout and stack the items along the Y-AXIS (vertical stack)
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		
//		BROWSE BUTTON
		JButton browseBtn = new JButton("Browse");
		browseBtn.setIcon(new ImageIcon(getClass().getResource("pot.png")));
		browseBtn.setForeground(Color.WHITE);
		browseBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		browseBtn.setBorderPainted(false);
		browseBtn.setBackground(GREEN);
		sideBar.add(browseBtn);
		
//		CART BUTTON
		JButton cartBtn = new JButton("Cart");
		cartBtn.setIcon(new ImageIcon(getClass().getResource("checkout.png")));
		cartBtn.setForeground(Color.WHITE);
		cartBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		cartBtn.setBorderPainted(false);
		cartBtn.setBackground(GREEN);
		sideBar.add(cartBtn);
		
//		REMINDER BUTTON
		JButton remindersBtn = new JButton("Reminders");
		remindersBtn.setIcon(new ImageIcon(getClass().getResource("reminder.png")));
		remindersBtn.setForeground(Color.WHITE);
		remindersBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		remindersBtn.setBorderPainted(false);
		remindersBtn.setBackground(GREEN);
		sideBar.add(remindersBtn);
		
	}
	
//	MainContent Panel
	public void mainContent() {
//		mainContent establish the main container for this content area
		JPanel mainContent = new JPanel();
		getContentPane().add(mainContent, BorderLayout.CENTER);
		mainContent.setLayout(new BorderLayout());
//		Container for the title
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(GREEN);
		mainContent.add(titlePanel, BorderLayout.NORTH);
//		Add the title to the panel
		JLabel titleLbl = new JLabel(" ");
		titlePanel.add(titleLbl);
//		The main open space is reserved for the body of the app 
		JPanel mainPanel = new JPanel();
		titlePanel.add(mainPanel, BorderLayout.CENTER);
//		Home screen has a background - acting like a splash screen
		JLabel bckgrndLbl = new JLabel("");
		bckgrndLbl.setIcon(new ImageIcon(getClass().getResource("/view/1.png")));
		mainPanel.add(bckgrndLbl);
	}
}