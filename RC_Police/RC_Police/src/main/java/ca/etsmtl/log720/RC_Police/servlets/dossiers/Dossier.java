package ca.etsmtl.log720.RC_Police.servlets.dossiers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ReferencedBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;
import ca.etsmtl.log720.RC_Police.utils.helpers.DossierHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.InfractionHelper;

/**
 * Servlet implementation class Dossier
 */
@WebServlet("/pages/protected/dossiers/dossier")
public class Dossier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private javax.sql.DataSource myDB;

    @Resource(name="jdbc/TestJeeDB")
    private void setMyDB(javax.sql.DataSource ds) {
        myDB = ds;
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dossier() {
        super();
        // TODO Auto-generated constructor stub

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String record_id = request.getParameter("id");
		DossierBean dossier = new DossierBean();

		
		if(record_id != null && !record_id.equals(""))
		{

	        try {
		        
	        	 	DossierHelper dHelper = new DossierHelper(myDB);
		            dossier = dHelper.getDosssierBy(Integer.parseInt(record_id));
		            if(dossier.getId() != null){
		            	dHelper.populateInfractionFor(dossier);
		            	dHelper.populateReactionsFor(dossier);
		            }
		            
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.setAttribute("dossier", dossier);
		
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/dossiers/Dossier.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DossierFormValidation validation = new DossierFormValidation(myDB);
		
		
		
		DossierBean dossier = validation.ValidateDossier(request);
		
		DossierHelper dHelper = new DossierHelper(myDB);
		try {
			int new_id = dHelper.saveNewDossier(dossier);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("form", validation);
		request.setAttribute("dossier", dossier);
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/dossiers/Dossier.jsp" ).forward( request, response );
		

	}

}
