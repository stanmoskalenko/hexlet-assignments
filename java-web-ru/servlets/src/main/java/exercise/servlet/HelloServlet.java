package exercise.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var queryStr =  req.getQueryString();
        var name = queryStr.substring(queryStr.lastIndexOf("=") + 1);

        if (name.isEmpty()) {
            name = "Guest";
        }
        var msg = String.format("Hello, %s!", name);
        req.setAttribute("message", msg);
        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
    }

    // END
}
