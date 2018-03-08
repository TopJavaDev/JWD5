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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parserType = req.getParameter("parserType");
        ParserManager parserManager = ParserManager.getInstance();
        XmlParser xmlParser = parserManager.getParser(parserType);
        RequestDispatcher requestDispatcher;
        List<Book> parsedEntities = null;
        FileInputStream xmlFileInputStream = new FileInputStream(new File("source.xml"));

        String parsingErrorCause = "";
        boolean errorIsCritical = false;
        try {
            parsedEntities = xmlParser.parse(xmlFileInputStream);
        } catch (ParserException e) {
            parsingErrorCause = e.getMessage();
            if (e.isCritical()) {
                errorIsCritical = true;
            }
        }
        if (parsedEntities == null && parsingErrorCause.isEmpty()) {
            parsingErrorCause = "List of parsed entities is empty";
        }
        resp.setContentType("text/html;charset=UTF-8");
        if (!parsingErrorCause.isEmpty() && errorIsCritical) {
            req.setAttribute("error", parsingErrorCause);
            requestDispatcher = req.getRequestDispatcher("/jsp/parsingError.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("books", parsedEntities);
            req.setAttribute("error", parsingErrorCause);
            requestDispatcher = req.getRequestDispatcher("/jsp/parsingResults.jsp");
            requestDispatcher.forward(req, resp);

            // todo PAGINATION
        }
    }
}
