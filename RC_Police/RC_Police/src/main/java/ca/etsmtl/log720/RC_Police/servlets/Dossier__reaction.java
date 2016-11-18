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
import ca.etsmtl.log720.RC_Police.Beans.ReactionBean;
import ca.etsmtl.log720.RC_Police.components.InfractionList;
import ca.etsmtl.log720.RC_Police.components.ReactionsList;
import ca.etsmtl.log720.RC_Police.servlets.dossiers.DossierFormValidation;
import ca.etsmtl.log720.RC_Police.utils.helpers.DossierHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.Dossiers__InfractionsHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.Dossiers__ReactionsHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.InfractionHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.ReactionHelper;

@WebServlet("/pages/protected/dossier__reaction")
public class Dossier__reaction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private javax.sql.DataSource myDB;

    @Resource(name="jdbc/TestJeeDB")
    private void setMyDB(javax.sql.DataSource ds) {
        myDB = ds;
    }
	
	
	public Dossier__reaction() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DossierHelper dHelper = new DossierHelper(myDB);
		ReactionHelper rHelper = new ReactionHelper(myDB);
		
		String record_id = request.getParameter("dossierid");
		DossierBean dossier = new DossierBean();
		ReactionsList reactions = new ReactionsList();
		
		if(record_id != null && !record_id.equals(""))
		{

	        try {
		        
		            dossier = dHelper.getDosssierBy(Integer.parseInt(record_id));
		            if(dossier.getId() != null){
		            	dHelper.populateInfractionFor(dossier);
		            	dHelper.populateReactionsFor(dossier);
		            	
		            }
		            
		            reactions.setReactions(new ArrayList<ReactionBean>(rHelper.getAllReactions()));
		            
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.setAttribute("dossier", dossier);
		request.setAttribute("reactions", reactions);
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/dossiers__reactions.jsp" ).forward( request, response );
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		String dossierid = (String) request.getParameter("dossierid");
		
		DossierHelper dHelper = new DossierHelper(myDB);
		ReactionHelper iHelper = new ReactionHelper(myDB);
		Dossiers__ReactionsHelper drHelper = new Dossiers__ReactionsHelper(myDB);
		
		
		try {
			DossierBean dossier = dHelper.getDosssierBy(Integer.parseInt(dossierid));
			
			if(dossier.getId() != null){
            	dHelper.populateReactionsFor(dossier);
            }
			
			String valeur;
			for (ReactionBean r : iHelper.getAllReactions()) {
				
				valeur = request.getParameter( r.getId().toString() );
				if(valeur != null && valeur.trim().length() != 0
						&& !dossier.getReactions().contains(r)){
					drHelper.addReaction(dossier, r);
				}else if(valeur == null
						&& dossier.getReactions().contains(r)){
					drHelper.removeReaction(dossier, r);
				}
				
			}
			
			response.sendRedirect(request.getContextPath() + "/pages/protected/dossiers/dossier?id=" + dossier.getId());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
