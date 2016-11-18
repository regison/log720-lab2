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
import javax.sql.DataSource;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;
import ca.etsmtl.log720.RC_Police.Beans.ReactionBean;


public class ReactionHelper extends BaseHelper {

	
	public static enum Fields
	{
		ID,
		DESCRIPTION
	}
	
	
	public ReactionHelper(DataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	
	public ReactionBean getReactionBy(int id) throws SQLException
	{
		
		ReactionBean reaction = new ReactionBean();
		
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
		
        con = myDB.getConnection();
	   
		con.setAutoCommit(false);
	
		pstmt = con.prepareStatement(
        		"select id, description "+
				"from reaction " +
				"where id = ?");
        pstmt.setInt(1, id);
        results = pstmt.executeQuery();

      
        while (results.next()) {
        	reaction.setId(results.getInt(ReactionHelper.Fields.ID.name()));
        	reaction.setDescription(results.getString(ReactionHelper.Fields.DESCRIPTION.name()));
			break;
		}
	   
	   pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
	   return reaction;
	}
	
	public List<ReactionBean> getReactionBy(ReactionHelper.Fields field, String value) throws SQLException
	{
		
		List<ReactionBean> reactions = new ArrayList<ReactionBean>();
		 
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
		
		
        con = myDB.getConnection();
	   
		con.setAutoCommit(false);

		pstmt = con.prepareStatement(
        		"select id, description "+
				"from reaction " +
				"where id = ?");
		pstmt.setString(1, value);
		results = pstmt.executeQuery();
	
		ReactionBean reaction ;
		 while (results.next()) {
			 reaction = new ReactionBean();
			 reaction.setId(results.getInt(ReactionHelper.Fields.ID.name()));
			 reaction.setDescription(results.getString(ReactionHelper.Fields.DESCRIPTION.name()));
				
			 reactions.add(reaction);
		}
	   
	   pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
	   return reactions;
	}
	
	
	public List<DossierBean> getDossiersBy(int reactionId) throws SQLException
	{
		
		List<DossierBean> dossiers = new ArrayList<DossierBean>();
		 
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
        		"inner join dossier__reaction dr " +
        		"on d.id = dr.iddossier " +
				"where dr.idreaction = ?");
        
        pstmt.setInt(1, reactionId);
        results = pstmt.executeQuery();

        DossierBean dossier;
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
	
	
	public void populateDossierFor(ReactionBean reaction) throws SQLException
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
	            		"inner join dossier__reaction dr " +
	            		"on d.id = dr.iddossier " +
						"where dr.idreaction = ?");
	            
	            pstmt.setInt(1, reaction.getId());
	            results = pstmt.executeQuery();
	
	            DossierBean dossier;
	            reaction.getDossiers().clear();
	            while (results.next()) {
	            	dossier = new DossierBean();
	            	dossier.setId(results.getInt(DossierHelper.Fields.ID.name()));
					dossier.setNom(results.getString(DossierHelper.Fields.NOM.name()));
					dossier.setPrenom(results.getString(DossierHelper.Fields.PRENOM.name()));
					dossier.setNoPlaque(results.getString(DossierHelper.Fields.NOPLAQUE.name()));
					dossier.setNoPermis(results.getString(DossierHelper.Fields.NOPERMIS.name()));
					
					reaction.getDossiers().add(dossier);
					
				}
	            
	            pstmt.close();
          
		}finally {
			if (con != null) con.close();
		}
	}
	
	

	public Integer saveNewReaction(ReactionBean reaction) throws Exception {
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
        
        
		try{		 
		
	        con = myDB.getConnection();
		   
			con.setAutoCommit(true);
		
			Integer nextId = this.getLastId()  +1;
			
			pstmt = con.prepareStatement(
	          		"insert into reaction"
	          		+ "(id, description) "
	          		+ "values"
	          		+ "(?,?)");
	          
				pstmt.setInt(1, nextId);
				pstmt.setString(2, reaction.getDescription());
		        pstmt.executeUpdate();
	        
	          pstmt.close();
	          
	          reaction.setId(nextId);
	          return reaction.getId();
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
		   		"select count(*) as countreaction "+
				"from reaction ");
			results = pstmt.executeQuery();
		
			
			while (results.next()) {
				lastId = results.getInt("countreaction") ;
				break;
			}
	   
			pstmt.close();
	     
			}finally {
				if (con != null) con.close();
			}
		
	   return lastId;
	}

	public List<ReactionBean> getAllReactions() throws SQLException {
		
		List<ReactionBean> reactions = new ArrayList<ReactionBean>();
		 
		ResultSet results;
		Connection con = null;
        PreparedStatement pstmt = null;
        
		try{		 
			
			con = myDB.getConnection();
		   
			con.setAutoCommit(false);
	
			pstmt = con.prepareStatement(
	        		"select id, description "+
					"from reaction ");
			results = pstmt.executeQuery();
		
			ReactionBean reaction ;
			
			while (results.next()) {
				reaction = new ReactionBean();
				reaction.setId(results.getInt(ReactionHelper.Fields.ID.name()));
				reaction.setDescription(results.getString(ReactionHelper.Fields.DESCRIPTION.name()));		 
					
				reactions.add(reaction);
			}
			 
			pstmt.close();
			return reactions;
			
		}finally {
			if (con != null) con.close();
		}
	   
	}

}
