package by.tc.task05.parser.iface;

import by.tc.task05.model.Book;
import by.tc.task05.model.BookAttributeTag;
import by.tc.task05.parser.ParserManager;
import by.tc.task05.parser.exception.ParserException;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public interface XmlParser {
    List<Book> parse(InputStream source) throws ParserException;

    class TagSwitcher {
        public static Book setAttributes(String elementName, String elementValue, Book book) throws ParserException {
            BookAttributeTag tag = BookAttributeTag.valueOf(elementName);
            switch (tag) {
                case AUTHOR: {
                    book.setAuthor(elementValue);
                    break;
                }
                case DESCRIPTION: {
                    book.setDescription(elementValue);
                    break;
                }
                case PRICE: {
                    book.setPrice(Float.parseFloat(elementValue));
                    break;
                }
                case GENRE: {
                    book.setGenre(elementValue);
                    break;
                }
                case PUBLISH_DATE: {
                    try {
                        book.setPublishDate(ParserManager.getPublishDateFormat().parse(elementValue));
                    } catch (ParseException e) {
                        throw new ParserException(e.getMessage());
                    }
                    break;
                }
                case TITLE: {
                    book.setTitle(elementValue);
                    break;
                }
            }
            return book;
        }
    }
}
