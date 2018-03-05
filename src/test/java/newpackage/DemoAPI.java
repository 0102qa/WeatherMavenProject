package newpackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hamcrest.Matchers;

public class DemoAPI {
	
Long todayMidnight;
long time = System.currentTimeMillis()/1000; 

	public long getTodayMidnightTime(){
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String timeStamp= df.format(Calendar.getInstance().getTime());	
		System.out.println(timeStamp +" today date");
		System.out.println(time + " Epoch NOW");
		try {
			todayMidnight = new SimpleDateFormat("MM/dd/yyyy").parse(timeStamp).getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(todayMidnight + " Epoch Today MIDNIGHT");
		return todayMidnight;
	}	
	public String getApiAdress() {
		RestAssured.baseURI=Page.apiUrl;
		Response res=given().	
				when().
					get("/latlong/"+Page.city+"").
				then().
					assertThat().statusCode(200).and().
					contentType(ContentType.JSON).and().
					time(lessThan(5000L)).and().
					body("isEmpty()", Matchers.is(false)).
					extract().response();
					String responseString = res.asString();
					//System.out.println(responseString);
					
					JsonPath js = new JsonPath(responseString);
					String apiAdress= js.get("Address");
					//System.out.println(apiAdress);
					return apiAdress;
	}
	public String getTodayTemperature(){		
		RestAssured.baseURI=Page.apiUrl;
		Response res=given().
		       when().
		       		get("/weather/40.7127753/-74.0059728/"+getTodayMidnightTime()+"").
		       then().
		       		assertThat().statusCode(200).and().
		       		contentType(ContentType.JSON).and().
		       		time(lessThan(5000L)).and().
		       		body("isEmpty()", Matchers.is(false)).
		       		extract().response();
				   	String responseString = res.asString();
				   //System.out.println(responseString);
			   
				   	JsonPath js = new JsonPath(responseString);
				   	float apiTodayTemperature= js.get("[0].currently.apparentTemperature");
				   	//System.out.println(apiTodayTemperature);
				   	String apiTodayTemperatureSting = Float.toString(apiTodayTemperature);
				   	return apiTodayTemperatureSting;
	}
	public String getTodaySummary(){		
		RestAssured.baseURI=Page.apiUrl;
		Response res=given().
		       when().
		       		get("/weather/40.7127753/-74.0059728/"+getTodayMidnightTime()+"").
		       		then().assertThat().statusCode(200).and().
		       		contentType(ContentType.JSON).and().
		       		time(lessThan(5000L)).and().
		       		body("isEmpty()", Matchers.is(false)).
		       		extract().response();
					String responseString = res.asString();
					//System.out.println(responseString);
			   
					JsonPath js = new JsonPath(responseString);
					String apiTodaySummary= js.get("[0].currently.summary");
					//System.out.println(apiTodaySummary);
					return apiTodaySummary;
	}	
}


