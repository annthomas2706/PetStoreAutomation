package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		logger=LogManager.getLogger(this.getClass());
		
		faker=new Faker();
		userPayload=new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
	}
	
	
	@Test(priority=1)
	
	public void testPostUser() {
		
		logger.info("Creating User");
		Response response=UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("User is created");
	}

	
	@Test(priority=2)
	
	public void testGetUser() {
		logger.info("Reading User");
		System.out.println(this.userPayload.getUsername());
		Response response=UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("User Info is Displayed");
	}

	@Test(priority=3)

	public void testUpdateUser() {
		
		//update data using same payload
		logger.info("Updating User");
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
	
		Response response=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("Updated User");
	}
	
	@Test(priority=4)

	public void testDeleteUser() {
	
		logger.info("Deleting User");
		Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		logger.info("Deleted User");}



}
