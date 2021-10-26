package idontcare;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Start {

  public static void main(String[] args) throws Exception {

    Server server = new Server(8080);

    ServletHandler handler = new ServletHandler();
    handler.addServletWithMapping(IDontCareServlet.class, "/dont/*");
    handler.addServletWithMapping(IDoCareServlet.class, "/do/*");

    server.setHandler(handler);

    server.start();
    server.join();

  }
}
