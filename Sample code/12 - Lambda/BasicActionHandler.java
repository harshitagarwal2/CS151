import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class BasicActionHandler extends JFrame {
    public BasicActionHandler() {
        JButton jb = new JButton("test");
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Clicked");
            }
        });

        // TODO: Change to Lambda
        JButton jb1 = new JButton("test Lambda");
        jb1.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Clicked");
        });
        //this.add(jb);
        this.add(jb1);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        BasicActionHandler bah = new BasicActionHandler();
    }
}