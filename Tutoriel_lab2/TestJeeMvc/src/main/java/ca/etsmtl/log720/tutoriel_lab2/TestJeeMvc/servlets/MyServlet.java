package ca.etsmtl.log720.tutoriel_lab2.TestJeeMvc.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.etsmtl.log720.tutoriel_lab2.TestJeeMvc.beans.MyBean;

public class MyServlet extends javax.servlet.http.HttpServlet
    implements javax.servlet.Servlet  {

  public MyServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException  {
     MyBean bean = new MyBean();
     request.getSession().setAttribute("myBean", bean); // Scope: Session
     response.sendRedirect
       ("/TestJeeMvc/TestClientWebFromServlet.jsp"); // redirection
  }

  protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
      // Insérer du code plus tard
  }
}
