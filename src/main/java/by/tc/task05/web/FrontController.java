package by.tc.task05.web;

import by.tc.task05.model.Book;
import by.tc.task05.parser.ParserManager;
import by.tc.task05.parser.exception.ParserException;
import by.tc.task05.parser.iface.XmlParser;

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
        RequestDispatcher requestDispatcher;
        List<Book> parsedEntities = null;

        String parsingErrorReason = "";
        boolean errorIsCritical = false;
        try {
            parsedEntities = concreteParser.parse();
        } catch (ParserException e) {
            parsingErrorReason = e.getMessage();
            if (e.isCritical()) {
                errorIsCritical = true;
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        if ((!parsingErrorReason.isEmpty() && errorIsCritical) || parsedEntities == null) {
            requestDispatcher = req.getRequestDispatcher("parsingError");
            requestDispatcher.forward(req, resp);
        } else {
            requestDispatcher = req.getRequestDispatcher("parsingResults");
            req.setAttribute("books", parsedEntities);
            req.setAttribute("error", parsingErrorReason);
            requestDispatcher.forward(req, resp);

            // todo PAGINATION
        }
    }
}
