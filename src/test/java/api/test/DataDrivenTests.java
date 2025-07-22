package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
@Test(priority=1,dataProvider="AllUserData",dataProviderClass=DataProviders.class)
	
	public void testPostUser(String userID,String userName,String firstName,String lastName,String Email, String Password, String Phone) {
		
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
	
		Response response=UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@Test(priority=2,dependsOnMethods = "testPostUser",dataProvider="UserNamesOnly",dataProviderClass=DataProviders.class)

	public void testDeleteUser(String userName) {

		Response response=UserEndpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
}



}
