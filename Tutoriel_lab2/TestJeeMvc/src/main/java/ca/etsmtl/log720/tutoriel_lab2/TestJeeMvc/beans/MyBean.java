package ca.etsmtl.log720.tutoriel_lab2.TestJeeMvc.beans;

public class MyBean
{
  // Cette m�thode permettra de tester votre Pojo
  public String getSalut_FromJSP() {
    return "Votre JavaBean vous salue avec votre JSP directement.";
  }

  // Cette m�thode permettra de tester votre Servlet
  public String getSalut_FromServlet() {
    return "Votre JavaBean vous salue avec votre Servlet.";
  }
}

