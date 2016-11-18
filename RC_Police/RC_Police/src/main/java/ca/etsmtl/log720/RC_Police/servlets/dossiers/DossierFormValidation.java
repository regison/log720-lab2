package ca.etsmtl.log720.RC_Police.servlets.dossiers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ca.etsmtl.log720.RC_Police.Beans.DossierBean;
import ca.etsmtl.log720.RC_Police.utils.helpers.DossierHelper;

public class DossierFormValidation {
	
	private javax.sql.DataSource myDB;
		
	/**
	 * Use for render in JSP
	 */
	public DossierFormValidation() {
		
	}
	
	public DossierFormValidation(javax.sql.DataSource ds) {
		 myDB = ds;
		 erreurs = new HashMap<String, String>();
	}

	private String resultat;
	private Map<String, String> erreurs;

	public String getResultat() {
	    return resultat;
	}

	public Map<String, String> getErreurs() {
	    return erreurs;
	}

	

	public DossierBean ValidateDossier ( HttpServletRequest request)  {
		
		
	    String nom = getValeurChamp( request, "Nom");
	    String prenom = getValeurChamp( request, "Prenom");
	    String numPlaque = getValeurChamp( request, "NoPlaque" );
	    String numPermis = getValeurChamp( request, "NoPermis" );

	    System.out.println(nom);
	    
	    DossierBean dossier = new DossierBean();

	    try {
	        validationNom( nom );
	        dossier.setNom( nom );
	    } catch ( Exception e ) {
	        setErreur( "Nom", e.getMessage() );
	    }
	   
	    
	    try {
	        validationPrenom( prenom );
	        dossier.setPrenom(prenom);
	    } catch ( Exception e ) {
	        setErreur( "Prenom", e.getMessage() );
	    }
	    
	    try {
	        validationNumPlaque( numPlaque );
	        dossier.setNoPlaque(numPlaque);
	    } catch ( Exception e ) {
	        setErreur( "NoPlaque", e.getMessage() );
	    }
	    
	    try {
	        validationNumPermis( numPermis );
	        dossier.setNoPermis(numPermis);
	    } catch ( Exception e ) {
	        setErreur( "NoPermis" , e.getMessage() );
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
		if(numPermis != null){
					
	        try {
	           DossierHelper dHelper = new DossierHelper(myDB);
	           
	           List<DossierBean> dossiers = dHelper.getDosssiersBy(DossierHelper.Fields.NOPERMIS, numPermis);
		       
	           if(dossiers.size() > 0){
	        	   throw new Exception( "Le numero de permis existe deja" );
	           }
	           System.out.println(dossiers.size());
	        
	        } catch (SQLException e) {
	        	throw new Exception( "Erruer interne lors de la validation" );
			}
		}
		else
			throw new Exception( "Merci de saisir le numero de permis" );
	}
	
	private void validationNumPlaque( String numPlaque ) throws Exception {
		if(numPlaque == null)
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
