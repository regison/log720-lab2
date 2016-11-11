package ca.etsmtl.log720.RC_Police.servlets.infractions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;
import ca.etsmtl.log720.RC_Police.servlets.dossiers.DossierFormValidation;
import ca.etsmtl.log720.RC_Police.utils.helpers.DossierHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.InfractionHelper;

/**
 * Servlet implementation class Dossier
 */
@WebServlet("/pages/protected/infractions/infraction")
public class Infraction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	private javax.sql.DataSource myDB;

    @Resource(name="jdbc/TestJeeDB")
    private void setMyDB(javax.sql.DataSource ds) {
        myDB = ds;
    }
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Infraction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String record_id = request.getParameter("id");
		String dossier_id = request.getParameter("dossierid");
		
		InfractionBean infraction = new InfractionBean();
		
		if(record_id != null && !record_id.equals(""))
		{
	        try {
		          InfractionHelper iHelper = new InfractionHelper(myDB);
		          
		          infraction = iHelper.getInfractionBy(Integer.parseInt(record_id));
		            
		          if(infraction.getId() != null){
		        	  iHelper.populateDossierFor(infraction);
		          }
		
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.setAttribute("infraction", infraction);
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/infractions/Infraction.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InfractionFormValidation validation = new InfractionFormValidation(myDB);
		
		
		
		InfractionBean infraction = validation.ValidateInfraction(request);
		
		InfractionHelper iHelper = new InfractionHelper(myDB);
		try {
			int new_id = iHelper.saveNewInfration(infraction);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("form", validation);
		request.setAttribute("infraction", infraction);
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/infractions/Infraction.jsp" ).forward( request, response );
		
		

	}

}
