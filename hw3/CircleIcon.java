
import java.awt.*;
import java.awt.geom.*;
import javax.swing.Icon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harshit
 */
public class CircleIcon implements Icon {
    private int size;
    private Color color= Color.RED;
    
    CircleIcon(int size){
        this.size = size;
    }
    
    public Color getColor(){
        return color;
    }
    
    public void setColor(Color c){
        color =c;
    }
    
    public void increaseSize(){
        int inc = size/10;
        size =  size + inc;
    }
    
    public void reduceSize() {
       int reduce = size/10;
        size = size - reduce;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double shape = new Ellipse2D.Double(x, y, size, size);
        g2.setColor(getColor());
        g2.fill(shape);
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
       return size;
    }
    
}
