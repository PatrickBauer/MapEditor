package mapeditor;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Patrick
 */
public class MainWindow extends JFrame {
    JScrollPane scrollPane;
    MapPanel panel;

    public MainWindow() {
        this.init();
        this.addComponents();
    }

    private void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1280, 800));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void addComponents() {
        panel = new MapPanel();
        panel.addMouseListener(panel);
        panel.addMouseMotionListener(panel);
        panel.addKeyListener(panel);
        
        scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panel.setScrollPane(scrollPane);
        
        
        this.add(scrollPane);
    }
    
    
}
