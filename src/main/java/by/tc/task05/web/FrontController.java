package by.tc.task05.web;

import by.tc.task05.Paginator;
import by.tc.task05.dao.Repository;
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
import java.io.InputStream;
import java.util.List;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parserType = req.getParameter("parserType");
        Integer pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        boolean errorIsCritical = false;
        String parsingErrorCause = "";
        if (parserType != null) {
            try {
                parseFile(parserType);
            } catch (ParserException e) {
                parsingErrorCause = e.getMessage();
                if (e.isCritical()) {
                    errorIsCritical = true;
                }
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher requestDispatcher;
        if (!parsingErrorCause.isEmpty() && errorIsCritical) {
            req.setAttribute("error", parsingErrorCause);
            requestDispatcher = req.getRequestDispatcher("/jsp/parsingError.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            Repository bookRepository = Repository.getRepository();
            req.setAttribute("books", bookRepository.getStoredData().get(pageNumber - 1));
            req.setAttribute("maxPages", bookRepository.getMaxPages());
            req.setAttribute("pageNumber", pageNumber);
            req.setAttribute("error", parsingErrorCause);
            requestDispatcher = req.getRequestDispatcher("/jsp/parsingResults.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    private void parseFile(String parserType) throws ParserException {
        ParserManager parserManager = ParserManager.getInstance();
        XmlParser xmlParser = parserManager.getParser(parserType);
        InputStream xmlFileInputStream = getClass().getResourceAsStream("/source.xml");
        List<Book> parsedEntities = xmlParser.parse(xmlFileInputStream);
        if (parsedEntities == null) {
            throw new ParserException("List of parsed entities is empty");
        }
        Paginator booksByPages = new Paginator(5, parsedEntities);
        Repository bookRepository = Repository.getRepository();
        bookRepository.setStoredData(booksByPages.getResultEntities());
        bookRepository.setMaxPages(booksByPages.getMaxPages());
    }
}
