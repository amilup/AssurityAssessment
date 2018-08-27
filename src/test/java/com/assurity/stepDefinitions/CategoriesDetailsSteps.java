package com.assurity.stepDefinitions;

import org.junit.Assert;

import com.assurity.commons.GlobalSetup;
import com.assurity.commons.RestAPIUtility;
import com.assurity.stepDefinitionsHelper.CategoriesDetailsStepsHelper;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CategoriesDetailsSteps {
	
	@Given("^A user access to Categories API$")
	public void a_user_access_to_Categories_API() throws Throwable {
		RestAPIUtility.CreateBaseUrl();
        GlobalSetup.endPoint = GlobalSetup.endPoint + "/Categories";
	}

	@Given("^I have added (\\d+)$")
	public void i_have_added(String categoryid) throws Throwable {
		RestAPIUtility.AddParametresToUrl(categoryid);
	}

	@Given("^I have added (.*) to url$")
	public void i_have_added_to_url(String parameter) throws Throwable {
		RestAPIUtility.AddParametresToUrl(parameter);
	}
	
	@Given("^I call to Categories Details API$")
	public void i_call_to_Categories_Details_API() throws Throwable {
		String parameter = "Details.json";
		RestAPIUtility.AddParametresToUrl(parameter);
	}

	@Given("^I have add query String (.*)$")
	public void i_have_add_query_String(String queryStringValue) throws Throwable {
		String[] queryArray = {"catalogue:" + queryStringValue};
		RestAPIUtility.AddingQureryStringToEndPoint(queryArray, GlobalSetup.endPoint);
	}

	@When("^I make a GET request$")
	public void i_make_a_GET_request() throws Throwable {
		GlobalSetup.response = RestAPIUtility.ReadRestApiData();
	}

	@Then("^I verify the status as OK$")
	public void i_verify_the_status_as_OK() throws Throwable {
		Assert.assertEquals(200, GlobalSetup.response.statusCode());
	}

	@Then("^I verify (.*) in the API response$")
	public void i_verify_in_the_API_response(String categoryName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    Assert.assertEquals(categoryName, GlobalSetup.response.path("Name"));
	}

	@Then("^I verify (.*) status$")
	public void i_verify_status(String canRelist) throws Throwable {
		Assert.assertEquals(canRelist, GlobalSetup.response.path("CanRelist").toString());
	}

	@Then("^I verify Promotions element with (.*) has a Description that contains the (.*)$")
	public void i_verify_Promotions_element_with_has_a_Description_that_contains_the(String name, String text) throws Throwable {
		String description = CategoriesDetailsStepsHelper.ReadArray(name);
		Assert.assertTrue(description.contains(text));
	}

}
