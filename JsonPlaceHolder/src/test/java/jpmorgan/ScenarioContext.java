package jpmorgan;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ScenarioContext {
	private RequestSpecification _REQUEST_SPEC;
	private Response response;
	private Scenario scn;
	
	public RequestSpecification get_REQUEST_SPEC() {
		return _REQUEST_SPEC;
	}
	public void set_REQUEST_SPEC(RequestSpecification _REQUEST_SPEC) {
		this._REQUEST_SPEC = _REQUEST_SPEC;
	}
	
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public Scenario getScn() {
		return scn;
	}
	public void setScn(Scenario scn) {
		this.scn = scn;
	}
	
}
