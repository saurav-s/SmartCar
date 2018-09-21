package com.smartcar.util;

import com.google.gson.Gson;


public class JsonUtil {
	
    /**
     * get the JSON format of object
     *
     * @param str string to be converted
     * @return JSON string value
     */
    public static String getJsonString(Object obj) {
    	Gson gson = new Gson();
    	return gson.toJson(obj);
    }
    
}
