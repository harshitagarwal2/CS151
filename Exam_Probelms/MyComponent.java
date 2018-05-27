package exam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class MyComponent extends JComponent {
	 public MyComponent() {
	 // Create and install the button and label
	 JButton button = new JButton("Hello");
	 add(button);
	 JLabel label = new JLabel();
	 add(label);
	 JTextField text = new JTextField();
	 add(text);
	 
	 button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(10000);
					}catch(InterruptedException e)
					{
					}
					super.run();
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						text.setText(text.getText());
					}
				});
				}
				};
			
			
		}
	});
	 }
	 
	 public static void main(String[] args) {
		MyComponent comp = new MyComponent();
	}
}

