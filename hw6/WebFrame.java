import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class WebFrame extends JFrame {

    public static void main(String[] args) {
        WebFrame frame = new WebFrame();
        DefaultTableModel model = new DefaultTableModel(new String[] { "url", "status"}, 0); 
        JTable table = new JTable(model); 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        model.addRow(new String[] { "http://www.sjsu.edu/cs/", ""});

        JScrollPane scrollpane = new JScrollPane(table); 
        scrollpane.setPreferredSize(new Dimension(600,300)); 
        frame.add(scrollpane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}