package api;

import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	public Faker faker;
	
	@BeforeClass
	protected void setup() {
		RestAssured.baseURI = "https://www.ynet.co.il/";
		faker = new Faker();
//		RestAssured.proxy("localhost", 8888);
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.addHeader("Content-Type", "application/x-www-form-urlencoded").build();
		RestAssured.requestSpecification = requestSpecification;

	}
}
