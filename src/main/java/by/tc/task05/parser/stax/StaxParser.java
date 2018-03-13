package by.tc.task05.parser.stax;

import by.tc.task05.model.Book;
import by.tc.task05.model.BookAttributeTag;
import by.tc.task05.parser.exception.ParserException;
import by.tc.task05.parser.iface.XmlParser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StaxParser implements XmlParser {

    @Override
    public List<Book> parse(InputStream source) throws ParserException {
        List<Book> bookList;
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        inputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);
        try {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(source);
            bookList = read(reader);
        } catch (XMLStreamException e) {
            throw new ParserException(e.getMessage());
        }
        return bookList;
    }

    private List<Book> read(XMLStreamReader reader) throws XMLStreamException, ParserException {
        List<Book> bookList = new ArrayList<>();
        Book book = null;
        String elementName = null;

        while (reader.hasNext()) {
            if (reader.getEventType() == XMLStreamConstants.START_DOCUMENT) {
                System.out.println("Parsing started");
            }
            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                System.out.println("Parsing ended");
            }
            int elementType = reader.next();
            switch (elementType) {
                case XMLStreamConstants.START_ELEMENT: {
                    elementName = reader.getLocalName().toUpperCase();
                    if (elementName.equals(BookAttributeTag.BOOK.toString())) {
                        book = new Book(reader.getAttributeValue(null, "id"));
                    }
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    book = XmlParser.TagSwitcher.setAttributes(elementName, text, book);
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    elementName = reader.getLocalName().toUpperCase();
                    if (elementName.equals(BookAttributeTag.BOOK.toString())) {
                        bookList.add(book);
                    }
                    break;
                }
            }
        }
        return bookList;
    }
}
