import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class BasicSwing extends JFrame {
	
	public static void main(String[] args) {
		JFrame theFrame = new JFrame("SwingExamole");
		//theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		theFrame.setSize(200,300);
		theFrame.setVisible(true);


		theFrame.setLayout(new FlowLayout());
		
		theFrame.add(new JLabel("Hello World."));
		
		theFrame.add(new JSlider(JSlider.HORIZONTAL,0,100,50));
		

		JButton jb = new JButton("test");
		ActionListener al = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Clicked2");
							
						}
			};

		JButton ok = new JButton("ok");
		ok.addActionListener(al);
		theFrame.add(ok);
		jb.addActionListener(al);
		jb.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("Clicked");
							System.out.println(e);
						}
			});
		theFrame.add(jb);
		theFrame.add(new JTextField(15));
			

		/*URL uri=getClass().getResource("menu.txt");
		File menu=new File(uri.getPath());
		InputStream input=new FileInputStream(menu);
		BufferedReader readMenu=new BufferedReader(new InputStreamReader(input));*/
		
		theFrame.pack();
		theFrame.setVisible(true);
		
	}
}
