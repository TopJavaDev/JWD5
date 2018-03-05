package by.tc.task05.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private final static String SAX = "SAX";
    private final static String StAX = "StAX";
    private final static String DOM = "DOM";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parserType = req.getHeader("parserType");
        resp.setContentType("text/html;charset=UTF-8");
        switch (parserType) {
            case SAX: {



                break;
            }
            case StAX: {



                break;
            }
            case DOM: {




            }
        }
    }
}
