package steps;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jpmorgan.ScenarioContext;
import pojo_Comments.Payload;


public class Comments_Steps {

	Response response;
	ScenarioContext _CNXT;
	String actual;
	Payload payload;
	
	public Comments_Steps(ScenarioContext cnxt) {
		this._CNXT=cnxt;
	}
	
	@When("I hit Comments endpoint {string}")
	public void i_hit_comments_endpoint(String endpoint) {
	    // Write code here that turns the phrase above into concrete actions
		response=_CNXT.get_REQUEST_SPEC().when().get(endpoint);
		_CNXT.setResponse(response);
	}
	

	@When("I search comments endpoint with id as {string}")
	public void i_search_comments_endpoint_with_id_as(String id) {
		response=_CNXT.get_REQUEST_SPEC().when().get("/comments/"+id);
		_CNXT.setResponse(response);
	}


	@When("I search comments endpoint with postid as {string}")
	public void i_search_comments_endpoint_with_postid_as(String postid) {
		response=_CNXT.get_REQUEST_SPEC().when().queryParam("postId", postid).get("/comments");
		_CNXT.setResponse(response);
	}


	@When("I search comments endpoint with userid as {string}")
	public void i_search_comments_endpoint_with_userid_as(String postid) {
	    // Write code here that turns the phrase above into concrete actions
		response=_CNXT.get_REQUEST_SPEC().when().queryParam("postId", postid).get("/posts");
		_CNXT.setResponse(response);
	}
	
	@When("I provide details as postid {string} And title {string} And email {string} body {string}")
	public void i_provide_details_as_postid_and_title_and_email_body(String postid, String name, String email, String body) {
		payload =new Payload(Long.parseLong(postid),name,email,body);
		response=_CNXT.get_REQUEST_SPEC().contentType(ContentType.JSON).when().body(payload).post("/comments");
		_CNXT.setResponse(response);
	}
	
	@Then("Validate postid displayed as {string}")
	public void validate_postid_displayed_as(String postid) {
		 actual=_CNXT.getResponse().jsonPath().get("postId").toString();
		    Assert.assertEquals(postid, actual);
	}

}
	