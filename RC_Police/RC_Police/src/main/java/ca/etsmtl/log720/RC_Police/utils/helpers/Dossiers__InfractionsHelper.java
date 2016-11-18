package ca.etsmtl.log720.RC_Police.utils.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;

public class Dossiers__InfractionsHelper extends BaseHelper {

	
	public static enum Fields
	{
		IDDOSSIER,
		IDINFRACTION
	}
	
	public Dossiers__InfractionsHelper(javax.sql.DataSource ds) {
		super(ds);
	}
	
	public void addInfraction(DossierBean dossier, InfractionBean infraction) throws SQLException {
		System.out.println("add infraction (id=" + infraction.getId() + ") for dossier (id=" + dossier.getId() + ")");
		
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(true);
			
			pstmt = con.prepareStatement(
	          		"insert into dossier__infraction"
	          		+ "(iddossier, idinfraction) "
	          		+ "values"
	          		+ "(?,?)");
	          
			pstmt.setInt(1, dossier.getId());
			pstmt.setInt(2, infraction.getId());
	        pstmt.executeUpdate();
	        
	        pstmt.close();
	          
		}catch(SQLException e){
		      e.printStackTrace();
		      throw e;
		}finally {
			if (con != null) con.close();
		}
	}

	public void removeInfraction(DossierBean dossier, InfractionBean infraction) throws SQLException {
		System.out.println("remove infraction (id=" + infraction.getId() + ") for dossier (id=" + dossier.getId() + ")");
		
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
	      
			 con = myDB.getConnection();
			   
			con.setAutoCommit(true);

			pstmt = con.prepareStatement(
	          		"delete from dossier__infraction "
	          		+ " where iddossier=? "
	          		+ "and idinfraction=?");
	          
			pstmt.setInt(1, dossier.getId());
			pstmt.setInt(2, infraction.getId());
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
