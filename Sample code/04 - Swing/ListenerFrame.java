import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

/*
Demonstrates bringing up a frame with a couple of buttons in it.
One button uses a named inner class listener, and one
uses an anonymous inner class listener
*/
public class ListenerFrame extends JFrame {
    private JLabel label;
    /*
    When the Yay button is clicked, we append a "!" to
    the JLabel. This inner class expresses that code.
    */
    private class YayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String text = label.getText();
            label.setText(text + "!");
            System.out.println("yay!!!");
            // note that we can access ivars like 'label'
        }
    }
    public ListenerFrame() {
        super("ListenerFrame");
        setLayout(new FlowLayout());
        /*
        Example 1 --
        Create a Yay button and a label in a little panel.
        Connect the button to a YayListener.
        */
        JPanel panel = new JPanel();
        add(panel); // Add the panel to the frame content
        
        // Add some things to the panel
        JButton yay = new JButton("Yay!");
        label = new JLabel("Woo Hoo");
        panel.add(yay);
        panel.add(label);
        ActionListener listener = new YayListener(); // create listener
        yay.addActionListener(listener); // register it with button
       
        /*
        Example 2 --
        Create a button that beeps.
        Similar effect to above, but does it all in one step
        using an anonymous inner class.
        */
        JButton beep = new JButton("Beep!");
        add(beep);
        beep.addActionListener(
        // Create anon inner class ActionListener on the fly
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //label.setText("Beep!");
                //System.out.println("beep!");
                Toolkit.getDefaultToolkit().beep();
            }
        });

        beep.addActionListener(
        // Create anon inner class ActionListener on the fly
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Beep!");
                System.out.println("beep!");
                //Toolkit.getDefaultToolkit().beep();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
        new ListenerFrame();
    }
}









