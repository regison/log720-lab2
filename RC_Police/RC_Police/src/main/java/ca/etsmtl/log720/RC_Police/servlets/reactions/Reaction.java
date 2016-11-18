package ca.etsmtl.log720.RC_Police.servlets.reactions;

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
import ca.etsmtl.log720.RC_Police.Beans.ReactionBean;
import ca.etsmtl.log720.RC_Police.servlets.dossiers.DossierFormValidation;
import ca.etsmtl.log720.RC_Police.utils.helpers.DossierHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.InfractionHelper;
import ca.etsmtl.log720.RC_Police.utils.helpers.ReactionHelper;

/**
 * Servlet implementation class Dossier
 */
@WebServlet("/pages/protected/reactions/reaction")
public class Reaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	private javax.sql.DataSource myDB;

    @Resource(name="jdbc/TestJeeDB")
    private void setMyDB(javax.sql.DataSource ds) {
        myDB = ds;
    }
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String record_id = request.getParameter("id");
		String dossier_id = request.getParameter("dossierid");
		
		ReactionBean reaction = new ReactionBean();
		
		if(record_id != null && !record_id.equals(""))
		{
	        try {
		          ReactionHelper iHelper = new ReactionHelper(myDB);
		          
		          reaction = iHelper.getReactionBy(Integer.parseInt(record_id));
		            
		          if(reaction.getId() != null){
		        	  iHelper.populateDossierFor(reaction);
		          }
		
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.setAttribute("reaction", reaction);
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/reactions/reaction.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReactionFormValidation validation = new ReactionFormValidation(myDB);
		
		
		
		ReactionBean reaction = validation.ValidateReaction(request);
		
		ReactionHelper iHelper = new ReactionHelper(myDB);
		try {
			int new_id = iHelper.saveNewReaction(reaction);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("form", validation);
		request.setAttribute("reaction", reaction);
		
		this.getServletContext().getRequestDispatcher( "/pages/protected/reactions/reaction.jsp" ).forward( request, response );
		
		

	}

}
