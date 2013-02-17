package mapeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

/**
 *
 * @author Patrick
 */
public class MainWindow extends JFrame implements ActionListener {
    private JScrollPane scrollPane;
    private MapPanel panel;
    private JMenuBar mainMenu;

    public MainWindow() {
        this.init();
        this.addComponents();
    }

    private void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1280, 800)); 
        this.setTitle("Map Editor");
    }

    private void addComponents() {
        //local variables
        JMenuItem menuItem;
        
        //MainMenu
        mainMenu = new JMenuBar();
        this.setJMenuBar(mainMenu);
        
        //Menus
        JMenu File = new JMenu("File");
        mainMenu.add(File);
        
        //Menuitems
        menuItem = new JMenuItem("Open");
        menuItem.addActionListener(this);
        File.add(menuItem);
        
        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(this);
        File.add(menuItem);
               
        
        //Map
        panel = new MapPanel();
        panel.addMouseListener(panel);
        panel.addMouseMotionListener(panel);
        panel.addKeyListener(panel);
        
        //Scrollpane
        scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.add(scrollPane);
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("Save".equals(e.getActionCommand())) {
            XMLWriter writer = new XMLWriter();
            writer.generate(this.panel.getCollisionBoxes());
        }
        
        if("Open".equals(e.getActionCommand())) {
            XMLReader reader = new XMLReader();
            this.panel.setCollisionBoxes(reader.read());
            this.panel.repaint();
        }
    }
    
}
