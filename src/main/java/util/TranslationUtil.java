package util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.TranslateRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import config.Config;

public class TranslationUtil {
    public static String translateProvidedText(String text) throws JsonProcessingException {
        String apiKey = Config.getProperty("rapid_api_key");
        String host = Config.getProperty("rapid_host");
        String url = "https://google-translate113.p.rapidapi.com/api/v1/translator/text";

        TranslateRequest request = new TranslateRequest();
        request.setFrom("auto");
        request.setTo("en");
        request.setText("El empresario más rico del mundo...");

        // Serialize the request body to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);

        Response response = RestAssured.given()
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", host)
                .contentType(ContentType.JSON)
                .body(body)
                .post(url);
        return response.jsonPath()
                .getString("trans");
    }
}

