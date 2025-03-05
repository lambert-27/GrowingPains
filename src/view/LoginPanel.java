package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains LoginPanel class - Contains structure for LoginPanel area of app


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	Instance variables 
	private JTextField email;
	private JPasswordField pass;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private JButton submit;
	private JButton cancel;
	
//	Constructor, takes in the default FONT and COLOUR scheme
	public LoginPanel(Font ARIAL, Color GREEN) {
//		Using a gridbag layout for flexibility
		gbl = new GridBagLayout();
		setLayout(gbl);
//		GridBagConstraints lets us put some customisation onto our layout
		gbc = new GridBagConstraints();
//		Padding above and below each component in the GridBag container
		gbc.insets = new Insets(0, 0, 5, 5);
		
//		Email Label
		JLabel emailLbl = new JLabel("Email: ");
		//Set pos (Note, start at 0 for x on labels, then for the textfield to appear beside x =1
		gbc.gridx = 0;
		gbc.gridy=5;
		emailLbl.setFont(ARIAL);
		add(emailLbl, gbc);
		
//		Email TextField
		email = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy=5;
		add(email, gbc);
		
		//Password Label
//		Note new line, meaning y pos has increased to 6
		gbc.gridx = 0;
		gbc.gridy=6;
		JLabel passlLbl = new JLabel("Password: ");
		passlLbl.setFont(ARIAL);
		add(passlLbl, gbc);
		
		//Password Field
		pass = new JPasswordField(20);
		pass.addActionListener(new ActionListener(){
			//When the user presses ENTER, let them login
			public void actionPerformed(ActionEvent e) {
				String msg = "Password is : " + new String(pass.getPassword());
				email.setText(msg);
			}
			
		});
		gbc.gridx = 1;
		gbc.gridy=6;
		add(pass, gbc);
		
		//Submit button
		submit = createButton("Login", ARIAL, GREEN);
		
		submit.addMouseListener(new MouseAdapter() {
			//When the user clicks the button to login, let them login
			public void mouseClicked(MouseEvent e) {
				String msg = "Password is : " + new String(pass.getPassword());
				email.setText(msg);
			}

			
		});
		
		//Note skipped a line, y pos = 8;
		gbc.gridx = 0;
		gbc.gridy=8;	
		add(submit, gbc);
		
		//Cancel button
		cancel = createButton("Cancel", ARIAL, GREEN);
		gbc.gridx = 1;
		gbc.gridy = 8;
		add(cancel, gbc);
	}
	
	//Create a button method, encapsulate common code
	public JButton createButton(String name, Font ARIAL, Color GREEN) {
		JButton btn = new JButton(name);
//		Set the imageIcon
		btn.setForeground(Color.WHITE);
		btn.setFont(ARIAL);
		btn.setBackground(GREEN);
		btn.setBorderPainted(false);
		
		return btn;
	}
}
