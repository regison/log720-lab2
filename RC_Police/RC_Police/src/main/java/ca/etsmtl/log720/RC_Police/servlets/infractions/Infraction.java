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
		
		ResultSet results;
		
		if(record_id != null && !record_id.equals(""))
		{
			Connection con = null;
	        PreparedStatement pstmt;
	        try {
		        try {
		            con = myDB.getConnection();
		            
					con.setAutoCommit(false);
					
		            pstmt = con.prepareStatement(
		            		"select id, description "+
							"from infraction " +
							"where id = ?");
		            pstmt.setInt(1, Integer.parseInt(record_id));
		            results = pstmt.executeQuery();
		
		          
		            while (results.next()) {
		            	infraction.setId(results.getInt(InfractionBean.INFRACTION_COL_ID));
		            	infraction.setDescription(results.getString(InfractionBean.INFRACTION_COL_DESCRIPTION));
						break;
					}
		            
		            
		            /* fetch associated Dossiers*/
		            
		            pstmt = con.prepareStatement(
		            		"select d.id, d.nom, d.prenom, d.nopermis, d.noplaque  "+
							"from dossier d " +
		            		"inner join dossier__infraction di " +
		            		"on d.id = di.iddossier " +
							"where di.idinfraction = ?");
		            
		            pstmt.setInt(1, infraction.getId());
		            results = pstmt.executeQuery();
		
		            DossierBean dossier;
		            while (results.next()) {
		            	dossier = new DossierBean();
		            	dossier.setId(results.getInt(DossierBean.DOSSIER_COL_ID));
						dossier.setNom(results.getString(DossierBean.DOSSIER_COL_NOM));
						dossier.setPrenom(results.getString(DossierBean.DOSSIER_COL_PRENOM));
						dossier.setNoPlaque(results.getString(DossierBean.DOSSIER_COL_NUMPLAQUE));
						dossier.setNoPermis(results.getString(DossierBean.DOSSIER_COL_NUMPERMIS));
						
						infraction.getDossiers().add(dossier);
						
					}
		            
		            pstmt.close();
		
		            
		        } finally {
		            if (con != null) con.close();
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
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

	}

}
