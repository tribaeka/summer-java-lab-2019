package by.epam.training.task8;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Runner {
    private static final String XML_PATH = "/Users/tribaeka/summer-java-lab-2019-yahor-hlushak/task-8/src/main/resources/shema/test5.xml";
    private static final String XSD_PATH = "/Users/tribaeka/summer-java-lab-2019-yahor-hlushak/task-8/src/main/resources/shema/event.xsd";
    public static void main(String[] args) throws IOException {
        System.out.println("Xml to xsd matching ->" + checkXmlForXSD());
    }

    private static boolean checkXmlForXSD() throws IOException {
        File xml = new File(XML_PATH);
        File xsd = new File(XSD_PATH);

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
            Schema schema = factory.newSchema(new StreamSource(XSD_PATH));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(XML_PATH));
            return true;
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
