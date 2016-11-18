package ca.etsmtl.log720.RC_Police.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class DataGridContainer {

	public final static String NULL_VALUE = "NULL";
	
	private String Id;
	private HashMap<Integer,String> columns;
	private List<HashMap<String,Object>> values;
	
	public DataGridContainer(String Id) {
		this.Id = "DataGrid-" + Id;
		this.columns = new HashMap<Integer, String>();
		this.values = new ArrayList<HashMap<String,Object>>();
	}

	public String getId() {
		return Id;
	}

	public List<String> getColumns() {
		return new ArrayList<String>(this.columns.values());
	}

	public List<HashMap<String, Object>> getValues() {
		return new ArrayList<HashMap<String,Object>>(this.values);
	}
	
	public void addColumns(int order, String columnName){
			int currentOrder = order;
			String previousValue = this.columns.put(currentOrder, columnName);
			while(previousValue != null){
				currentOrder++;
				previousValue = this.columns.put(currentOrder, columnName);
			}
	}
	
	public boolean addColumns(String columnName){
		if(columnName != null)
		{
			this.addColumns(this.columns.size(), columnName);
			return true;
		}
		
		return false;
	}
	
	public boolean addValue(String rowId, Set<Entry<String, Object>> row){
		if(rowId == null || row == null || row.isEmpty())
			return false;
		
		for (Entry<String, Object> entry : row) {
			
			if(entry.getKey() != null && this.getColumns().contains(entry.getKey())){
				if(entry.getValue() == null){
					return false;
				}
			}
			
		}
		
		
		return false;
	}
	

}
