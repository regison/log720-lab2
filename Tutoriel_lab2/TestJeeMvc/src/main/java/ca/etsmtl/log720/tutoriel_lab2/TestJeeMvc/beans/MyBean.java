package ca.etsmtl.log720.tutoriel_lab2.TestJeeMvc.beans;

public class MyBean
{
  // Cette méthode permettra de tester votre Pojo
  public String getSalut_FromJSP() {
    return "Votre JavaBean vous salue avec votre JSP directement.";
  }

  // Cette méthode permettra de tester votre Servlet
  public String getSalut_FromServlet() {
    return "Votre JavaBean vous salue avec votre Servlet.";
  }
}

