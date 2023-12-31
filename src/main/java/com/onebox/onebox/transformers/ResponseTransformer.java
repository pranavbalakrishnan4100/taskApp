package com.onebox.onebox.transformers;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseTransformer {
	
	//TODO: Return response code based on the kind of exception thrown
	
	public static final String EXTRA_PARAM_FOUND="extra_param_found";
	public static final String SUCCESS="success";
	public static final String FAILURE="failure";

	
	public static ResponseEntity<Object> transformToAPIResponse(List resultList, JSONObject listCriteria, HttpStatus httpStatus) {
		return transformToAPIResponse(resultList, httpStatus, null);
	}
	
	public static ResponseEntity<Object> transformToAPIResponse(List resultList, HttpStatus httpStatus) {
		return transformToAPIResponse(resultList,  httpStatus, null);
	}
		
	public static ResponseEntity<Object> transformToAPIResponse(List resultList, HttpStatus httpStatus, Exception ex) {
		JSONArray resultJSONArray=new JSONArray();
		
        for (Object a1 : resultList) {
        	ObjectMapper objectMapper = new ObjectMapper();
            JSONObject objJSON = null;
			try {
				objJSON = new JSONObject(objectMapper.writeValueAsString(a1));
			} catch (JsonProcessingException | JSONException e) {
				e.printStackTrace();
			}
            resultJSONArray.put(objJSON);
        }
        
        JSONObject statusJSON=new JSONObject();
       
        statusJSON.put("code", httpStatus.value());
        statusJSON.put("description", httpStatus.is2xxSuccessful()?SUCCESS: ex==null?FAILURE:ex.getMessage());
        
		JSONObject resultJSON=new JSONObject();
		resultJSON.put("status", statusJSON);
		resultJSON.put("data", resultJSONArray);
		
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type", "application/json");
		
		return new ResponseEntity<>(resultJSON.toString(), headers, httpStatus);
		
	}
}
