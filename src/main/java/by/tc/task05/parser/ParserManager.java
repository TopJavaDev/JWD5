package by.tc.task05.parser;

import by.tc.task05.parser.dom.DOMParser;
import by.tc.task05.parser.iface.XmlParser;
import by.tc.task05.parser.sax.SaxParser;
import by.tc.task05.parser.stax.StaxParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ParserManager {
    private static final ParserManager instance = new ParserManager();
    private static final DateFormat publishDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Map<String, XmlParser> parsers = new HashMap<>();

    private ParserManager() {
        parsers.put("SAX", new SaxParser());
        parsers.put("StAX", new StaxParser());
        parsers.put("DOM", new DOMParser());
    }

    public static ParserManager getInstance() {
        return instance;
    }

    public XmlParser getParser(String parserType) {
        return parsers.get(parserType);
    }

    public static DateFormat getPublishDateFormat() {
        return publishDateFormat;
    }
}
