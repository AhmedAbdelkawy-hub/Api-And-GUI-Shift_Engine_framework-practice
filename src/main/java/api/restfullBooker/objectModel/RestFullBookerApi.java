package api.restfullBooker.objectModel;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestFullBookerApi {
	
  // Commen locator and service name and status code 
	private RestActions apiObject;
	public static final String BaseUrl = "https://restful-booker.herokuapp.com/";
	public static final int Success = 200;
	public static final int SuccessDelete = 201;
	private String AuthServiceName = "auth";
	//constructor
	public RestFullBookerApi(RestActions apiObject) {
		this.apiObject = apiObject;
	}
	
	// login method 
	@SuppressWarnings("unchecked")
	public void Login(String Username, String Password) {
		
		//Authentication
		  JSONObject authentication = new JSONObject();
		  authentication.put("username",Username );
		  authentication.put("password",Password );
		  
		  Response createToken = apiObject.buildNewRequest(AuthServiceName, RequestType.POST)
				  .setContentType(ContentType.JSON)
				  .setRequestBody(apiObject)
				  .setRequestBody(authentication)
				  .performRequest();
		  String Token = RestActions.getResponseJSONValue(createToken, "token");
		  apiObject.addHeaderVariable("Cookie", "token="+Token);
	}
	
}
