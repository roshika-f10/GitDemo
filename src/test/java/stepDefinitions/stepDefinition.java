package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils{
	
	RequestSpecification reqSpec;
	ResponseSpecification res;
	RequestSpecification req;
	Response response;
	static String place_id;
	JsonPath js;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {	
			/* res = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON)
			.build();*/
			  
		 reqSpec = given().spec(requestSpecification()).body(data.add_Place_Payload(name,language,address));
	}


	

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		res = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		if(method.equalsIgnoreCase("POST")) 
		{
		 response = reqSpec.when().post(resourceAPI.getResource());
		 }
		 else if (method.equalsIgnoreCase("GET"))
			 response = reqSpec.when().get(resourceAPI.getResource());
	}
	
	
	
	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expected) {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals(getJsonPath(response,keyValue),expected);
		
	}






	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   place_id = getJsonPath(response,"place_id");
	   reqSpec=given().spec(requestSpecification()).queryParam("place_id",place_id);
	   user_calls_with_http_request(resource,"GET");
	   String actualName = getJsonPath(response,"name");
	   assertEquals(actualName,expectedName);
	   
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		reqSpec = given().spec(requestSpecification()).body(data.deletePlcePayload(place_id));
	}

}
