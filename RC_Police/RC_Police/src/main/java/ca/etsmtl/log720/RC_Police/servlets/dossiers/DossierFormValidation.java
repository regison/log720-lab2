package ca.etsmtl.log720.RC_Police.servlets.dossiers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;

public class DossierFormValidation {
	
	private javax.sql.DataSource myDB;

    @Resource(name="jdbc/TestJeeDB")
    private void setMyDB(javax.sql.DataSource ds) {
        myDB = ds;
    }
		

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
	    return resultat;
	}

	public Map<String, String> getErreurs() {
	    return erreurs;
	}

	

	public DossierBean ValidateDossier ( HttpServletRequest request ) {
		
	    String id = getValeurChamp( request, DossierBean.DOSSIER_COL_ID );
	    String nom = getValeurChamp( request, DossierBean.DOSSIER_COL_NOM );
	    String prenom = getValeurChamp( request, DossierBean.DOSSIER_COL_PRENOM );
	    String numPlaque = getValeurChamp( request, DossierBean.DOSSIER_COL_NUMPLAQUE );
	    String numPermis = getValeurChamp( request, DossierBean.DOSSIER_COL_NUMPERMIS );

	    DossierBean dossier = new DossierBean();

	    try {
	        validationNom( nom );
	    } catch ( Exception e ) {
	        setErreur( DossierBean.DOSSIER_COL_NOM, e.getMessage() );
	    }
	    dossier.setNom( nom );
	    
	    try {
	        validationPrenom( prenom );
	    } catch ( Exception e ) {
	        setErreur( DossierBean.DOSSIER_COL_PRENOM, e.getMessage() );
	    }
	    
	    try {
	        validationNumPlaque( numPlaque );
	    } catch ( Exception e ) {
	        setErreur( DossierBean.DOSSIER_COL_NUMPLAQUE, e.getMessage() );
	    }
	    
	    try {
	        validationNumPermis( numPermis );
	    } catch ( Exception e ) {
	        setErreur( DossierBean.DOSSIER_COL_NUMPERMIS, e.getMessage() );
	    }

	    if ( erreurs.isEmpty() ) {
	        resultat = "Succès de l'inscription.";
	        
	        
	    } else {
	        resultat = "Échec de l'inscription.";
	    }

	    return dossier;
	}

	
	private void validationNom( String nom ) throws Exception {
		if(nom == null)
			throw new Exception( "Merci de saisir le nom" );
	}
	
	private void validationPrenom( String prenom ) throws Exception {
		if(prenom == null)
			throw new Exception( "Merci de saisir le Prenom" );
	}
	
	private void validationNumPermis( String numPermis ) throws Exception {
		if(numPermis == null)
			throw new Exception( "Merci de saisir le numero de permis" );
	}
	
	private void validationNumPlaque( String numPlaque ) throws Exception {
		if(numPlaque != null){
			
			ResultSet results;
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
		            results = pstmt.();
		
		            
		            while (results.next()) {
						
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
		else
			throw new Exception( "Merci de saisir le numero de plaque" );
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur( String champ, String message ) {
	    erreurs.put( champ, message );
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
	
	
}
