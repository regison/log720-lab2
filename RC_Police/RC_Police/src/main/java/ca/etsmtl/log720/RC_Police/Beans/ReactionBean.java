package ca.etsmtl.log720.RC_Police.Beans;

import java.util.ArrayList;
import java.util.List;

public class ReactionBean extends EntityBean {


	String description;
	
	List<DossierBean> dossiers;
	
	
	
	public ReactionBean() {
		super();
		description = "";
		
		dossiers = new ArrayList<DossierBean>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.updateRn_Descriptor();
	}

	public List<DossierBean> getDossiers() {
		return dossiers;
	}
	
	protected void updateRn_Descriptor() {
		super.setRn_Descriptor(this.description);
	}

}
