package mapeditor;

import java.awt.Rectangle;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLWriter {

    public XMLWriter() {
    }

    public void generate(ArrayList<Rectangle> collisionBoxes) {
        try {
            Document doc = new Document();
            
            Element collision = new Element("collision");
            doc.setRootElement(collision);
            
            Element block;
            
            for (Rectangle rect : collisionBoxes) {
                block = new Element("block");
                
                block.setAttribute(new Attribute("x", Integer.toString((int) rect.getX())));
                block.setAttribute(new Attribute("y", Integer.toString((int) rect.getY())));
                block.setAttribute(new Attribute("width", Integer.toString((int) rect.getWidth())));
                block.setAttribute(new Attribute("height", Integer.toString((int) rect.getHeight())));
                
                doc.getRootElement().addContent(block);
            }

            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("C:\\Users\\Patrick\\Documents\\GameDev\\Game\\resources\\maps\\1.xml"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
