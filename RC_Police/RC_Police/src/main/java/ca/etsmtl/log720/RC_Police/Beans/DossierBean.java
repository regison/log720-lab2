/**
 * 
 */
package ca.etsmtl.log720.RC_Police.Beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author csimon
 *
 */
public class DossierBean extends EntityBean{
	
	
	public final static String DOSSIER_COL_ID = "id";
	public final static String DOSSIER_COL_NOM = "nom";
	public final static String DOSSIER_COL_PRENOM = "prenom";
	public final static String DOSSIER_COL_NUMPLAQUE = "noplaque";
	public final static String DOSSIER_COL_NUMPERMIS = "nopermis";

	String nom, prenom, noPlaque, noPermis;

	List<InfractionBean> infractions;
	
	public DossierBean() {
		id = null;
		nom = prenom = noPlaque = noPermis = "";
		
		infractions = new ArrayList<InfractionBean>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
		this.updateRn_Descriptor();
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
		this.updateRn_Descriptor();
	}

	public String getNoPlaque() {
		return noPlaque;
	}

	public void setNoPlaque(String noPlaque) {
		this.noPlaque = noPlaque;
		this.updateRn_Descriptor();
	}

	public String getNoPermis() {
		return noPermis;
	}
	
	public void setNoPermis(String noPermis) {
		this.noPermis = noPermis;
		this.updateRn_Descriptor();
	}


	public List<InfractionBean> getInfractions() {
		return infractions;
	}
	
	protected void updateRn_Descriptor() {
		super.setRn_Descriptor(this.noPermis + "-" + this.prenom + " " + this.nom + " : " + this.noPlaque);
		
	}

}
