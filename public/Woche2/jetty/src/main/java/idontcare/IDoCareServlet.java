package idontcare;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IDoCareServlet extends HttpServlet {

  List<String> answer = List.of(
      "<!DOCTYPE html>",
      "<html>",
      "  <head>",
      "	<title>I do care!</title>",
      "  </head>",
      "  <body>",
      "    <h1>I do care!</h1>",
      "  </body>",
      "</html>"
  );



  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    boolean luege = "false".equals(req.getParameter("wahrheit"));

    if (luege) {
      resp.sendRedirect("/dont");
    }
    else {
      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();
      answer.forEach(writer::println);
    }
  }
}
