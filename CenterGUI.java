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
	private JButton submit = new JButton("Submit");
	private Date date = new Date();
	private File file;
	private BufferedWriter out;
	private JLabel err1 = new JLabel("", SwingConstants.CENTER);
	private JLabel err2 = new JLabel("", SwingConstants.CENTER);
	
	public void initFile() throws IOException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
		file = new File(dateFormat.format(date) + ".txt");
		if(!file.exists())
		{
			file.createNewFile();
			out = new BufferedWriter(new FileWriter(file));
			out.write("Visitors this day:");
			out.flush();
		}
		else
		{
			out = new BufferedWriter(new FileWriter(file, true));
		}	
	}
	
	public void write(String n, String e) throws IOException
	{
		out.newLine();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mma 'on' MM/dd/yyyy");
		String output = n + " - " + e + " - " + dateFormat.format(date);
		out.write(output);
		out.flush();
	}
	
	public CenterGUI() throws IOException
	{
		initFile();
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setPreferredSize(new Dimension(450, 250));
		panel.setLayout(new GridLayout(4, 1));
			JPanel p0 = new JPanel();
			p0.setLayout(new GridLayout(2, 1));
				JLabel title = new JLabel("<html><font color=red>L</font><font color=orange>G</font><font color=yellow>B</font><font color=green>T</font><font color=blue>A</font><font color=purple>+</font> Center Sign In</html>", SwingConstants.CENTER);
				title.setFont(new Font("SansSerif", Font.PLAIN, 32));
				p0.add(title);
				p0.add(new JLabel());
			JPanel p1 = new JPanel();
			p1.setLayout(new GridLayout(2, 2, 10, 10));
				JLabel l1 = new JLabel("Please enter your name:", SwingConstants.CENTER);
				l1.setFont(new Font("Serif", Font.PLAIN, 18));
				p1.add(l1);
				name.setFont(new Font("Serif", Font.PLAIN, 18));
				p1.add(name);
				err1.setFont(new Font("Serif", Font.PLAIN, 18));
				err1.setForeground(Color.RED);
				p1.add(err1);
			JPanel p2 = new JPanel();
			p2.setLayout(new GridLayout(2, 2, 10, 10));
				JLabel l2 = new JLabel("Please enter your email:", SwingConstants.CENTER); 
				l2.setFont(new Font("Serif", Font.PLAIN, 18));
				p2.add(l2);
				email.setFont(new Font("Serif", Font.PLAIN, 18));
				p2.add(email);
				err2.setFont(new Font("Serif", Font.PLAIN, 18));
				err2.setForeground(Color.RED);
				p2.add(err2);
			JPanel p3 = new JPanel();
				submit.setPreferredSize(new Dimension(100, 40));
				submit.setFont(new Font("SansSerif", Font.PLAIN, 20));
				submit.addActionListener(new listener());
				p3.add(submit);
			panel.add(p0);
			panel.add(p1);
			panel.add(p2);
			panel.add(p3);
		add(panel);
	}
	
	private class listener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			err1.setText("");
			err2.setText("");
			String n = name.getText();
			String e = email.getText();			
			if(n.isEmpty() || e.isEmpty())
			{
				if(n.isEmpty())
					err1.setText("This field is required.");
				if(e.isEmpty())
					err2.setText("This field is required.");									
			}
			else
			{
				try {
					write(n, e);
				} catch (IOException e1) {}
				name.setText("");
				email.setText("");
			}
		}
	}
}