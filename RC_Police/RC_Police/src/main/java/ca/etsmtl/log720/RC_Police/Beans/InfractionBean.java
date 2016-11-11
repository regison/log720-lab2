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
public class InfractionBean extends EntityBean {
		
	
	public final static String INFRACTION_COL_ID = "id";
	public final static String INFRACTION_COL_DESCRIPTION = "description";
	public final static String INFRACTION_COL_SEVERITE = "severite";
	
	String description;
	private Integer severite;
	
	List<DossierBean> dossiers;
	
	
	
	public InfractionBean() {
		super();
		description = "";
		severite = null;
		
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

	public Integer getSeverite() {
		return this.severite;
	}

	public void setSeverite(int severite) {
		this.severite = severite;
	}

}
