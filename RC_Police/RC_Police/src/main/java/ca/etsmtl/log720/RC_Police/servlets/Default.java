package ca.etsmtl.log720.RC_Police.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log720.RC_Police.Beans.BaseBean;


/**
 * Servlet implementation class Default
 */
public class Default extends javax.servlet.http.HttpServlet
implements javax.servlet.Servlet  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Default() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			System.out.println("enter in doGet");
			
			String path = ((HttpServletRequest) request).getRequestURI();
			System.out.println("path =" + path);
			System.out.println("contextPath" + request.getContextPath() + "/");
			
			BaseBean base = (BaseBean)request.getSession(true).getAttribute("BaseBean");
			if(base == null){
				base = new BaseBean();
				request.getSession().setAttribute("BaseBean", base);
			}
			
			
			if(!path.equals(request.getContextPath() + "/index.jsp")
					&& !path.equals(request.getContextPath() + "/")
					&& !path.equals("/index.jsp")
					&& !path.equals("/"))
			{
				System.out.println("navigateTo - > " + path.replace(request.getContextPath(), ""));
				base.navigateTo(path.replace(request.getContextPath()+"/", ""));	
				//response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
				/* Transmission de la paire d'objets request/response à notre JSP */
				this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
			}
			
		}catch(Exception e){
			System.err.println("Internal Erreur encountered");
			response.sendError(503,e.getMessage());
		}
		
		System.out.println("exit in doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
