package ca.etsmtl.log720.RC_Police.utils.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;
import ca.etsmtl.log720.RC_Police.Beans.ReactionBean;

public class Dossiers__ReactionsHelper extends BaseHelper {

	
	public static enum Fields
	{
		IDDOSSIER,
		IDREACTION
	}
	
	public Dossiers__ReactionsHelper(javax.sql.DataSource ds) {
		super(ds);
	}
	
	public void addReaction(DossierBean dossier, ReactionBean reaction) throws SQLException {
		System.out.println("add infraction (id=" + reaction.getId() + ") for dossier (id=" + dossier.getId() + ")");
		
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(true);
			
			pstmt = con.prepareStatement(
	          		"insert into reaction__dossier "
	          		+ "(iddossier, idreaction) "
	          		+ "values"
	          		+ "(?,?)");
	          
			pstmt.setInt(1, dossier.getId());
			pstmt.setInt(2, reaction.getId());
	        pstmt.executeUpdate();
	        
	        pstmt.close();
	          
		}catch(SQLException e){
		      e.printStackTrace();
		      throw e;
		}finally {
			if (con != null) con.close();
		}
	}

	public void removeReaction(DossierBean dossier, ReactionBean reaction) throws SQLException {
		System.out.println("remove reaction (id=" + reaction.getId() + ") for dossier (id=" + dossier.getId() + ")");
		
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
	      
			 con = myDB.getConnection();
			   
			con.setAutoCommit(true);

			pstmt = con.prepareStatement(
	          		"delete from reaction__dossier "
	          		+ " where iddossier=? "
	          		+ "and idreaction=?");
	          
			pstmt.setInt(1, dossier.getId());
			pstmt.setInt(2, reaction.getId());
	        pstmt.executeUpdate();
	        
	        pstmt.close();
	          
		}catch(SQLException e){
		      e.printStackTrace();
		      throw e;
		}finally {
			if (con != null) con.close();
		}
	}

}
