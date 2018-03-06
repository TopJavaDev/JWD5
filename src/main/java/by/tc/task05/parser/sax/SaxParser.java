package by.tc.task05.parser.sax;

import by.tc.task05.model.Entity;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import by.tc.task05.parser.iface.Parser;

import java.util.List;

public class SaxParser extends DefaultHandler implements Parser {


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void setFileSource() {

    }

    @Override
    public List<Entity> parse() {

        return null;
    }
}
