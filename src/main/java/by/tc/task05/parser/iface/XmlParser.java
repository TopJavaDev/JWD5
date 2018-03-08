package by.tc.task05.parser.iface;

import by.tc.task05.model.Book;
import by.tc.task05.parser.exception.ParserException;
import org.xml.sax.InputSource;

import java.util.List;

public interface XmlParser {
    InputSource XML_INPUT_SOURCE = new InputSource("source.xml");

    List<Book> parse() throws ParserException;
}
