package com.onebox.onebox;

import org.json.JSONObject;

public class ListSpec {
	
	public Integer rowCount=100;
	public Integer startIndex=0;
	public JSONObject searchSpecs=new JSONObject();
	public String sortField;
	public String sortOrder;
	public Long parentId=null;
	public String parentField;
	
	public ListSpec() {
	}
	
	public ListSpec(JSONObject listSpec) throws Exception {
		for(String key: listSpec.keySet()) {
			if(key.equals("rowCount")) {
				rowCount=listSpec.getInt("rowCount");
			}else if(key.equals("startIndex")) {
				startIndex=listSpec.getInt("startIndex");
			}else if(key.equals("searchSpecs")) {
				searchSpecs=listSpec.getJSONObject("searchSpecs");
			}else if(key.equals("sortField")) {
				sortField=listSpec.getString("sortField");
			}else if(key.equals("sortOrder")) {
				sortOrder=listSpec.getString("sortOrder");
			}else if(key.equals("parentId")) {
				parentId=listSpec.getLong("parentId");
			}else if(key.equals("parentField")) {
				parentField=listSpec.getString("parentField");
			}else{
				throw new Exception("Invalid param");
			}
		}
	}
}
