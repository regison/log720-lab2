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

	String nom, prenom, noPlaque, noPermis;

	List<InfractionBean> infractions;
	List<ReactionBean> reactions;
	
	public DossierBean() {
		id = null;
		nom = prenom = noPlaque = noPermis = "";
		
		infractions = new ArrayList<InfractionBean>();
		reactions = new ArrayList<ReactionBean>();
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
	
	public int getSeverite(){
		int maxSeveriteInfractions = 0;
		for(InfractionBean infraction : this.getInfractions()){
			maxSeveriteInfractions = Math.max(maxSeveriteInfractions, infraction.getSeverite());
		}
		
		return maxSeveriteInfractions;
	}

	public List<InfractionBean> getInfractions() {
		return infractions;
	}
	
	public List<ReactionBean> getReactions() {
		return reactions;
	}
	
	protected void updateRn_Descriptor() {
		super.setRn_Descriptor(this.noPermis + "-" + this.prenom + " " + this.nom + " : " + this.noPlaque);
		
	}

}
