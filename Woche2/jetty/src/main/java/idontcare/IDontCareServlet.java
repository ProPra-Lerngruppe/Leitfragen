package idontcare;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IDontCareServlet extends HttpServlet {

  List<String> answer = List.of(
      "<!DOCTYPE html>",
      "<html>",
      "  <head>",
      "	<title>I don't care!</title>",
      "  </head>",
      "  <body>",
      "    <h1>I don't care!</h1>",
      "     <h2>...But do you?</h2>",
          "     <form action='/dont'  method='POST'>",
          "     <h3>Do you really care?</h3>",
          "       <input type='radio' name='ido' value='caring'>",
          "       <label>I really do!</label>",
          "       <input type='radio' name='idont' value='caring'>",
          "       <label>I really don't!</label>",
          "     <h3>What is your name?</h3>",
          "       <input type='text' name='name' value='your name...'>",
          "     <input type='submit' value='Submit'>",
          "     </form>",
      "  </body>",
      "</html>"


  );

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("application/json");
    //resp.setContentType("application/json");
    PrintWriter writer = resp.getWriter();

    answer.forEach(writer::println);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    String theyCare = req.getParameter("ido");
    PrintWriter wr = resp.getWriter();
    if(theyCare != null) {
      wr.println(name+"does secretly care.");
      System.out.println(name+"does secretly care.");
      return;
    }
    wr.println(name+" really does not care.");
    System.out.println(name+" really does not care.");
  }
}
