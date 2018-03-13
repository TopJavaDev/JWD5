package by.tc.task05.parser.sax;

import by.tc.task05.model.Book;
import by.tc.task05.parser.exception.ParserException;
import by.tc.task05.parser.iface.XmlParser;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements XmlParser {

    @Override
    public List<Book> parse(InputStream source) throws ParserException {
        InputSource xmlInputSource = new InputSource(source);
        XMLReader xmlReader;
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            throw new ParserException(e.getMessage());
        }
        SaxParserHandler handler = new SaxParserHandler();
        xmlReader.setContentHandler(handler);
        try {
            xmlReader.parse(xmlInputSource);
        } catch (IOException | SAXException e) {
            throw new ParserException(e.getMessage());
        }
        return handler.getBooks();
    }

    class SaxParserHandler extends DefaultHandler {
        private List<Book> bookList;
        private Book book;
        private StringBuilder content;

        @Override
        public void startDocument() throws SAXException {
            System.out.println("Parsing started");
            bookList = new ArrayList<>();
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("Parsing ended");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("book")) {
                book = new Book(attributes.getValue("id"));
            } else {
                content = new StringBuilder();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException, ParserException {
            if (qName.equalsIgnoreCase("book")) {
                bookList.add(book);
                book = null;
            } else {
                book = XmlParser.TagSwitcher.setAttributes(qName.toUpperCase(), content.toString(), book);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            content.append(ch, start, length);
        }

        @Override
        public void warning(SAXParseException e) throws ParserException {
            throw new ParserException(e.getMessage(), false);
        }

        @Override
        public void error(SAXParseException e) throws ParserException {
            throw new ParserException(e.getMessage(), false);
        }

        @Override
        public void fatalError(SAXParseException e) throws ParserException {
            throw new ParserException(e.getMessage());
        }

        List<Book> getBooks() {
            return bookList;
        }
    }
}
