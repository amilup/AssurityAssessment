package com.assurity.commons;

/**
 * Created by Amila on 25/08/2018.
 */
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.assurity.commons.GlobalSetup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestAPIUtility {
	
	static GlobalSetup globalSetup;
	//private static JSONArray arrCount;
	
	public static void CreateBaseUrl() throws Exception{
		
		globalSetup = new GlobalSetup();
		
		String baseUrl = globalSetup.propertyData.getProperty("baseUrl");
				//GlobalSetup.propertyData.getProperty("baseUrl");
        baseUrl = baseUrl + "/" + globalSetup.propertyData.getProperty("vertion");
        GlobalSetup.endPoint = baseUrl; //This should be global parameter

        if(baseUrl.equals(null)){
            throw new Exception("Base URL is not define. Add base URL to property file");
        }
	}
	
    /**
     * Created by Amila on 25/08/2018.
     * This is create the end point base on the base path parameters
     * @param basePathParameter the values need to add to the base url to create end point
     */
    public static void AddParametresToUrl(String basePathParameter) throws Exception {

        //Verify the end character of the endpoint is "/". If not add "/" to end of the URL before adding end parameters
        if(GlobalSetup.endPoint.substring(GlobalSetup.endPoint.length()-1).equals("/")){
        	GlobalSetup.endPoint =  GlobalSetup.endPoint + basePathParameter;
        }else if(String.valueOf(basePathParameter.charAt(0)).equals("/")){
        	GlobalSetup.endPoint =  GlobalSetup.endPoint + basePathParameter;
        }else {
        	GlobalSetup.endPoint =  GlobalSetup.endPoint + "/" + basePathParameter;
        }
    }

    /**
     * Created by Amila on 25/08/2018.
     * This is to add single or multiple query string to API request URL
     * @param queryArray the set of array need to add to the end point as query string
     * @param endPoint is the end point create in CreateBaseUrl()
     */
    public static void AddingQureryStringToEndPoint(String[] queryArray, String endPoint) throws Exception{

        String[] splitArr;

        if(queryArray.length < 2) {
            if(queryArray.length != 0) {
                splitArr = queryArray[0].split(":");
                endPoint = endPoint + "?" + splitArr[0] + "=" + splitArr[1];
            }else {
                throw new Exception("No Value define for query string.");
            }
        }else {
            for(int arrItem = 0; arrItem < queryArray.length; arrItem++) {
                if(arrItem != 0) {
                    splitArr = queryArray[arrItem].split(":");
                    endPoint = endPoint + "&" + splitArr[0] + "=" + splitArr[1];
                }else {
                    splitArr = queryArray[arrItem].split(":");
                    endPoint = endPoint + "?" + splitArr[0] + "=" + splitArr[1];
                }

            }
        }
    }

    /**
     * Created by Amila on 25/08/2018.
     * This is to add single or multiple query string to API request URL
     * @param requestFullPath is full path that need to pass for request
     * @param userName is Use name to send use for basic authentication
     * @param password is Password to send use for basic authentication
     */
    public static Response ReadRestApiData() {
        System.out.println("Start reading data...");
        RequestSpecification request = RestAssured.given();
        Response response = request.get(GlobalSetup.endPoint);
        System.out.println("End data reading...");
        return response;
    }
}