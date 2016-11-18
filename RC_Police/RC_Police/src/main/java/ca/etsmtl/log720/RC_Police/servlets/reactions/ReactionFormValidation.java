package ca.etsmtl.log720.RC_Police.servlets.reactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;
import ca.etsmtl.log720.RC_Police.Beans.ReactionBean;
import ca.etsmtl.log720.RC_Police.utils.helpers.DossierHelper;

public class ReactionFormValidation {
	
	private javax.sql.DataSource myDB;
		
	public ReactionFormValidation(javax.sql.DataSource ds) {
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

	

	public ReactionBean ValidateReaction ( HttpServletRequest request)  {
		
		
	    String description = getValeurChamp( request, "Description");
	    ReactionBean reaction = new ReactionBean();

	    try {
	        validationDescription( description );
	        reaction.setDescription(description);
	    } catch ( Exception e ) {
	        setErreur( "Description", e.getMessage() );
	    }
	    
	    if ( erreurs.isEmpty() ) {
	        resultat = "Succès de l'enregistrement.";
	        
	        
	    } else {
	        resultat = "Échec de l'enregistrement.";
	    }

	    return reaction;
	}

	
	private void validationDescription( String nom ) throws Exception {
		if(nom == null)
			throw new Exception( "Merci de saisir la description" );
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
