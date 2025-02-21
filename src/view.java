package view;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class view extends JFrame{
	private final ImageIcon ICON = new ImageIcon("growing_pains.png");
	public view() {
		super("Growing Pains");
//		Calls super calls methods (JFrame)
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Both the X and Y are set to be the maximum possible
		setSize(1280, 720);
		setVisible(true);
//		Set the location of the window relative to the screen, null = centered
		setLocationRelativeTo(null);
		setIconImage(ICON.getImage());
	}
	
}
