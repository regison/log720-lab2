package ca.etsmtl.log720.RC_Police.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;
import ca.etsmtl.log720.RC_Police.components.InfractionList;
import ca.etsmtl.log720.RC_Police.servlets.dossiers.DossierFormValidation;
import ca.etsmtl.log720.RC_Police.utils.helpers.DossierHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.Dossiers__InfractionsHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.InfractionHelper;

@WebServlet("/pages/protected/dossier__infraction")
public class Dossier__infraction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private javax.sql.DataSource myDB;

    @Resource(name="jdbc/TestJeeDB")
    private void setMyDB(javax.sql.DataSource ds) {
        myDB = ds;
    }
	
	
	public Dossier__infraction() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DossierHelper dHelper = new DossierHelper(myDB);
		InfractionHelper iHelper = new InfractionHelper(myDB);
		
		String record_id = request.getParameter("dossierid");
		DossierBean dossier = new DossierBean();
		InfractionList infractions = new InfractionList();
		
		if(record_id != null && !record_id.equals(""))
		{

	        try {
		        
		            dossier = dHelper.getDosssierBy(Integer.parseInt(record_id));
		            if(dossier.getId() != null){
		            	dHelper.populateInfractionFor(dossier);
		            	
		            }
		            
		            infractions.setInfraction(new ArrayList<InfractionBean>(iHelper.getAllInfractions()));
		            
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.setAttribute("dossier", dossier);
		request.setAttribute("infractions", infractions);
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/dossiers__infractions.jsp" ).forward( request, response );
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		String dossierid = (String) request.getParameter("dossierid");
		
		DossierHelper dHelper = new DossierHelper(myDB);
		InfractionHelper iHelper = new InfractionHelper(myDB);
		Dossiers__InfractionsHelper diHelper = new Dossiers__InfractionsHelper(myDB);
		
		
		try {
			DossierBean dossier = dHelper.getDosssierBy(Integer.parseInt(dossierid));
			
			if(dossier.getId() != null){
            	dHelper.populateInfractionFor(dossier);
            }
			
			String valeur;
			for (InfractionBean i : iHelper.getAllInfractions()) {
				
				valeur = request.getParameter( i.getId().toString() );
				if(valeur != null && valeur.trim().length() != 0
						&& !dossier.getInfractions().contains(i)){
					diHelper.addInfraction(dossier, i);
				}else if(valeur == null
						&& dossier.getInfractions().contains(i)){
					diHelper.removeInfraction(dossier, i);
				}
				
				
			}
			
			response.sendRedirect(request.getContextPath() + "/pages/protected/dossiers/dossier?id=" + dossier.getId());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
