package by.tc.task05.parser.dom;

import by.tc.task05.model.Book;
import by.tc.task05.parser.exception.ParserException;
import by.tc.task05.parser.iface.XmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static by.tc.task05.model.BookAttributeTag.BOOK;

public class DOMParser implements XmlParser {

    @Override
    public List<Book> parse(InputStream source) throws ParserException {
        List<Book> bookList = new ArrayList<>();
        NodeList bookNodes;
        NodeList bookChildNodes;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document;

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(source);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new ParserException(e.getMessage());
        }
        bookNodes = document.getElementsByTagName(BOOK.toString().toLowerCase());
        for (int i = 0; i < bookNodes.getLength(); i++) {
            Node bookNode = bookNodes.item(i);
            Book book = new Book(bookNode.getAttributes().getNamedItem("id").getNodeValue());
            bookChildNodes = bookNode.getChildNodes();
            for (int j = 0; j < bookChildNodes.getLength(); j++) {
                Node bookChildNode = bookChildNodes.item(j);
                if (bookChildNode.getNodeType() == Node.ELEMENT_NODE) {
                    String childNodeName = bookChildNode.getNodeName().toUpperCase();
                    String childNodeValue = bookChildNode.getFirstChild().getNodeValue();
                    book = XmlParser.TagSwitcher.setAttributes(childNodeName, childNodeValue, book);
                }
            }
            bookList.add(book);
        }
        return bookList;
    }
}
