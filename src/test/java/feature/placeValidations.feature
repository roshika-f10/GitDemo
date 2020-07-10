Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
Examples:
	|name		|language		|address		     |
	|Black House|Spanish		|Philiphs Street	 |
	#|White House|English-AU		|King William Street |
	
	
	#To delete a added place. Here we take the place id from the above test case.
@DeletePlace @Regression
Scenario: Verify if DeletePlace functionality is working
	Given Delete place payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	
