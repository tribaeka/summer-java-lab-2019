package by.epam.training.task8;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static by.epam.training.task8.Utilities.*;

public class Runner {
    private static final String TASK5_XML_PATH = "schema/test5.xml";
    private static final String TASK5_XSD_PATH = "schema/event.xsd";
    private static final String PEOPLE_XML_PATH = "converter/people.xml";
    private static final String REWRITE_PATH = "task-8/src/main/resources/converter/people.xml";
    private static ClassLoader loader = Runner.class.getClassLoader();
    public static void main(String[] args) throws IOException {
        System.out.println("Xml to xsd matching ->" + checkXmlForXSD());
        Person newPerson = new Person();
        newPerson.setSurname("somesurname");
        newPerson.setName("somename");
        newPerson.setBirthday(LocalDate.now());
        newPerson.setBirthplace("somebrithplace");
        newPerson.setWork("somework");
        try {
            File peopleXml = new File(loader.getResource(PEOPLE_XML_PATH).getFile());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(peopleXml);
            addPersonToXmlOnPosition(newPerson, 3, document);
            changeWorkAtFirstLetterAtName('I', document);
            List<Person> personList = parseXmlToList(document);
            System.out.println(personList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addPersonToXmlOnPosition(Person person, int position, Document document) throws TransformerException, IOException {
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("person");

        Element personXml = document.createElement("person");

        Element surname = document.createElement("surname");
        personXml.appendChild(surname);
        surname.appendChild(document.createTextNode(person.getSurname()));

        Element name = document.createElement("name");
        personXml.appendChild(name);
        name.appendChild(document.createTextNode(person.getName()));

        Element birthday = document.createElement("birthday");
        personXml.appendChild(birthday);
        Element day = document.createElement("day");
        birthday.appendChild(day);
        day.appendChild(document.createTextNode(String.valueOf(person.getBirthday().getDayOfMonth())));
        Element month = document.createElement("month");
        birthday.appendChild(month);
        month.appendChild(document.createTextNode(fixMothFormat(person.getBirthday().getMonthValue())));
        Element year = document.createElement("year");
        birthday.appendChild(year);
        year.appendChild(document.createTextNode(String.valueOf(person.getBirthday().getYear())));

        Element birthplace = document.createElement("birthplace");
        personXml.appendChild(birthplace);
        birthplace.setAttribute("city", person.getBirthplace());

        Element work = document.createElement("work");
        personXml.appendChild(work);
        work.appendChild(document.createTextNode(person.getWork()));

        List<Integer> idList = new ArrayList<>();
        List<Node> nodesAfterInsertPosition = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            idList.add(Integer.parseInt(nodeList.item(i).getAttributes().item(0).getNodeValue()));
            if (i + 1 >= position){
                nodesAfterInsertPosition.add(nodeList.item(i));
            }
        }
        for (Node node : nodesAfterInsertPosition){
            document.adoptNode(node);
        }
        String newId = String.valueOf(idList.stream().max(Integer::compareTo).get() + 1);
        personXml.setAttribute("ID", newId);
        document.getFirstChild().appendChild(personXml);
        for (Node node : nodesAfterInsertPosition){
            document.getFirstChild().appendChild(node);
        }
        writeDocumentInFile(document, REWRITE_PATH);
    }

    private static void changeWorkAtFirstLetterAtName(Character letter, Document document) throws IOException {
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("person");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (getTagValue("name", (Element) node).charAt(0) == letter){
                ((Element) node).getElementsByTagName("work")
                        .item(0)
                        .getFirstChild()
                        .setTextContent(generateRandomString(5));
            }
        }
        writeDocumentInFile(document, REWRITE_PATH);
    }

    private static List<Person> parseXmlToList(Document document) throws IOException,
            ParserConfigurationException, SAXException {
        List<Person> parsedList = new ArrayList<>();
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
