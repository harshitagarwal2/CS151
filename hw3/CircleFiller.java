/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author harshit
 */
public class CircleFiller {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        
        CircleIcon icon = new CircleIcon(100);
        JLabel label = new JLabel(icon);
        JButton red = new JButton("Red Button");
        JButton green = new JButton("Green Button");
        JButton blue = new JButton("Blue Button");
        JButton zoomIn = new JButton("ZoomIN Button");
        JButton zoomOut = new JButton("ZoomOut Button");
       
        frame.add(blue);
        frame.add(red);
        frame.add(green);
        frame.add(zoomIn);
        frame.add(zoomOut); 
        frame.add(label);
        
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icon.setColor(Color.GREEN);
                label.repaint();
            }
        });
        
        blue.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                icon.setColor(Color.BLUE);
                label.repaint();
            }
        });

            
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icon.setColor(Color.RED);
                label.repaint();
            }
        });
            
        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icon.reduceSize();
                label.repaint();
            }
        });
        
        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icon.increaseSize();
                label.repaint();
            }
        });



        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
 
}
