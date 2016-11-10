package ca.etsmtl.log720.RC_Police.servlets.dossiers;

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
		
		ResultSet results;
		
		if(record_id != null && !record_id.equals(""))
		{
			Connection con = null;
	        PreparedStatement pstmt, pstmt_linkedDossier;
	        try {
		        try {
		            con = myDB.getConnection();
		            
						con.setAutoCommit(false);
					
		            pstmt = con.prepareStatement(
		            		"select id, nom, prenom, nopermis, noplaque "+
							"from dossier " +
							"where id = ?");
		            pstmt.setInt(1, Integer.parseInt(record_id));
		            results = pstmt.executeQuery();
		
		            
		            while (results.next()) {
						dossier.setId(results.getInt(DossierBean.DOSSIER_COL_ID));
						dossier.setNom(results.getString(DossierBean.DOSSIER_COL_NOM));
						dossier.setPrenom(results.getString(DossierBean.DOSSIER_COL_PRENOM));
						dossier.setNoPlaque(results.getString(DossierBean.DOSSIER_COL_NUMPLAQUE));
						dossier.setNoPermis(results.getString(DossierBean.DOSSIER_COL_NUMPERMIS));
						break;
					}
		            

		            pstmt.close();
		            
	            	/* fetch associated infraction*/
		            
		            pstmt_linkedDossier = con.prepareStatement(
		            		"select i.id, i.description "+
							"from infraction i " +
		            		"inner join dossier__infraction di " +
		            		"on i.id = di.idinfraction " +
							"where di.iddossier = ?");
		            
		            pstmt_linkedDossier.setInt(1, dossier.getId());
		            results = pstmt_linkedDossier.executeQuery();
		          
		           
		            
		            
		            
		            
		            InfractionBean infraction;
		            while (results.next()) {
		            	infraction = new InfractionBean();
		            	infraction.setId(results.getInt(InfractionBean.INFRACTION_COL_ID));
		            	infraction.setDescription(results.getString(InfractionBean.INFRACTION_COL_DESCRIPTION));
						
						dossier.getInfractions().add(infraction);
						
					}
		            
		            pstmt_linkedDossier.close();
		
		            
		        } finally {
		            if (con != null) con.close();
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
		
		DossierFormValidation validation = new DossierFormValidation();
		
		DossierBean dossier = (DossierBean) request.getAttribute("dossier");
		if(dossier == null){
			
			
		}
		
		
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/dossiers/Dossier.jsp" ).forward( request, response );
		

	}

}
