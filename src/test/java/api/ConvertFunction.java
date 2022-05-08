package api;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ConvertFunction extends BaseTest {

	private Response convertImage(String language, Boolean isOverlayRequired, String url, Boolean iscreatesearchablepdf, Boolean issearchablepdfhidetextlayer) {
		Response response = given().log().all().header("apikey", "K81368153988957")
				.body(String.format(
						"{\r\n\"language\": \"%s\",\r\n\"isOverlayRequired\": %b,\r\n\"url\":\"%s\",\r\n\"iscreatesearchablepdf\":%b,\r\n\"issearchablepdfhidetextlayer\":%b}",
						language, isOverlayRequired, url, iscreatesearchablepdf, issearchablepdfhidetextlayer))
				.when().log().all().post("https://api.ocr.space/parse/image").then().statusCode(200).extract().response();
				return response;
	}
	
	
	
	@Test(description = "Set Ocr language and convert image to text")
	public void SetOcrLanguageAndConvertImageToText(){
		{
			Response response = convertImage("jpn", true, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Nihongo.svg/220px-Nihongo.svg.png", false, false);
			JsonPath jsonPathEvaluator = response.jsonPath();
			Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals("312", jsonPathEvaluator.get(".*.ParsedText"));
		}
	}
	
	
	
	


}
