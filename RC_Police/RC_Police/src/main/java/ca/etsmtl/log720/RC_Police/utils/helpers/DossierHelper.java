package ca.etsmtl.log720.RC_Police.utils.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;
import ca.etsmtl.log720.RC_Police.Beans.ReactionBean;



public class DossierHelper extends BaseHelper{

	
	public static enum Fields
	{
		ID,
		NOM,
		PRENOM,
		NOPLAQUE,
		NOPERMIS,
	}
		
	public DossierHelper(javax.sql.DataSource ds) {
		super(ds);
	}
	
	public DossierBean getDosssierBy(int id) throws SQLException
	{
		
		DossierBean dossier = new DossierBean();
		
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
			con = myDB.getConnection();
			   
			con.setAutoCommit(false);
		
			pstmt = con.prepareStatement(
		   		"select id, nom, prenom, nopermis, noplaque "+
				"from dossier " +
				"where id = ?");
			pstmt.setInt(1, id);
			results = pstmt.executeQuery();
		
			while (results.next()) {
				dossier.setId(results.getInt(DossierHelper.Fields.ID.name()));
				dossier.setNom(results.getString(DossierHelper.Fields.NOM.name()));
				dossier.setPrenom(results.getString(DossierHelper.Fields.PRENOM.name()));
				dossier.setNoPlaque(results.getString(DossierHelper.Fields.NOPLAQUE.name()));
				dossier.setNoPermis(results.getString(DossierHelper.Fields.NOPERMIS.name()));
				break;
			}
		   
		   pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
	   return dossier;
	}
	
	public List<DossierBean> getDosssiersBy(DossierHelper.Fields field, String value) throws SQLException
	{
		
		List<DossierBean> dossiers = new ArrayList<DossierBean>();
		 
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
		
        con = myDB.getConnection();
	   
		con.setAutoCommit(false);
	
		pstmt = con.prepareStatement(
	   		"select id, nom, prenom, nopermis, noplaque "+
			"from dossier " +
			"where " + field.name() + " = ?");
		pstmt.setString(1, value);
		results = pstmt.executeQuery();
	
		DossierBean dossier ;
		 while (results.next()) {
			dossier = new DossierBean();
			dossier.setId(results.getInt(DossierHelper.Fields.ID.name()));
			dossier.setNom(results.getString(DossierHelper.Fields.NOM.name()));
			dossier.setPrenom(results.getString(DossierHelper.Fields.PRENOM.name()));
			dossier.setNoPlaque(results.getString(DossierHelper.Fields.NOPLAQUE.name()));
			dossier.setNoPermis(results.getString(DossierHelper.Fields.NOPERMIS.name()));
			dossiers.add(dossier);
		}
	   
	   pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
	   return dossiers;
	}
		
	public List<InfractionBean> getInfractionBy(int dossierId) throws SQLException
	{
		
		List<InfractionBean> infractions = new ArrayList<InfractionBean>();
		 
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(false);
		
			pstmt = con.prepareStatement(
	          		"select i.id, i.description, i.idniveau "+
						"from infraction i " +
	          		"inner join dossier__infraction di " +
	          		"on i.id = di.idinfraction " +
						"where di.iddossier = ?");
	          
			pstmt.setInt(1, dossierId);
			results = pstmt.executeQuery();
	        
	          
			InfractionBean infraction;
			while (results.next()) {
				infraction = new InfractionBean();
				infraction.setId(results.getInt(InfractionHelper.Fields.ID.name()));
				infraction.setDescription(results.getString(InfractionHelper.Fields.DESCRIPTION.name()));
				infraction.setSeverite(results.getInt(InfractionHelper.Fields.IDNIVEAU.name()));
				
				infractions.add(infraction);
					
			}
	          
			pstmt.close();
	          
 		}finally {
 			if (con != null) con.close();
 		}
	   
	   return infractions;
	}
		
	public List<ReactionBean> getReactionsBy(int dossierId) throws SQLException
	{
		
		List<ReactionBean> reactions = new ArrayList<ReactionBean>();
		 
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(false);
		
			pstmt = con.prepareStatement(
	          		"select r.id, r.description "+
						"from reaction r " +
	          		"inner join reaction__dossier dr " +
	          		"on r.id = dr.idreaction " +
						"where dr.iddossier = ?");
	          
			pstmt.setInt(1, dossierId);
			results = pstmt.executeQuery();
	        
	          
			ReactionBean reaction;
			while (results.next()) {
				reaction = new ReactionBean();
				reaction.setId(results.getInt(InfractionHelper.Fields.ID.name()));
				reaction.setDescription(results.getString(InfractionHelper.Fields.DESCRIPTION.name()));
				
				reactions.add(reaction);
					
			}
	          
			pstmt.close();
	          
 		}finally {
 			if (con != null) con.close();
 		}
	   
	   return reactions;
	}
	
	public void populateInfractionFor(DossierBean dossier) throws SQLException
	{
		dossier.getInfractions().clear();
		for(InfractionBean infraction : this.getInfractionBy(dossier.getId()))
		{
				dossier.getInfractions().add(infraction);
				
		}
	         
	}
	
	public void populateReactionsFor(DossierBean dossier) throws SQLException
	{
		dossier.getReactions().clear();
		for(ReactionBean reaction : this.getReactionsBy(dossier.getId()))
		{
				dossier.getReactions().add(reaction);
				
		}
	         
	}
	
	public Integer saveNewDossier(DossierBean dossier) throws Exception {
		Connection con = null;
        PreparedStatement pstmt = null;
        
        
        
		try{		 
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(true);
		
			Integer nextId = this.getLastId()  +1;
			
			pstmt = con.prepareStatement(
	          		"insert into dossier"
	          		+ "(id, nom, prenom, noPermis, noplaque, idniveau) "
	          		+ "values"
	          		+ "(?,?,?,?,?,0)");
	          
				pstmt.setInt(1, nextId);
				pstmt.setString(2, dossier.getNom());
				pstmt.setString(3, dossier.getPrenom());
				pstmt.setString(4, dossier.getNoPermis());
				pstmt.setString(5, dossier.getNoPlaque());
		        pstmt.executeUpdate();
	        
	          pstmt.close();
	          
	          dossier.setId(nextId);
	          return dossier.getId();
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
	   		"select count(*) as countdossier "+
			"from dossier ");
		results = pstmt.executeQuery();
	
		
		while (results.next()) {
			lastId = results.getInt("countdossier") ;
			break;
		}
	   
	   pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
		
	   return lastId;
	}	

}
