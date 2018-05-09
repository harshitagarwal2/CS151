import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
   This program implements an animation that moves a car shape.
*/
class MyPanel extends JPanel
{  MoveableShape s;
public MyPanel(MoveableShape m)
   {  s = m; }
   public void paintComponent(Graphics g)
   {    super.paintComponent(g);
        s.draw((Graphics2D)g);
   }
}
public class AnimationTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      int DEFAULT_WIDTH = 400;
      int DEFAULT_HEIGHT = 400;
      frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      int CAR_WIDTH = 100;
      final MoveableShape shape = new CarShape(0, 0, CAR_WIDTH);
      final JPanel panel = new MyPanel(shape);
      frame.add(panel);
      final int DELAY = 100;
      // Milliseconds between timer ticks
      Timer t = new Timer(DELAY, new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               shape.translate(1, 0);
               panel.repaint();
               System.out.println(event);
            }
         });
      t.start();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}