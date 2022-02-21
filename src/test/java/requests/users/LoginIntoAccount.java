package requests.users;

import com.google.gson.Gson;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import static io.restassured.RestAssured.given;

public class LoginIntoAccount {
    public static Gson gson = new Gson();

    public static Response loginIntoAccount(RequestSpecification requestSpec, RestAssuredConfig config, String loginUrl, User userdata) {
        String requestData = gson.toJson(userdata);

        var response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestData)
                .when()
                .post(loginUrl);

        return response;
    }
}
