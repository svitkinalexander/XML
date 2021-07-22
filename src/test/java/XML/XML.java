package XML;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XML {
    private static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("employee.xml"), handler);

        for (Employee employee : employees) {
            System.out.println(String.format("Имя сотрудника: %s, его должность: %s", employee.getName(), employee.getJob()));
        }
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement (String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
            if (qName.equals("employee")) {
                String name = attributes.getValue("name");
                String job = attributes.getValue("job");
                employees.add(new Employee(name, job));
            }
        }
    }
}
