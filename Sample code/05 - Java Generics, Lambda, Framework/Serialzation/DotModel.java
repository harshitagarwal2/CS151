// DotModel.java
/*
 Contains the data model for a single dot: x,y, color
 Uses the "bean" getter/setter style, so works with the XML encode/decode.
 Has a zero-arg constructor.
*/
import java.awt.Color;
import java.io.*;

public class DotModel implements Serializable {
private int x;
private int y;
private transient Color color;

public DotModel() {
 x = 0;
 y = 0;
 color = Color.BLACK;
 }


 // standard getters/setters for x/y/color
public Color getColor() {
 return color;
 }
public void setColor(Color color) {
 this.color = color;
 }
public int getX() {
 return x;
 }
public void setX(int x) {
 this.x = x;
 }
public int getY() {
 return y;
 }
public void setY(int y) {
 this.y = y;
 }

 // Convenience setters for clients

 // Moves x,y both the given dx,dy
public void moveBy(int dx, int dy) { 

 x += dx;
 y += dy;
 }

 // Sets both x and y
public void setXY(int x, int y) {
 this.x = x;
 this.y = y;
 }
} 