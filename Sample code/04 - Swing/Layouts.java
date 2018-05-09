// Layouts.java
/*
Demonstrates some basic layouts.
*/
import java.awt.*;
import javax.swing.*;
public class Layouts {
    public static void main(String[] args) {
        // Here we setup each frame right from main() --
        // alternately, could do setup in the ctor of each frame.
        // GUI Look And Feel
        // Do this incantation at the start of main() to tell Swing
        // to use the GUI LookAndFeel of the native platform. It's ok
        // to ignore the exception.
        try {
            UIManager.setLookAndFeel(UIManager.     getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        
        // ------
        // 1. Flow Layout
        // Flow layout arranges Left-right top-bottom, like text
        JFrame frame1 = new JFrame("Flow Layout");
        frame1.setLayout(new FlowLayout());
        // Use frame.add() to install components
        frame1.add(new JLabel("Hello World."));
        frame1.add(new JLabel("Another Label."));
        frame1.add(new JLabel("Klaatu Barada Nikto!"));
        frame1.add(new JButton("ok"));
        // Force the frame to size/layout its components
        frame1.pack();
        frame1.setVisible(true);
        
        // ------
        // 2. Box Layout
        JFrame frame2 = new JFrame("Box Layout");
        // Create a component with vertical Box layout,
        // and install it in the frame
        JComponent content2 = new JPanel();
        content2.setLayout(new BoxLayout(content2, BoxLayout.Y_AXIS));
        frame2.setContentPane(content2);

        // add a few components
        frame2.add(new JLabel("Homer"));
        frame2.add(new JLabel("Marge"));
        // add a little spacer
        frame2.add(Box.createVerticalStrut(12));
        frame2.add(new JLabel("Lisa"));
        frame2.add(new JLabel("Bart"));
        frame2.add(new JLabel("Maggie"));
        frame2.pack();
        frame2.setVisible(true);
        

        // ------
        // 3. Border Layout + nested box panel
        JFrame frame3 = new JFrame("Border Layout");
        // Border layout
        frame3.setLayout(new BorderLayout());
        // Add labels around the edge
        frame3.add(new JLabel("North"), BorderLayout.NORTH);
        frame3.add(new JLabel("West"), BorderLayout.WEST);
        frame3.add(new JLabel("South"), BorderLayout.SOUTH);
        // Add a FaceComponent in the center (draws as sort of face in a rect)
        frame3.add(new Face(200, 200), BorderLayout.CENTER);
        // Create a little vertical box JPanel
        // and put it in the EAST with our controls.
        // Make the panel RED. It is front of the window content (light gray)
        // but behind the JButtons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.RED);
        panel.add(new JLabel("Warp Core Breach"));
        panel.add(new JButton("Panic!"));
        panel.add(new JButton("Retry"));
        panel.add(new JButton("Ignore"));
        panel.add(Box.createVerticalStrut(20)); // 20 pixel spacer
        panel.add(new JCheckBox("Ignore Warning Signs"));
        frame3.add(panel, BorderLayout.EAST);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.pack();
        frame3.setVisible(true);
    }
}