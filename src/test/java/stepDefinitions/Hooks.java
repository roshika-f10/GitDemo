package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		stepDefinition m = new stepDefinition();
		if(stepDefinition.place_id==null) {
		m.add_Place_Payload_with("Anne", "Italian", "sunshine coast");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_Id_created_maps_to_using("Anne", "getPlaceAPI");
		}
		
	}

}


