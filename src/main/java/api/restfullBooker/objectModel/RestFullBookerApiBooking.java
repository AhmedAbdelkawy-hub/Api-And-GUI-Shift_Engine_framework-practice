package api.restfullBooker.objectModel;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;
import com.shaft.validation.Assertions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestFullBookerApiBooking {

	// Commen locator and service name and status code
	private RestActions apiObject;
	private String BookingServiceName = "booking";

	// constructor
	public RestFullBookerApiBooking(RestActions apiObject) {
		this.apiObject = apiObject;
	}

	// get Booking IDs method
	public Response GetBookingIds() {

		return apiObject.buildNewRequest(BookingServiceName, RequestType.GET).performRequest();
	}

	// get Booking IDS ovverrid with paramters

	public Response GetBookingIds(String firstName, String lastName) {

		return apiObject.buildNewRequest(BookingServiceName+"/", RequestType.GET)
				.setUrlArguments("firstname" + firstName + "&lastname" + lastName).performRequest();
	}

	// get Booking method

	public Response GetBooking(String bookingId) {

		return apiObject.buildNewRequest(BookingServiceName+"/" + bookingId, RequestType.GET).performRequest();
	}

	// Create New booking

	public Response CreateBooking(String firstName, String lasttName, int totalPrice, boolean depositPaid,
			String Checkin, String Checkout, String additionalNeeds) {

		return apiObject.buildNewRequest(BookingServiceName, RequestType.POST).setContentType(ContentType.JSON).setRequestBody(
				CreateBookingBody(firstName, lasttName, totalPrice, depositPaid, Checkin, Checkout, additionalNeeds))
				.performRequest();

	}

	///////////////////////////////////////
	// create JSON body
	@SuppressWarnings({ "unused", "unchecked" })

	private JSONObject CreateBookingBody(String firstName, String lasttName, int totalPrice, boolean depositPaid,
			String Checkin, String Checkout, String additionalNeeds) {

		JSONObject CreateBookingBody = new JSONObject();
		JSONObject bookingdates = new JSONObject();
		CreateBookingBody.put("firstname", firstName);
		CreateBookingBody.put("lastname", lasttName);
		CreateBookingBody.put("totalprice", totalPrice);
		CreateBookingBody.put("depositpaid", depositPaid);

		bookingdates.put("checkin", Checkin);
		bookingdates.put("checkout", Checkout);
		CreateBookingBody.put("bookingdates", bookingdates);
		CreateBookingBody.put("additionalneeds", additionalNeeds);

		return CreateBookingBody;
	}
	
	
	// Delete booking

	public Response DeleteBooking(String bookingId) {

		return  apiObject.buildNewRequest(BookingServiceName+"/" + bookingId, RequestType.DELETE)
				.setTargetStatusCode(RestFullBookerApi.SuccessDelete)
				.setContentType(ContentType.JSON)
				.performRequest();
	}



}
