package ca.etsmtl.log720.RC_Police.components;

import java.util.ArrayList;
import java.util.List;

import ca.etsmtl.log720.RC_Police.Beans.InfractionBean;
import ca.etsmtl.log720.RC_Police.Beans.ReactionBean;

public class ReactionsList {

	List<ReactionBean> reactions;
	
	public ReactionsList() {
		reactions = new ArrayList<ReactionBean>();
	}

	public List<ReactionBean> getReactions() {
		return reactions;
	}

	public void setReactions(List<ReactionBean> reactions) {
		this.reactions = reactions;
	}
	
	

}
