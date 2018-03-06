package by.tc.task05.parser;

import by.tc.task05.parser.dom.DOMParser;
import by.tc.task05.parser.iface.Parser;
import by.tc.task05.parser.sax.SaxParser;
import by.tc.task05.parser.stax.StaxParser;

import java.util.Map;

public class ParserManager {
    private static final ParserManager instance = new ParserManager();
    private Map<String, Parser> parsers;

    private ParserManager() {
        parsers.put("SAX", new SaxParser());
        parsers.put("StAX", new StaxParser());
        parsers.put("DOM", new DOMParser());
    }

    public static ParserManager getInstance() {
        return instance;
    }

    public Parser getParser(String parserType) {
        return parsers.get(parserType);
    }
}