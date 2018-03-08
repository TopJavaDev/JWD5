package by.tc.task05.parser.dom;

import by.tc.task05.model.Book;
import by.tc.task05.parser.exception.ParserException;
import by.tc.task05.parser.iface.XmlParser;

import java.io.InputStream;
import java.util.List;

public class DOMParser implements XmlParser {

    @Override
    public List<Book> parse(InputStream source) throws ParserException {
        return null;
    }
}
