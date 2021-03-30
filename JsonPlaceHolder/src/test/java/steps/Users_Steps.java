package steps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jpmorgan.ScenarioContext;
import pojo_Users.Address;
import pojo_Users.Company;
import pojo_Users.Geo;
import pojo_Users.Payload;


public class Users_Steps {
Scenario scn;
Response response;
RequestSpecification _REQUEST_SPEC;
String actual;
Geo geo;
Company company;
Address address;
Payload payload;
ScenarioContext _CNXT;	

public Users_Steps(ScenarioContext cnxt) {
	this._CNXT=cnxt;
}

@Before
public void BeforeScn(Scenario s) {
	this.scn=s;
}

@After
public void AfterScn(Scenario s) {
	scn.log("Scenario is completed and "+ "Scenario Failed-"+scn.isFailed());
}
	
@Given("Base URL")
public void base_url() throws IOException {
    // Write code here that turns the phrase above into concrete actions
	Properties 	prop=new Properties();
	FileInputStream in =new FileInputStream("src/test/resources/config.properties");
	prop.load(in);
	_REQUEST_SPEC=RestAssured.given().baseUri(prop.getProperty("baseURL"));
	_CNXT.set_REQUEST_SPEC(_REQUEST_SPEC);
}

@When("I hit User endpoint {string}")
public void i_hit_user_endpoint(String endpoint) {
    // Write code here that turns the phrase above into concrete actions
		response=_CNXT.get_REQUEST_SPEC().when().get(endpoint);
		_CNXT.setResponse(response);
}
@Then("API should return Http code as {int}")
public void API_should_return_http_code_as(Integer code) {
    // Write code here that turns the phrase above into concrete actions
	_CNXT.getResponse().then().assertThat().statusCode(code);
		
}

@When("I search user endpoint with id as {string}")
public void i_search_user_endpoint_with_id_as(String endpoint) {
    // Write code here that turns the phrase above into concrete actions
		response=_CNXT.get_REQUEST_SPEC().when().get(endpoint);
		_CNXT.setResponse(response);
}


@When("^I search user endpoint with name as \"([^\\\\\\\"]*)\"$")
public void i_search_user_endpoint_with_name_as(String param) {
    // Write code here that turns the phrase above into concrete actions
	response=_CNXT.get_REQUEST_SPEC().when().queryParam("name", param).get("/users");
	_CNXT.setResponse(response);
	
}

@Then("Validate id as displayed as {string}")
public void validate_id_as_displayed_as(String id) {
    
	actual =_CNXT.getResponse().jsonPath().get("id").toString();
	Assert.assertEquals(id, actual);
}

@Then("Validate name is not null")
public void validate_name_is_not_null() {
    
	actual=_CNXT.getResponse().jsonPath().get("name");
	Assert.assertNotNull(actual);
}

@Then("Validate name as {string}")
public void validate_name_as(String string) {
    // Write code here that turns the phrase above into concrete actions
    
	_CNXT.getResponse().jsonPath().getList("name").contains(string);
}

@When("I provide user details for payload")
public void i_provide_user_details_for_payload(DataTable dataTable) throws JsonProcessingException {
	List<String> data=dataTable.asList();
	
	String name=data.get(0);
	String username=data.get(1);
	String email=data.get(2);
	String phone=data.get(9);
	String website=data.get(10);
	
	String street=data.get(3);
	String suite=data.get(4);
	String city=data.get(5);
	String zipcode=data.get(6);
	
	String lat=data.get(7);
	String lng=data.get(8);
	String bs=data.get(13);
	String CatchPhrase=data.get(12);
	String companyName=data.get(11);
	
	Company company=new Company(companyName,CatchPhrase,bs);
	geo=new Geo(lat,lng);
	Address address=new Address(street,suite,city,zipcode,geo);
	
	payload =new Payload(name,username,email,phone,website,company,address);
	
	response=_CNXT.get_REQUEST_SPEC().contentType(ContentType.JSON).when().body(payload).post("/users");
	_CNXT.setResponse(response);
		/*
		 * System.out.println(response.asPrettyString()); ObjectMapper obj =new
		 * ObjectMapper(); String
		 * mydata=obj.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
		 * System.out.println(mydata);
		 */
}



@Then("^Validate \"([^\\\"]*)\" should be \"([^\\\"]*)\"$")
public void validate_field_should_be_value(String f,String val) {
 
	Assert.assertEquals(val,_CNXT.getResponse().jsonPath().get(f));
	
}

}
