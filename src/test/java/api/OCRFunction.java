package api;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OCRFunction extends BaseTest {

	public Response convertImageWithOption(String language, Boolean isOverlayRequired, String url, Boolean iscreatesearchablepdf, Boolean issearchablepdfhidetextlayer, String filetype) {
		Response response = given().log().all().header("apikey", "K81368153988957")
				.body(String.format(
						"{\r\n\"language\": \"%s\",\r\n\"isOverlayRequired\": \"%s\",\r\n\"url\":\"%s\",\r\n\"iscreatesearchablepdf\":\"%s\",\r\n\"issearchablepdfhidetextlayer\":\"%s\",\r\n\"filetype\":\"%s\"}",
						language, isOverlayRequired, url, iscreatesearchablepdf, issearchablepdfhidetextlayer, filetype))
				.when().post("https://api.ocr.space/parse/image").then().statusCode(200).extract().response();
				return response;
	}
	
	public Response convertImageWithType(String url, String filetype) {
		Response response = given().log().all().header("apikey", "K81368153988957")
				.body(String.format(
						"{\r\n\"language\": \"eng\",\r\n\"isOverlayRequired\": \"false\",\r\n\"url\":\"%s\",\r\n\"iscreatesearchablepdf\":\"false\",\r\n\"issearchablepdfhidetextlayer\":\"false\",\r\n\"filetype\":\"%s\"}",
						 url, filetype))
				.when().post("https://api.ocr.space/parse/image").then().statusCode(200).extract().response();
				return response;
	}
	

	


}
