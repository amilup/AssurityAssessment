package com.assurity.stepDefinitionsHelper;

import java.util.List;

import org.json.JSONException;

import com.assurity.commons.GlobalSetup;

public class CategoriesDetailsStepsHelper {
	
	/**
     * Created by Amila on 26/08/2018.
     * This is return the description for given name
     * @param nane the name of the Promotions name
     */
	public static String ReadArray(String name) throws JSONException{
    	String description = "";
    	List<String> arrCount = GlobalSetup.response.body().path("Promotions");
    	for(int itemCount = 0; itemCount < arrCount.size(); itemCount++){
    		String promotionsName = GlobalSetup.response.body().path("Promotions[" + itemCount + "].Name").toString();
    		if(promotionsName.equals(name)){
    			description = GlobalSetup.response.body().path("Promotions[" + itemCount + "].Description").toString();
    			break;
    		}
    	}
		return description;
    }

}
