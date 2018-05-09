//DotPanel.java
/*
 A Panel that draws a series of dots.
 The data model is a list of DotModel objects.
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.io.*;
public class DotPanel extends JPanel {
    private ArrayList<DotModel> dots; // our data model is a list of DotModel objects
    public final int SIZE = 20; // diameter of one dot
    // remember the last dot for mouse tracking
    private int lastX, lastY;
    private DotModel lastDot;
    // Booleans that control how we draw
    private boolean print;
    private boolean smartRepaint;
    private boolean oldRepaint;
    private boolean redPaint;
    // dirty bit = changed from disk version
    private boolean dirty;
    /*
    Creates an empty DotPanel.
    */
    public DotPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        // Subclasing off JPanel, these things work
        setOpaque(true);
        // optimization: set opaque true if we fill 100% of our pixels
        setBackground(Color.white);
        dots = new ArrayList<DotModel>();
        clear();
        // Controls for debugging options
        print = false;
        smartRepaint = false;
        oldRepaint = true; 

        redPaint = false;
        /*
        Mouse Strategy:
        -if the click is not on an existing dot, then make a dot
        -note where the first click is into lastX, lastY
        -then in MouseMotion: compute the delta of this position
        vs. the last
        -Use the delta to change things (not the abs coordinates)
        */
        addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (print) 
                    System.out.println("press:" + e.getX() + " " + e.getY());
                DotModel dotModel = findDot(e.getX(), e.getY());
                if (dotModel == null) { // make a dot if nothing there
                    dotModel = doAdd(e.getX(), e.getY());
                }
                // invariant -- dotModel now determined, one way or another
                // Note the starting setup to compute deltas later
                lastDot = dotModel;
                lastX = e.getX();
                lastY = e.getY();
                // Change color of dot in some cases
                // shift -> change to black
                // double-click -> change to red
                if (e.isShiftDown()) {
                    doSetColor(dotModel, Color.BLACK);
                }
                else if (e.getClickCount() == 2) {
                    doSetColor(dotModel, Color.RED);
                }
            }
        });
        addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (print) 
                    System.out.println("drag:" + e.getX() + " " + e.getY());
                if (lastDot != null) {
                    // compute delta from last point
                    int dx = e.getX()-lastX;
                    int dy = e.getY()-lastY;
                    lastX = e.getX();
                    lastY = e.getY();
                    // For fun, if shift-key is down, multiply dx,dy * -2
                    // demonstrates that the UI appearance of "drag" is a just
                    // a careful illusion
                    if (e.isShiftDown()) {
                        dx *= -2;
                        dy *= -2;
                    }
                    // apply the delta to that dot model
                    doMove(lastDot, dx, dy);
                }
            }
        });

        JButton menuItem = new JButton("Open...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String result = JOptionPane.showInputDialog("File Name", null);
                if (result != null) {
                    File f = new File(result);
                    open(f);
                }
            }
        });
        this.add(menuItem);
        

        menuItem = new JButton("Save...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String result = JOptionPane.showInputDialog("File Name", null);
                if (result != null) {
                    File f = new File(result);
                    save(f);
                }
            }
        });
        this.add(menuItem);

        menuItem = new JButton("Serialize...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String result = JOptionPane.showInputDialog("File Name", null);
                if (result != null) {
                    File f = new File(result);
                    serialize(f);
                }
            }
        });
        this.add(menuItem);

        menuItem = new JButton("Open ser...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String result = JOptionPane.showInputDialog("File Name", null);
                if (result != null) {
                    File f = new File(result);
                    openSer(f);
                }
            }
        });
        this.add(menuItem);

        menuItem = new JButton("Save PNG...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String result = JOptionPane.showInputDialog("File Name", null);
                if (result != null) {
                    File f = new File(result);
                    saveImage(f);
                }
            }
        });
        this.add(menuItem);
        this.setVisible(true);

        
    }

    // Clears out all the data (used by new docs, and for opening docs)
    public void clear() {
        dots.clear();
        dirty = false;
        repaint(); 
    }
    // Default ctor, uses a default size
    public DotPanel() {
        this(300, 300);
    }
    
    /*
    Moves a dot from one place to another.
    Does the necessary repaint.
    This animation can repaint two ways.
    Plain repaint: repaint the whole panel
    Smart repaint: repaint just the old+new bounds of the dot
    */
    public void doMove(DotModel dotModel, int dx, int dy) {
        if (!smartRepaint) {
            // Change the data model, then repaint the whole panel
            dotModel.moveBy(dx, dy);
            repaint();
        }
        else {
            // Smart repaint: old + new
            // Repaint the "old" rectangle
            if (oldRepaint) {
                repaintDot(dotModel);
            }
            // Change the model
            dotModel.moveBy(dx, dy);
            // Repaint the "new" rectangle
            repaintDot(dotModel);
        }
        setDirty(true);
    }

    /*
    Utility -- change the color of the given dot model,
    and then do the needed repaint/setDirty.
    */
    private void doSetColor(DotModel dot, Color color) {
        dot.setColor(color); // change the model
        repaint(); // bookeeping for the view: repaint and set dirty
        setDirty(true);
    }

    /*
    Utility -- does a repaint rect just around one dot. Used
    by smart repaint when dragging a dot.
    */
    private void repaintDot(DotModel dot) {
        repaint(dot.getX()-SIZE/2, dot.getY()-SIZE/2, SIZE+1, SIZE+1);
    }
    
    /*
    Utility -- given a completed dot model, adds it and sets things up.
    This is the bottleneck for adding a dot.
    */
    public void doAdd(DotModel dotModel) {
        dots.add(dotModel);
        repaint();
        setDirty(true);
    }
    
    /*
    Convenience doAdd() that takes an int x,y, adds and returns
    a dot model for it.
    */
    public DotModel doAdd(int x, int y) {
        DotModel dotModel = new DotModel();
        dotModel.setXY(x, y);
        doAdd(dotModel);
        return dotModel; 
    }

    /*
    Finds a dot in the data model that contains
    the given x,y or returns null.
    */
    public DotModel findDot(int x, int y) {
        // Search through the dots in reverse order, so
        // hit topmost ones first.
        for (int i=dots.size()-1; i>=0; i--) {
            DotModel dotModel = dots.get(i);
            int centerX = dotModel.getX();
            int centerY = dotModel.getY();
            // figure x-squared + y-squared, see if it's
            // less than radius squared.
            // trick: don't need to take square root
            int xySquared = (x - centerX)*(x - centerX) +
            (y - centerY)*(y - centerY);
            int radiusSquared = (SIZE/2)*(SIZE/2);
            if (xySquared <= radiusSquared) 
                return dotModel;
        }
        return null;
    }

    /*
    Standard override -- draws all the dots.
    */
    public void paintComponent(Graphics g) {
        // As a JPanel subclass we need call super.paintComponent()
        // so JPanel will draw the white background for us.
        super.paintComponent(g);
        // Go through all the dots, drawing a circle for each
        for (DotModel dotModel : dots) {
            g.setColor(dotModel.getColor());
            g.fillOval(dotModel.getX() - SIZE/2, dotModel.getY() - SIZE/2,
            SIZE, SIZE);
        }
        // Draw the "requested" clip rect in red
        // (this just shows off smart-repaint)
        if (redPaint) {
            Rectangle clip = g.getClipBounds();
            if (clip != null) {
                g.setColor(Color.red);
                g.drawRect(clip.x, clip.y, clip.width-1, clip.height-1);
            }
        }
    }

    /*
    Accessors for the dirty bit.
    */
    public boolean getDirty() {
        return dirty;
    }
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
    /*
    Saves out our state (all the dot models) to the given file.
    Uses Java built-in XMLEncoder.
    */
    public void save(File file) {
        try {
        XMLEncoder xmlOut = new XMLEncoder(
            new BufferedOutputStream(new FileOutputStream(file)));
            DotModel[] dotArray = dots.toArray(new DotModel[0]);
            // Dump that whole array
            xmlOut.writeObject(dotArray);
            xmlOut.writeObject("This is a test");
            // And we're done!
            xmlOut.close();
            setDirty(false);
            // Tip: only clear dirty bit *after* all the things that
            // could fail/throw an exception
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialize(File file) {
        try {
        ObjectOutputStream outStream =
            new ObjectOutputStream(new FileOutputStream(file));
        DotModel[] dotArray = dots.toArray(new DotModel[0]);
        // Dump that whole array
        outStream.writeObject(dotArray);
        outStream.flush();
        // And we're done!
        outStream.close();
        setDirty(false);
        // Tip: only clear dirty bit *after* all the things that
        // could fail/throw an exception
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    Reads in all the dots from the file and set the panel
    to show them.
    */
    public void open(File file) {
        DotModel[] dotArray = null;
        try {
            // Create an XMLDecoder around the file
            XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream(
            new FileInputStream(file))); 
            // Read in the whole array of DotModels
            dotArray = (DotModel[]) xmlIn.readObject();
            String test = (String) xmlIn.readObject();
            System.out.println(test);
            xmlIn.close();
            // Now we have the data, so go ahead and wipe out the old state
            // and put in the new. Goes through the same doAdd() bottleneck
            // used by the UI to add dots.
            // Note that we do this after the operations that might throw.
            clear();
            for(DotModel dm:dotArray) {
                doAdd(dm);
            }
            setDirty(false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Reads in all the dots from the file and set the panel
    to show them.
    */
    public void openSer(File file) {
        DotModel[] dotArray = null;
        try {
            // Create an XMLDecoder around the file
            ObjectInputStream inStream = new ObjectInputStream(
            new FileInputStream(file)); 
            // Read in the whole array of DotModels
            dotArray = (DotModel[]) inStream.readObject();
            inStream.close();
            // Now we have the data, so go ahead and wipe out the old state
            // and put in the new. Goes through the same doAdd() bottleneck
            // used by the UI to add dots.
            // Note that we do this after the operations that might throw.
            clear();
            for(DotModel dm:dotArray) {
                doAdd(dm);
            }
            setDirty(false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }

    }

    /*
    Saves the current appearance of the DotPanel out as a PNG
    in the given file.
    */
    public void saveImage(File file) {
        // Create an image bitmap, same size as ourselves
        BufferedImage image = (BufferedImage) createImage(getWidth(), getHeight());
        // Get Graphics pointing to the bitmap, and call paintAll()
        // This is the RARE case where calling paint() is appropriate
        // (normally the system calls paint()/paintComponent())
        Graphics g = image.getGraphics();
        paintAll(g);
        g.dispose(); // Good but not required--
        // dispose() Graphics you create yourself when done with them.
        try {
            javax.imageio.ImageIO.write(image, "PNG", file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    } 
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        DotPanel panel = new DotPanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);

    }
}