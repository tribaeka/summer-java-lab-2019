package by.epam.training.task8;

import by.epam.training.task8.model.Birthplace;
import by.epam.training.task8.model.People;
import by.epam.training.task8.model.Person;
import com.thoughtworks.xstream.XStream;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static by.epam.training.task8.Utilities.*;
import static by.epam.training.task8.Utilities.writeStringInFile;
import static by.epam.training.task8.XmlModel.*;

public class Runner {
    private static final String TASK5_XML_PATH = "schema/test5.xml";
    private static final String TASK5_XSD_PATH = "schema/event.xsd";
    private static final String PEOPLE_XML_PATH = "converter/people.xml";
    private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

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
            People people = new People(parseXmlToList(document));
            Collections.sort(people.getPeople(), new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getAge() - o2.getAge();
                }
            });
            System.out.println(people);
            XStream xstream = new XStream();
            xstream.aliasField("ID", Person.class, "id");
            xstream.useAttributeFor(Person.class, "id");
            xstream.useAttributeFor(Birthplace.class, "city");
            xstream.alias("person", Person.class);
            xstream.alias("persons", People.class);
            xstream.addImplicitCollection(People.class, "people");

            String xml = xstream.toXML(people);
            File outFile = new File("out.xml");
            writeStringInFile(outFile, XML_HEAD + xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addPersonToXmlOnPosition(Person person, int position, Document document) throws TransformerException, IOException {
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName(PERSON_XML_TAG);

        Element personXml = document.createElement(PERSON_XML_TAG);

        Element surname = document.createElement(SURNAME_XML_TAG);
        personXml.appendChild(surname);
        surname.appendChild(document.createTextNode(person.getSurname()));

        Element name = document.createElement(NAME_XML_TAG);
        personXml.appendChild(name);
        name.appendChild(document.createTextNode(person.getName()));

        Element birthday = document.createElement(BIRTHDAY_XML_TAG);
        personXml.appendChild(birthday);
        Element day = document.createElement(DAY_XML_TAG);
        birthday.appendChild(day);
        day.appendChild(document.createTextNode(String.valueOf(person.getBirthday().getDayOfMonth())));
        Element month = document.createElement(MONTH_XML_TAG);
        birthday.appendChild(month);
        month.appendChild(document.createTextNode(fixMothFormat(person.getBirthday().getMonthValue())));
        Element year = document.createElement(YEAR_XML_TAG);
        birthday.appendChild(year);
        year.appendChild(document.createTextNode(String.valueOf(person.getBirthday().getYear())));

        Element birthplace = document.createElement(BIRTHPLACE_XML_TAG);
        personXml.appendChild(birthplace);
        birthplace.setAttribute(CITY_XML_ATTRIBUTE, person.getBirthplace());

        Element work = document.createElement(WORK_XML_TAG);
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
        personXml.setAttribute(ID_XML_ATTRIBUTE, newId);
        document.getFirstChild().appendChild(personXml);
        for (Node node : nodesAfterInsertPosition){
            document.getFirstChild().appendChild(node);
        }
    }

    private static void changeWorkAtFirstLetterAtName(Character letter, Document document) throws IOException {
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName(PERSON_XML_TAG);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (getTagValue(NAME_XML_TAG, (Element) node).charAt(0) == letter){
                ((Element) node).getElementsByTagName(WORK_XML_TAG)
                        .item(0)
                        .getFirstChild()
                        .setTextContent(generateRandomString(5));
            }
        }
    }

    private static List<Person> parseXmlToList(Document document){
        List<Person> parsedList = new ArrayList<>();
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName(PERSON_XML_TAG);
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
            person.setSurname(getTagValue(SURNAME_XML_TAG, element));
            person.setName(getTagValue(NAME_XML_TAG, element));
            person.setBirthday(constructDate(element));
            person.setBirthplace(getTagsAttribute(BIRTHPLACE_XML_TAG, CITY_XML_ATTRIBUTE, element));
            person.setWork(getTagValue(WORK_XML_TAG, element));
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
        NodeList nodeList = element.getElementsByTagName(BIRTHDAY_XML_TAG).item(0).getChildNodes();
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
