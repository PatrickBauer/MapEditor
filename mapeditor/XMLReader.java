package mapeditor;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLReader {

    public XMLReader() {
    }

    public ArrayList<Rectangle> read() {
        ArrayList<Rectangle> collisionBoxes = new ArrayList<>();

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("C:\\Users\\Patrick\\Documents\\GameDev\\Game\\resources\\maps\\1.xml");

        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("block");

            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);

                collisionBoxes.add(
                        new Rectangle(
                            Integer.parseInt(node.getAttributeValue("x")), 
                            Integer.parseInt(node.getAttributeValue("y")), 
                            Integer.parseInt(node.getAttributeValue("width")), 
                            Integer.parseInt(node.getAttributeValue("height"))
                        ));
            }

        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }

        return collisionBoxes;
    }
}
