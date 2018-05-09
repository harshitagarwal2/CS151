import javax.swing.Timer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class TimerTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      final int FIELD_WIDTH = 20;
      final JTextField textField = new JTextField(FIELD_WIDTH);
      frame.setLayout(new FlowLayout());
      frame.add(textField);
      ActionListener listener = new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               Date now = new Date();
               textField.setText(now.toString());
            }
      };
      final int DELAY = 1000;
   // Milliseconds between timer ticks
      Timer t = new Timer(DELAY, listener);
      t.start();
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}