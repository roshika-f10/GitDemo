package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
public AddPlace add_Place_Payload(String name, String language, String address) {
	    
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);		
		return p;
		
}

public String deletePlcePayload(String placeId) {
	return "{\r\n" + 
			"    \"place_id\":\""+placeId+"\"\r\n" + 
			"}\r\n" + 
			"";
}

}
