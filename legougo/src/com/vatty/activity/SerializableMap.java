package com.vatty.activity;

import java.io.Serializable;
import java.util.Map;
/**
 * 
 * @author Barry
 *
 */
public class SerializableMap implements Serializable {
		   
	
		    private Map<String,Object> map;
	 
		    public Map<String, Object> getMap() {
		        return map;
		    }
		 
		    public void setMap(Map<String, Object> map) {
		        this.map = map;
		    }
		   
		  
		}