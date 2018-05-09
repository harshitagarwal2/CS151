import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class FontTest
{
   public static void main(String[] args)
   {
    FontFrame frame = new FontFrame(); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setVisible(true);
    } 
}

/**
   A frame with a text message panel
*/
class FontFrame extends JFrame
{  
   public static final int DEFAULT_WIDTH = 300;
   public static final int DEFAULT_HEIGHT = 200;
   public FontFrame()
   {
setTitle("FontTest"); setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      // add panel to frame
      FontPanel panel = new FontPanel();
      add(panel);
   }
}
/**
   A panel that shows a centered message in a box.
*/
class FontPanel extends JPanel
{
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      String message = "Hello, World! good Morning";
      int size = (getWidth() + getHeight())/50;
      Font f = new Font("Serif", Font.BOLD, size);
      g2.setFont(f);
      // measure the size of the message
      FontRenderContext context = g2.getFontRenderContext(); 
      Rectangle2D bounds = f.getStringBounds(message, context);
      // set (x,y) = top left corner of text
      double x = (getWidth() - bounds.getWidth()) / 2; 
      double y = (getHeight() - bounds.getHeight()) / 2;
      // add ascentleading to y to reach the baseline
      double ascentleading = -bounds.getY();

      double baseY = y + ascentleading;
      // draw the message
      g2.drawString(message, (int) x, (int) baseY); 
      g2.setPaint(Color.RED);
          // draw the baseline
      g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));
      g2.setPaint(Color.GREEN);
      // draw the enclosing rectangle
        Rectangle2D rect =
        new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
      g2.draw(rect);
      

   }
}
