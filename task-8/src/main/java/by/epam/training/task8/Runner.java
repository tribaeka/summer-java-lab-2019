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
    private static final String XML_PATH = "schema/test5.xml";
    private static final String XSD_PATH = "schema/event.xsd";
    public static void main(String[] args) throws IOException {
        System.out.println("Xml to xsd matching ->" + checkXmlForXSD());
    }

    private static boolean checkXmlForXSD() throws IOException {
        ClassLoader loader = Runner.class.getClassLoader();
        File xml = new File(loader.getResource(XML_PATH).getFile());
        File xsd = new File(loader.getResource(XSD_PATH).getFile());

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
            Schema schema = factory.newSchema(new StreamSource(loader.getResource(XSD_PATH).getPath()));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(loader.getResource(XML_PATH).getPath()));
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
