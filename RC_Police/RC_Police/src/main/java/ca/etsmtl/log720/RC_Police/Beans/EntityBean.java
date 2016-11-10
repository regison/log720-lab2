package ca.etsmtl.log720.RC_Police.Beans;

public abstract class EntityBean {

	protected Integer id;
	private String Rn_Descriptor;
	
	
	public EntityBean() {
		id = null;
		Rn_Descriptor = "";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	protected abstract void updateRn_Descriptor();
	
	public String getRn_Descriptor() {
		return Rn_Descriptor;
	}
	
	protected void setRn_Descriptor(String Rn_Descriptor) {
		this.Rn_Descriptor = Rn_Descriptor;
	}
	
	
	
}
