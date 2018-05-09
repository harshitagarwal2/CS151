import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;


public class CarShape implements MoveableShape
{
  private int x;
  private int y;
  private int width;
    public CarShape(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }
    public void translate(int dx, int dy) { x+=dx;
        y += dy; }
    public void draw(Graphics2D g2)
    {
        // Create the parts of this car and draw them here.
       Rectangle2D.Double body
             = new Rectangle2D.Double(x, y + width / 6,
                   width - 1, width / 6);
       Ellipse2D.Double frontTire
             = new Ellipse2D.Double(x + width / 6, y + width / 3,width / 6, width / 6);
       g2.draw(body);
       g2.draw(frontTire);
    }
}