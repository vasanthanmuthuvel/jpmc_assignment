package steps;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jpmorgan.ScenarioContext;
import pojo_Posts.Payload;

public class Posts_Steps {

	Response response;
	ScenarioContext _CNXT;
	String actual;
	Payload payload;
	
	public Posts_Steps(ScenarioContext cnxt) {
		this._CNXT=cnxt;
	}
	@When("I hit Posts endpoint {string}")
	public void i_hit_posts_endpoint(String endpoint) {
	    // Write code here that turns the phrase above into concrete actions
		response=_CNXT.get_REQUEST_SPEC().when().get(endpoint);
		_CNXT.setResponse(response);
	}
	
	@When("I search post endpoint with userid as {string}")
	public void i_search_post_endpoint_with_userid_as(String userid) {
	    // Write code here that turns the phrase above into concrete actions
		response=_CNXT.get_REQUEST_SPEC().when().queryParam("userId", userid).get("/posts");
		_CNXT.setResponse(response);
	}
	
	@When("I search post endpoint with postid as {string}")
	public void i_search_post_endpoint_with_postid_as(String postid) {
		response=_CNXT.get_REQUEST_SPEC().when().get("/posts/"+postid+"/comments");
		_CNXT.setResponse(response);
	}
	@Then("Validate userid as displayed as {string}")
	public void validate_userid_as_displayed_as(String userid) {
	    actual=_CNXT.getResponse().body().jsonPath().getList("userId").get(0).toString();
	    Assert.assertEquals(userid, actual);
	}
	
	@Then("Validate postid as displayed as {string}")
	public void validate_postid_as_displayed_as(String postid) {
		actual=_CNXT.getResponse().body().jsonPath().getList("postId").get(0).toString();
	    Assert.assertEquals(postid, actual);
	}
	
	@Then("Validate userid displayed as {string}")
	public void validate_userid_displayed_as(String userid) {
	    actual=_CNXT.getResponse().jsonPath().get("userId").toString();
	    Assert.assertEquals(userid, actual);
	}
	

	@When("I provide details as userid {string} And title {string} And body {string}")
	public void i_provide_details_as_userid_and_title_and_body(String userid, String title, String body) {
		payload =new Payload(Long.parseLong(userid),title,body);
		response=_CNXT.get_REQUEST_SPEC().contentType(ContentType.JSON).when().body(payload).post("/posts");
		_CNXT.setResponse(response);
	}
	
	@When("I provide empty values")
	public void i_provide_empty_values() {
		String payload="{\r\n" + 
				"    \"userId\":" + 
				"    \"title\": \"\",\r\n" + 
				"    \"body\": \"\"\r\n" + 
				"  }";
		response=_CNXT.get_REQUEST_SPEC().contentType(ContentType.JSON).when().body(payload).post("/posts");
		_CNXT.setResponse(response);
	}

}
