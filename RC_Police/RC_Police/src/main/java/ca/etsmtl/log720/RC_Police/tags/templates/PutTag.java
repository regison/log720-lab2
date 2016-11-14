package ca.etsmtl.log720.RC_Police.tags.templates;

import java.util.Hashtable;
import java.util.Stack;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import ca.etsmtl.log720.RC_Police.Beans.templates.PageParameter;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class PutTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -719164997504268822L;
	private String name, content = "", direct="false";

	public void setName(String s) { name = s; }
	public void setContent(String s) {content = s; }
	public void setDirect(String s) { direct = s; }

	public int doStartTag() throws JspException {
		InsertTag parent = (InsertTag)getAncestor(
										"ca.etsmtl.log720.RC_Police.tags.templates.InsertTag");
		if(parent == null)
			throw new JspException("PutTag.doStartTag(): " +
										  "No InsertTag ancestor");

		Stack template_stack = parent.getStack();
		
		if(template_stack == null) 
			throw new JspException("PutTag: no template stack");	

		Hashtable params = (Hashtable)template_stack.peek();

		if(params == null) 
			throw new JspException("PutTag: no hashtable");	
		//System.out.println(name + " -> '" +content.trim() +"'");
		if(!content.trim().equals("")){
			params.put(name, new PageParameter(content,direct));
			return SKIP_BODY;
		}else
			return EVAL_BODY_BUFFERED;
		
		
	}
	
	public int doAfterBody() throws JspException {
		
		InsertTag parent = (InsertTag)getAncestor(
				"ca.etsmtl.log720.RC_Police.tags.templates.InsertTag");
		if(parent == null)
		throw new JspException("PutTag.doStartTag(): " +
						  "No InsertTag ancestor");
		
		Stack template_stack = parent.getStack();
		
		if(template_stack == null) 
		throw new JspException("PutTag: no template stack");	
		
		Hashtable params = (Hashtable)template_stack.peek();
		
		if(params == null) 
		throw new JspException("PutTag: no hashtable");	
		
		params.put(name, new PageParameter(super.getBodyContent().getString(),"true"));
		
		return SKIP_BODY;
    }
	
	public void release() {
		name = content = direct = null;
	}
	private TagSupport getAncestor(String className) 
											throws JspException {
		Class klass = null; // can’t name variable "class"
		try {
			klass = Class.forName(className);
		}
		catch(ClassNotFoundException ex) {
			throw new JspException(ex.getMessage());
		}
		return (TagSupport)findAncestorWithClass(this, klass);
	}
}
