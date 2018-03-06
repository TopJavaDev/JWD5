package by.tc.task05.parser.sax;

import by.tc.task05.model.Book;
import by.tc.task05.parser.exception.ParserException;
import by.tc.task05.parser.iface.XmlParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements XmlParser {

    @Override
    public List<Book> parse() throws ParserException {
        XMLReader xmlReader = null;
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            throw new ParserException(e.getCause());
        }
        SaxParserHandler handler = new SaxParserHandler();
        xmlReader.setContentHandler(handler);
        try {
            xmlReader.parse(XML_INPUT_SOURCE);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return handler.getBooks();
    }

    class SaxParserHandler extends DefaultHandler {
        private List<Book> bookList;

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
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }

        @Override
        public void warning(SAXParseException e) throws SAXException {
            super.warning(e);
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            super.error(e);
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            super.fatalError(e);
        }

        public List<Book> getBooks() {
            return bookList;
        }
    }
}
