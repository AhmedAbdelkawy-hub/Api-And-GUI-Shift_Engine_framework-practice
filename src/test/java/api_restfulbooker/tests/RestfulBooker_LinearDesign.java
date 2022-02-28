package api_restfulbooker.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Assertions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestfulBooker_LinearDesign {
	
	private RestActions apiObject;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void beforeClass() {
		
		apiObject = DriverFactory.getAPIDriver("https://restful-booker.herokuapp.com/");
		
		//Authentication
		  JSONObject authentication = new JSONObject();
		  authentication.put("username","admin" );
		  authentication.put("password","password123" );
		  
		  Response createToken = apiObject.buildNewRequest("auth", RequestType.POST)
				  .setContentType(ContentType.JSON)
				  .setRequestBody(apiObject)
				  .setRequestBody(authentication)
				  .performRequest();
		  String Token = RestActions.getResponseJSONValue(createToken, "token");
		  apiObject.addHeaderVariable("Cookie", "token="+Token);
	}
	
  @Test
  public void GetBookingIds() {
	  
	  apiObject.buildNewRequest("booking", RequestType.GET ).performRequest();
  }
  
  @Test
  public void GetBooking() {
	  
	  apiObject.buildNewRequest("booking/" +"5", RequestType.GET ).performRequest();
  }
  
  
@SuppressWarnings({ "unchecked", "deprecation", "removal" })
@Test
  public void CreateBooking() {
	  
	  //Json Objects
	  JSONObject CreateBookingBody = new JSONObject();
	  CreateBookingBody.put("firstname","Ahmed" );
	  CreateBookingBody.put("lastname","abdelkawy" );
	  CreateBookingBody.put("totalprice",500);
	  CreateBookingBody.put("depositpaid",true);
   	  JSONObject bookingdates = new JSONObject();
   	  bookingdates.put("checkin", "2020-01-01");
   	  bookingdates.put("checkout", "2022-01-01");
      CreateBookingBody.put("bookingdates",bookingdates);
      CreateBookingBody.put("additionalneeds","diner");
      
      //Create New booking
   	   Response CreateBookingRes = apiObject.buildNewRequest("booking", RequestType.POST)
	 .setContentType(ContentType.JSON)
	 .setRequestBody(CreateBookingBody)
     .performRequest();
   	   
   	   //Get the  created booking value
   	  String bookingid = RestActions.getResponseJSONValue(CreateBookingRes, "bookingid");   
   	  
   	  Response getBookinRes = apiObject.buildNewRequest("booking/" +bookingid, RequestType.GET ).performRequest();
   	  
   	  String FirstName = RestActions.getResponseJSONValue(getBookinRes, "firstname");
 	  String lastName = RestActions.getResponseJSONValue(getBookinRes, "lastname");
 	  String checkindate = RestActions.getResponseJSONValue(getBookinRes, "bookingdates.checkin");
 	  String checkoutdate = RestActions.getResponseJSONValue(getBookinRes, "bookingdates.checkout");
 	  String totalprice = RestActions.getResponseJSONValue(getBookinRes, "totalprice");
 	  
   	Assertions.assertEquals("Ahmed", FirstName);
   	Assertions.assertEquals("abdelkawy", lastName);
   	Assertions.assertEquals("2020-01-01", checkindate);
   	Assertions.assertEquals("2022-01-01", checkoutdate);
	Assertions.assertEquals("500" , totalprice);
	Assertions.assertJSONFileContent(getBookinRes, System.getProperty("jsonFolderPath")+"restFullBooker/Booking.json");
	
  }
  

@Test (dependsOnMethods = {"CreateBooking"})
  public void deleteBooking() {
	  
	  
	  
	    
   	  
   	  Response getBookingId = apiObject.buildNewRequest("booking/", RequestType.GET )
   			  .setUrlArguments("firstname=Ahmed&lastname=abdelkawy")
   			  .performRequest();
   	  
   	  String BookingId = RestActions.getResponseJSONValue(getBookingId, "bookingid[0]");
	  
	  //DeleteRequest
	  
	  Response deleteBooking =  apiObject.buildNewRequest("booking/" +BookingId, RequestType.DELETE )
	  .setTargetStatusCode(201)
	  .setContentType(ContentType.JSON)
	  .performRequest();
	  
	  String deleteBookingBody = RestActions.getResponseBody(deleteBooking);
	  Assertions.assertEquals("Created", deleteBookingBody);
  }
}
