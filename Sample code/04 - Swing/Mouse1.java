import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mouse1 extends JFrame {
	
	JTextField xField, yField;
	
	public Mouse1() {
		xField = new JTextField(5);
		yField = new JTextField(5);
		
		add(new JLabel("X:"));
		add(xField);
		add(new JLabel("Y:"));
		add(yField);
		
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				System.out.println("Dragged!");
			}
			
			public void mouseMoved(MouseEvent e) {
				xField.setText(e.getX() + "");
				yField.setText(e.getY() + "");
			}
						
		});
		
		setLayout(new FlowLayout());
	}
	
	public static void main(String[] args) {
		JFrame frame = new Mouse1();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
