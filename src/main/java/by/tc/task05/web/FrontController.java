package by.tc.task05.web;

import by.tc.task05.model.Entity;
import by.tc.task05.parser.ParserManager;
import by.tc.task05.parser.iface.Parser;

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
        Parser concreteParser = parserManager.getParser(parserType);


        List<Entity> parsedEntities = concreteParser.parse();


        resp.setContentType("text/html;charset=UTF-8");


    }
}
