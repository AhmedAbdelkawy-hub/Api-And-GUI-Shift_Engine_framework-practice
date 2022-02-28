package api_restfulbooker.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Assertions;

import api.restfullBooker.objectModel.RestFullBookerApi;
import api.restfullBooker.objectModel.RestFullBookerApiBooking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestFullBookerTests {

	private RestActions apiObject;
	private RestFullBookerApi restFullBookerApi;
	private RestFullBookerApiBooking restFullBookerApiBooking;

	@BeforeClass
	public void beforeClass() {
		apiObject = DriverFactory.getAPIDriver(RestFullBookerApi.BaseUrl);
		restFullBookerApi = new RestFullBookerApi(apiObject);
		restFullBookerApi.Login("admin", "password123");
		restFullBookerApiBooking = new RestFullBookerApiBooking(apiObject);

	}

	// User can get booking id
	@Test
	public void UsercanGetBokkingIds() {
		restFullBookerApiBooking.GetBookingIds();

	}

//User can get booking 
	@Test
	public void UsercanGetBokking() {
		restFullBookerApiBooking.GetBooking("1");

	}

//User can Create booking 
	@Test
	public void UsercanCreatetBokking() {
		Response CreateBookingRes = restFullBookerApiBooking.CreateBooking("Ahmed", "abdelkawy", 500, true,
				"2020-01-01", "2022-01-01", "Checkin");

		String bookingid = RestActions.getResponseJSONValue(CreateBookingRes, "bookingid");

		// Get the booking values
		Response getBookinRes = restFullBookerApiBooking.GetBooking(bookingid);

		String FirstName = RestActions.getResponseJSONValue(getBookinRes, "firstname");
		String lastName = RestActions.getResponseJSONValue(getBookinRes, "lastname");
		String checkindate = RestActions.getResponseJSONValue(getBookinRes, "bookingdates.checkin");
		String checkoutdate = RestActions.getResponseJSONValue(getBookinRes, "bookingdates.checkout");
		String totalprice = RestActions.getResponseJSONValue(getBookinRes, "totalprice");

		// Asseration
		
		Assertions.assertEquals("Ahmed", FirstName);
		Assertions.assertEquals("abdelkawy", lastName);
		Assertions.assertEquals("2020-01-01", checkindate);
		Assertions.assertEquals("2022-01-01", checkoutdate);
		Assertions.assertEquals("500", totalprice);

	}
	
	// Delete booking

	@Test(dependsOnMethods = { "UsercanCreatetBokking" })
	public void UserCandeleteBooking() {

		Response getBookingId = restFullBookerApiBooking.GetBookingIds("Ahmed", "abdelkawy");

		String BookingId = RestActions.getResponseJSONValue(getBookingId, "bookingid[0]");

		// DeleteRequest

		Response deleteBooking = restFullBookerApiBooking.DeleteBooking(BookingId);

		String deleteBookingBody = RestActions.getResponseBody(deleteBooking);
		Assertions.assertEquals("Created", deleteBookingBody);
	}

}
