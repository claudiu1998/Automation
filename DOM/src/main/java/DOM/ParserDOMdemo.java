package DOM;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class ParserDOMdemo {
    public static void main(String[] args) {
        try {
            File inputFile = new File("lucratori.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("department");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    System.out.println("Department Name : "
                            + eElement.getAttribute("name"));
                    System.out.println("Department ID : "
                            + eElement.getAttribute("depId"));


                }



                nList = doc.getElementsByTagName("employee");
                for ( temp = 0; temp < nList.getLength(); temp++) {
                    nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {


                        Element eElement = (Element) nNode;
                        System.out.println("Employee ID : "
                                + eElement.getAttribute("empId"));
                        System.out.println("Last Name: "
                                + eElement
                                .getElementsByTagName("lastName")
                                .item(0)
                                .getTextContent());
                        System.out.println("First Name : "
                                + eElement
                                .getElementsByTagName("firstName")
                                .item(0)
                                .getTextContent());
                        System.out.println("Birth Date : "
                                + eElement
                                .getElementsByTagName("birthDate")
                                .item(0)
                                .getTextContent());

                        System.out.println("Position : "
                                + eElement
                                .getElementsByTagName("position")
                                .item(0)
                                .getTextContent());
                        NodeList skillsNodeList = eElement.getElementsByTagName("skills").item(0).getChildNodes();
                        for(int i=0;i<skillsNodeList.getLength();i++){
                            Node currentNode = skillsNodeList.item(i);

                            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {

                                Element currentElement = (Element) currentNode;
                                System.out.println("Skill: "
                                        + currentElement
                                        .getTextContent());
                            }

                    }
                        System.out.println("Manager ID : "
                                + eElement
                                .getElementsByTagName("managerId")
                                .item(0)
                                .getTextContent());
                    }

                }



            }
            }
            catch(Exception e){
                e.printStackTrace();
                }

    }

}
