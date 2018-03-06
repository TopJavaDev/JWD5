package by.tc.task05.web;

import by.tc.task05.model.Book;
import by.tc.task05.parser.ParserManager;
import by.tc.task05.parser.iface.XmlParser;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parserType = req.getHeader("parserType");
        ParserManager parserManager = ParserManager.getInstance();
        XmlParser concreteParser = parserManager.getParser(parserType);


        try {
            List<Book> parsedEntities = concreteParser.parse();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("parsingResults");
        requestDispatcher.forward(req, resp);
    }
}
