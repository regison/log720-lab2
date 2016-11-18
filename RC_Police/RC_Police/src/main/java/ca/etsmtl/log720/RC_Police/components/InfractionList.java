package ca.etsmtl.log720.RC_Police.components;

import java.util.ArrayList;
import java.util.List;

import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;

public class InfractionList {

	List<InfractionBean> infraction;
	
	public InfractionList() {
		infraction = new ArrayList<InfractionBean>();
	}

	public List<InfractionBean> getInfraction() {
		return infraction;
	}

	public void setInfraction(List<InfractionBean> infraction) {
		this.infraction = infraction;
	}
	
	

}
