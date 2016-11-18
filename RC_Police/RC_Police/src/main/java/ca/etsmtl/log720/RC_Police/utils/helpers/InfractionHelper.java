package ca.etsmtl.log720.RC_Police.utils.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;


public class InfractionHelper {

	
	public static enum Fields
	{
		ID,
		DESCRIPTION, IDNIVEAU
	}
	
	private javax.sql.DataSource myDB;

	
	public InfractionHelper(javax.sql.DataSource ds) {
		 myDB = ds;
	}
	
	public InfractionBean getInfractionBy(int id) throws SQLException
	{
		
		InfractionBean infraction = new InfractionBean();
		
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
		
        con = myDB.getConnection();
	   
		con.setAutoCommit(false);
	
		pstmt = con.prepareStatement(
        		"select id, description, idniveau "+
				"from infraction " +
				"where id = ?");
        pstmt.setInt(1, id);
        results = pstmt.executeQuery();

      
        while (results.next()) {
        	infraction.setId(results.getInt(InfractionHelper.Fields.ID.name()));
        	infraction.setDescription(results.getString(InfractionHelper.Fields.DESCRIPTION.name()));
        	infraction.setSeverite(results.getInt(InfractionHelper.Fields.IDNIVEAU.name()));
			break;
		}
	   
	   pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
	   return infraction;
	}
	
	public List<InfractionBean> getInfractionBy(InfractionHelper.Fields field, String value) throws SQLException
	{
		
		List<InfractionBean> dossiers = new ArrayList<InfractionBean>();
		 
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
		
        con = myDB.getConnection();
	   
		con.setAutoCommit(false);

		pstmt = con.prepareStatement(
        		"select id, description, idniveau  "+
				"from infraction " +
				"where id = ?");
		pstmt.setString(1, value);
		results = pstmt.executeQuery();
	
		InfractionBean infraction ;
		 while (results.next()) {
			 infraction = new InfractionBean();
			 infraction.setId(results.getInt(InfractionHelper.Fields.ID.name()));
			 infraction.setDescription(results.getString(InfractionHelper.Fields.DESCRIPTION.name()));
	        	infraction.setSeverite(results.getInt(InfractionHelper.Fields.IDNIVEAU.name()));
				
			dossiers.add(infraction);
		}
	   
	   pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
	   return dossiers;
	}
	
	
	public List<DossierBean> getDossiersBy(int infractionId) throws SQLException
	{
		
		List<DossierBean> infractions = new ArrayList<DossierBean>();
		 
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
        con = myDB.getConnection();
	   
		con.setAutoCommit(false);
	
		/* fetch associated Dossiers*/
        
        pstmt = con.prepareStatement(
        		"select d.id, d.nom, d.prenom, d.nopermis, d.noplaque  "+
				"from dossier d " +
        		"inner join dossier__infraction di " +
        		"on d.id = di.iddossier " +
				"where di.idinfraction = ?");
        
        pstmt.setInt(1, infractionId);
        results = pstmt.executeQuery();

        DossierBean dossier;
        while (results.next()) {
        	dossier = new DossierBean();
        	dossier.setId(results.getInt(DossierHelper.Fields.ID.name()));
			dossier.setNom(results.getString(DossierHelper.Fields.NOM.name()));
			dossier.setPrenom(results.getString(DossierHelper.Fields.PRENOM.name()));
			dossier.setNoPlaque(results.getString(DossierHelper.Fields.NOPLAQUE.name()));
			dossier.setNoPermis(results.getString(DossierHelper.Fields.NOPERMIS.name()));
			
			infractions.add(dossier);
			
		}
        
        pstmt.close();
          
     		}finally {
     			if (con != null) con.close();
     		}
	   
	   return infractions;
	}
	
	
	public void populateDossierFor(InfractionBean infraction) throws SQLException
	{
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(false);
		
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
	            	dossier.setId(results.getInt(DossierHelper.Fields.ID.name()));
					dossier.setNom(results.getString(DossierHelper.Fields.NOM.name()));
					dossier.setPrenom(results.getString(DossierHelper.Fields.PRENOM.name()));
					dossier.setNoPlaque(results.getString(DossierHelper.Fields.NOPLAQUE.name()));
					dossier.setNoPermis(results.getString(DossierHelper.Fields.NOPERMIS.name()));
					
					infraction.getDossiers().add(dossier);
					
				}
	            
	            pstmt.close();
          
		}finally {
			if (con != null) con.close();
		}
	}
	
	

	public Integer saveNewInfration(InfractionBean infraction) throws Exception {
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
        
        
		try{		 
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(true);
		
			Integer nextId = this.getLastId()  +1;
			
			pstmt = con.prepareStatement(
	          		"insert into infraction"
	          		+ "(id, description, idniveau) "
	          		+ "values"
	          		+ "(?,?,?)");
	          
				pstmt.setInt(1, nextId);
				pstmt.setString(2, infraction.getDescription());
				pstmt.setInt(3, infraction.getSeverite());
		        pstmt.executeUpdate();
	        
	          pstmt.close();
	          
	          infraction.setId(nextId);
	          return infraction.getId();
		}catch(SQLException e){
		      e.printStackTrace();
		      throw e;
		}catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
              throw e;
		}finally {
			if (con != null) con.close();
		}
		
		
	}

	private int getLastId() throws SQLException {
		
		Integer lastId = null;
		
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(false);
		
			pstmt = con.prepareStatement(
		   		"select count(*) as countinfraction "+
				"from infraction ");
			results = pstmt.executeQuery();
		
			
			while (results.next()) {
				lastId = results.getInt("countinfraction") ;
				break;
			}
	   
			pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
		
	   return lastId;
	}

	public List<InfractionBean> getAllInfractions() throws SQLException {
		
		List<InfractionBean> infractions = new ArrayList<InfractionBean>();
		 
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
			
			con = myDB.getConnection();
		   
			con.setAutoCommit(false);
	
			pstmt = con.prepareStatement(
	        		"select id, description ,idniveau "+
					"from infraction ");
			results = pstmt.executeQuery();
		
			InfractionBean infraction ;
			
			while (results.next()) {
				infraction = new InfractionBean();
				infraction.setId(results.getInt(InfractionHelper.Fields.ID.name()));
				infraction.setDescription(results.getString(InfractionHelper.Fields.DESCRIPTION.name()));
				infraction.setSeverite(results.getInt(InfractionHelper.Fields.IDNIVEAU.name()));				 
					
				infractions.add(infraction);
			}
			 
			pstmt.close();
			return infractions;
			
		}finally {
			if (con != null) con.close();
		}
	   
	}

}
