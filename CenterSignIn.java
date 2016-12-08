import javax.swing.JFrame;

public class CenterSignIn 
{	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Center Sign In");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new CenterGUI());
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}