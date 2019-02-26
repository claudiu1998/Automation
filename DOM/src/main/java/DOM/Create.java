package DOM;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
public class Create {
    public static void main(String argv[]) {

        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("company");
            doc.appendChild(rootElement);

            // department element
            Element department = doc.createElement("department");
            rootElement.appendChild(department);

            // setting attribute to element
            Attr attr = doc.createAttribute("name");
            attr.setValue("Development");
            department.setAttributeNode(attr);
            attr = doc.createAttribute("depId");
            attr.setValue("1");
            department.setAttributeNode(attr);

            // employee tag
            Element emmployee = doc.createElement("employee");
            department.appendChild(emmployee);
            // setting attribute to element
            attr = doc.createAttribute("empId");
            attr.setValue("001");
            emmployee.setAttributeNode(attr);

            //  employee information
            Element carname = doc.createElement("lastName");
            carname.appendChild(doc.createTextNode("Eminescu"));
            emmployee.appendChild(carname);

            carname = doc.createElement("firstName");
            carname.appendChild(doc.createTextNode("Mihai"));
            emmployee.appendChild(carname);

            carname = doc.createElement("birthDate");
            carname.appendChild(doc.createTextNode("10.01.1998"));
            emmployee.appendChild(carname);

            carname = doc.createElement("position");
            carname.appendChild(doc.createTextNode("Department Manager"));
            emmployee.appendChild(carname);

            Element skills = doc.createElement("skills");
            emmployee.appendChild(skills);

//            Element employee = doc.createElement("skills");
//            department.appendChild(employee);

            Element skill1 = doc.createElement("skill");
            skill1.appendChild(doc.createTextNode("Communication"));
            skills.appendChild(skill1);

           skill1 = doc.createElement("skill");
            skill1.appendChild(doc.createTextNode("Java"));
            skills.appendChild(skill1);

//            carname = doc.createElement("skill");
//            carname.appendChild(doc.createTextNode("Java"));
//            department.appendChild(carname);

            //cfreate tag manager
            carname = doc.createElement("managerId");
            carname.appendChild(doc.createTextNode("002"));
            emmployee.appendChild(carname);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("lucratori1.xml"));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
