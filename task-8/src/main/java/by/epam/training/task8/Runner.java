package by.epam.training.task8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static final String TASK5_XML_PATH = "schema/test5.xml";
    private static final String TASK5_XSD_PATH = "schema/event.xsd";
    private static final String PEOPLE_XML_PATH = "converter/people.xml";
    private static ClassLoader loader = Runner.class.getClassLoader();
    public static void main(String[] args) throws IOException {
        System.out.println("Xml to xsd matching ->" + checkXmlForXSD());
        try {
            List<Person> personList = parseXmlToList();
            System.out.println(personList);
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    private static List<Person> parseXmlToList() throws IOException, ParserConfigurationException, SAXException {
        List<Person> parsedList = new ArrayList<>();
        File peopleXml = new File(loader.getResource(PEOPLE_XML_PATH).getFile());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(peopleXml);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("person");
        for (int i = 0; i < nodeList.getLength(); i++) {
            parsedList.add(getPeople(nodeList.item(i)));
        }
        return parsedList;
    }

    private static Person getPeople(Node node) {
        Person person = new Person();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            person.setId(Integer.parseInt(element.getAttribute("ID")));
            person.setSurname(getTagValue("surname", element));
            person.setName(getTagValue("name", element));
            person.setBirthday(constructDate(element));
            person.setBirthplace(getTagsAttribute("birthplace", "city", element));
            person.setWork(getTagValue("work", element));
        }

        return person;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    private static String getTagsAttribute(String tag, String attribute, Element element) {
        Node node = element.getElementsByTagName(tag).item(0);
        return node.getAttributes().getNamedItem(attribute).getNodeValue();
    }

    private static LocalDate constructDate(Element element){
        NodeList nodeList = element.getElementsByTagName("birthday").item(0).getChildNodes();
        StringBuilder preParsedDateBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element){
                String nodeValue = nodeList.item(i).getChildNodes().item(0).getNodeValue();
                preParsedDateBuilder.append(nodeValue).append("/");
            }
        }
        preParsedDateBuilder.deleteCharAt(preParsedDateBuilder.length() - 1);
        return LocalDate.parse(preParsedDateBuilder, formatter);
    }
    private static boolean checkXmlForXSD() throws IOException {

        File xml = new File(loader.getResource(TASK5_XML_PATH).getFile());
        File xsd = new File(loader.getResource(TASK5_XSD_PATH).getFile());

        if (!xml.exists()) {
            System.out.println("Xml file not exist");
        }

        if (!xsd.exists()) {
            System.out.println("Xsd file not exist");
        }

        if (!xml.exists() || !xsd.exists()) {
            return false;
        }

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(loader.getResource(TASK5_XSD_PATH).getPath()));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(loader.getResource(TASK5_XML_PATH).getPath()));
            return true;
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }catch (NullPointerException npe){
            System.out.println("Bad classpath: " + npe.getMessage());
            return false;
        }
    }
}
