//Code written by Derrick Swick
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.border.EmptyBorder;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CenterGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTextField name = new JTextField("");
	private JTextField email = new JTextField("");
	private Date date = new Date();
	private BufferedWriter out;
	private JLabel err1 = new JLabel("", JLabel.CENTER);
	private JLabel err2 = new JLabel("", JLabel.CENTER);
	
	public void initFile() throws IOException
	{
		File file = new File(new SimpleDateFormat("MM.dd.yyyy").format(date) + ".txt");		
		if(!file.exists())
		{
			file.createNewFile();
			out = new BufferedWriter(new FileWriter(file));
			out.write("Visitors this day:");
			out.flush();
		}
		else
			out = new BufferedWriter(new FileWriter(file, true));
	}
		
	public CenterGUI()
	{
		try {
			initFile();
		} catch ( IOException e ) {}
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 10, 10, 10));
		panel.setPreferredSize(new Dimension(450, 250));
		panel.setLayout(new GridLayout(4, 1, 10, 10));
			JPanel p1 = new JPanel();
				JLabel title = new JLabel("<html><font color=red>L</font><font color=orange>G</font><font color=yellow>B</font><font color=green>T</font><font color=blue>A</font><font color=purple>+</font> Center Sign In</html>", JLabel.CENTER);
				title.setFont(new Font("SansSerif", Font.PLAIN, 32));
				p1.add(title);
			JPanel p2 = new JPanel();
			p2.setLayout(new GridLayout(2, 2, 10, 10));
				JLabel l1 = new JLabel("Please enter your name:", JLabel.CENTER);
				l1.setFont(new Font("Serif", Font.PLAIN, 18));
				p2.add(l1);
				name.addKeyListener(new listener());
				name.setFont(new Font("Serif", Font.PLAIN, 18));
				p2.add(name);
				err1.setFont(new Font("Serif", Font.PLAIN, 18));
				err1.setForeground(Color.RED);
				p2.add(err1);
			JPanel p3 = new JPanel();
			p3.setLayout(new GridLayout(2, 2, 10, 10));
				JLabel l2 = new JLabel("Please enter your email:", JLabel.CENTER); 
				l2.setFont(new Font("Serif", Font.PLAIN, 18));
				p3.add(l2);
				email.setFont(new Font("Serif", Font.PLAIN, 18));
				email.addKeyListener(new listener());
				p3.add(email);
				err2.setFont(new Font("Serif", Font.PLAIN, 18));
				err2.setForeground(Color.RED);
				p3.add(err2);
			JPanel p4 = new JPanel();
				JButton submit = new JButton("Submit");
				submit.setPreferredSize(new Dimension(100, 40));
				submit.setFont(new Font("SansSerif", Font.PLAIN, 20));
				submit.addActionListener(new listener());
				p4.add(submit);
			panel.add(p1);
			panel.add(p2);
			panel.add(p3);
			panel.add(p4);
		add(panel);
	}
	
	private class listener implements ActionListener, KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			if(e.getSource() == name && e.getKeyCode() == KeyEvent.VK_ENTER)
				email.requestFocus();
			else if(e.getSource() == email && e.getKeyCode() == KeyEvent.VK_ENTER)
				actionPerformed(null);
		}
		
		public void actionPerformed(ActionEvent event)
		{
			err1.setText("");
			err2.setText("");
			String n = name.getText();
			String e = email.getText();

			if(!e.contains("@") || (!e.endsWith(".com") && !e.endsWith(".org") && !e.endsWith(".edu") && !e.endsWith(".gov") && !e.endsWith(".net")))
				err2.setText("Invalid email.");
			
			if(n.isEmpty() || e.isEmpty() || err2.getText() == "Invalid email.")
			{
				if(n.isEmpty())
					err1.setText("This field is required.");
				if(e.isEmpty())
					err2.setText("This field is required.");
			}
			else
			{
				try {
					out.newLine();
					SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mma 'on' MM/dd/yyyy");
					String output = n + " - " + e + " - " + dateFormat.format(date);
					out.write(output);
					out.flush();
				} catch (IOException e1) {}
				name.setText("");
				email.setText("");
				name.requestFocus();
			}
		}

		@Override
		public void keyReleased( KeyEvent e ) {}
		@Override
		public void keyTyped( KeyEvent e ) {}
	}
}