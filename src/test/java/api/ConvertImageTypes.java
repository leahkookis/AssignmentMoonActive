package api;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ConvertImageTypes extends BaseTest {

	private Response convertImage(String language, Boolean isOverlayRequired, String url, Boolean iscreatesearchablepdf, Boolean issearchablepdfhidetextlayer, String filetype) {
		Response response = given().log().all().header("apikey", "K81368153988957")
				.body(String.format(
						"{\r\n\"language\": \"%s\",\r\n\"isOverlayRequired\": \"%s\",\r\n\"url\":\"%s\",\r\n\"iscreatesearchablepdf\":\"%s\",\r\n\"issearchablepdfhidetextlayer\":\"%s\",\r\n\"filetype\":\"%s\"}",
						language, isOverlayRequired, url, iscreatesearchablepdf, issearchablepdfhidetextlayer, filetype))
				.when().post("https://api.ocr.space/parse/image").then().statusCode(200).extract().response();
				return response;
	}
	
	@Test(description = "Convert image type jpg than validate image text")
	public void ConvertImageTypeJPG(){
		{
			Response response = convertImage("eng", true, "https://h7z8m6j8.stackpathcdn.com/wp-content/uploads/2021/01/43675ImageFile5.jpg", false, false, "jpg");
			JsonPath jsonPathEvaluator = response.jsonPath();
			Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals("PLEASE\\r\\nHANDLE WITH CARE\\r\\nFRAGILE\\r\\n", jsonPathEvaluator.get(".*.ParsedText"));
		}
	}
	
	@Test(description = "Convert image type gif than validate image text")
	public void ConvertImageTypeGIF(){
		{
			Response response = convertImage("eng", true, "https://images.ctfassets.net/b4k16c7lw5ut/blog-simpson-gif/ac0327d74dfd26727dd31afa5a1f2405/simpson-gif", false, false, "jpg");
			JsonPath jsonPathEvaluator = response.jsonPath();
			Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals("to start, press any key", jsonPathEvaluator.get(".*.ParsedText"));
		}
	}
	
	@Test(description = "Convert image type png than validate image text")
	public void ConvertImageTypePNG(){
		{
			Response response = convertImage("eng", true, "https://upload.wikimedia.org/wikipedia/commons/d/d2/Chlo%C3%A9_%28logo%29.png", false, false, "jpg");
			JsonPath jsonPathEvaluator = response.jsonPath();
			Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals("chloe", jsonPathEvaluator.get(".*.ParsedText"));
		}
	}
	

	
	


}
