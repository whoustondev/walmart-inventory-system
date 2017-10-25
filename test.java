
import javax.swing.*;
import java.awt.*;


public class test
{
	public static void main(String[] args)
	{

		
	    Object[] options1 = { "Login", "Cancel"};

	    	JPanel panel = new JPanel();
	    	panel.setBackground(Color.lightGray);
	    	JTextField username = new JTextField(10);
	    	JTextField password = new JTextField(10);
	    	panel.add(Box.createVerticalStrut(20)); // a spacer
	    	panel.add(new Label("Username:"));
	    	panel.add(username);
	    
	    	panel.add(new Label("Password:"));
	    	
	    	panel.add(password);
	    	int result = JOptionPane.showOptionDialog(null, panel, "Login Page", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
	    	if (result == JOptionPane.YES_OPTION)
	    	{
	    		JOptionPane.showMessageDialog(null, username.getText());
	    	
	    	}
	    	else 
	    	{
	    		System.exit(0);
	    		
	    	}
	    	System.exit(0);

	}
		
}