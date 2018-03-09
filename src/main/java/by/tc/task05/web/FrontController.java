package by.tc.task05.web;

import by.tc.task05.dao.Repository;
import by.tc.task05.model.Book;
import by.tc.task05.parser.ParserManager;
import by.tc.task05.parser.exception.ParserException;
import by.tc.task05.parser.iface.XmlParser;
import by.tc.task05.Paginator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parserType = req.getParameter("parserType");
        Integer pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        RequestDispatcher requestDispatcher;
        boolean errorIsCritical = false;
        String parsingErrorCause = "";
        Repository bookRepository = Repository.getRepository();
        if (parserType != null) {
            ParserManager parserManager = ParserManager.getInstance();
            XmlParser xmlParser = parserManager.getParser(parserType);
            List<Book> parsedEntities = null;
            InputStream xmlFileInputStream = getClass().getResourceAsStream("/source.xml");
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
            Paginator booksByPages = new Paginator(5, parsedEntities);
            bookRepository.setStoredData(booksByPages.getResultEntities());
            bookRepository.setMaxPages(booksByPages.getMaxPages());
        }
        resp.setContentType("text/html;charset=UTF-8");
        if (!parsingErrorCause.isEmpty() && errorIsCritical) {
            req.setAttribute("error", parsingErrorCause);
            requestDispatcher = req.getRequestDispatcher("/jsp/parsingError.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("books", bookRepository.getStoredData().get(pageNumber - 1));
            req.setAttribute("maxPages", bookRepository.getMaxPages());
            req.setAttribute("pageNumber", pageNumber);
            req.setAttribute("error", parsingErrorCause);
            requestDispatcher = req.getRequestDispatcher("/jsp/parsingResults.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
