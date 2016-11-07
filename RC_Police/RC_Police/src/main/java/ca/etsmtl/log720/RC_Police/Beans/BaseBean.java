package ca.etsmtl.log720.RC_Police.Beans;


public class BaseBean {

	private static BaseBean instance = null;
	    
	public static BaseBean init(){

		
		if(instance == null)
		{
			instance = new BaseBean();
			instance.currentPage = "/pages/welcome.jsp";
			
		}
		return instance;
				
	}
	    
	
    private String currentPage;
    private final String title = "RC_Police";
	private String path;
   
	

	/**
     * Default constructor. 
     */
    public BaseBean() {
    	currentPage = "pages/welcome.jsp";
    }
    
    public String getCurrentPage(){
    	return this.currentPage;
    }
    
    public void navigateTo(String page){
    	
    	this.currentPage = page;
    	
    }
    
    public String getTitle() {
		return title;
	}

	public void setPath(String pathInfo) {
		this.path = pathInfo;
		
	}
	
	public String getPath() {
		return this.path;
		
	}
}
