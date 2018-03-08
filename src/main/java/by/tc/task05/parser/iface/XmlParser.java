package by.tc.task05.parser.iface;

import by.tc.task05.model.Book;
import by.tc.task05.parser.exception.ParserException;

import java.io.InputStream;
import java.util.List;

public interface XmlParser {
    List<Book> parse(InputStream source) throws ParserException;
}
