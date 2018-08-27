package com.assurity.commons;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import io.restassured.response.Response;

public class GlobalSetup {
	
	//Globle parameters
	public static String endPoint;
	public Properties propertyData;
	public static Response response;
	
	//Local parameters
	/**
     * Created by Amila on 26/08/2018.
     * This is to read the categories data property file
     */
	public GlobalSetup(){
		
		propertyData = new Properties();
		
		try{
			InputStream propertyPath = new FileInputStream("src/test/resources/properties/categoriesData.properties");
			propertyData.load(propertyPath);
		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		
	}

}
