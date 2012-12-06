package mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Patrick
 */
class MapPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private BufferedImage map = null;
    private Integer currentX = null, currentY = null; 
    private Integer mouseX = null, mouseY = null; 
    private ArrayList<Rectangle> collisionBoxes = new ArrayList<>();
    private double scale = 1.0;

    public MapPanel() {
        super();
        this.setFocusable(true);
        
        //load map
       try {                
          map = ImageIO.read(new File("C:\\Users\\Patrick\\Documents\\GameDev\\Game\\resources\\maps\\1.png"));
          this.setPreferredSize(new Dimension(this.map.getWidth(), this.map.getHeight()));
       } catch (IOException ex) {
            System.out.println(ex);
       }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if(this.scale > 1.0) {
            g2d.scale(scale, scale);
        }
        
        if(this.map != null) {
            g2d.drawImage(map, 0, 0, null);
        }
        
        if(currentX != null) {
            g2d.setColor(Color.GREEN);
            g2d.drawRect(currentX, currentY, mouseX - currentX, mouseY - currentY);
        }
        
        for(Rectangle rect : this.collisionBoxes) {
            g2d.setColor(Color.red);
            g2d.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        } 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            this.currentX = this.mouseX = (int) (e.getX() / this.scale);
            this.currentY = this.mouseY = (int) (e.getY() / this.scale);
        }
    
        if(e.getButton() == MouseEvent.BUTTON3){
            for(int i = this.collisionBoxes.size() - 1; i >= 0 ; i--) {
                if(this.collisionBoxes.get(i).contains((int) (e.getX() / this.scale), (int) (e.getY() / this.scale))) {
                    this.collisionBoxes.remove(i);
                    break;
                }
            }
        }
        
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && this.currentX != null) {
            if((int) (e.getX() / this.scale) - this.currentX > 4 && (int) (e.getY() / this.scale) - this.currentY > 4){
                this.collisionBoxes.add(new Rectangle(this.currentX, this.currentY, (int) (e.getX() / this.scale) - this.currentX, (int) (e.getY() / this.scale) - this.currentY));
            }
            this.currentX = this.currentY = null;
        }
        
        this.repaint();
    }

    @Override
    public void mouseDragged (MouseEvent e) {
        this.mouseX = (int) (e.getX() / this.scale);
        this.mouseY = (int) (e.getY() / this.scale);
        
        this.repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == '+') {
            this.scale += 0.1;
        }
        
        if(e.getKeyChar() == '-' && this.scale > 1.0) {
            this.scale -= 0.1;
        }
        
        this.repaint();
        this.setPreferredSize(new Dimension((int)(this.map.getWidth() * this.scale), (int)(this.map.getHeight() * this.scale)));
        this.revalidate();
        
        //this.scrollPane.getVerticalScrollBar().setValue((int)(300/this.scale));
        //this.scrollPane.getHorizontalScrollBar().setValue((int)(300/this.scale));
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
 
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
