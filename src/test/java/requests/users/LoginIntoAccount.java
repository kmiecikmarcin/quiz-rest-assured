package requests.users;

import com.google.gson.Gson;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginIntoAccount {
    public static Gson gson = new Gson();

    public static Response loginIntoAccount(RequestSpecification requestSpec, RestAssuredConfig config, String loginUrl,
                                            User userdata) {
        String requestData = gson.toJson(userdata);

        Response response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestData)
                .when()
                .post(loginUrl);

        return response;
    }
    public static Response loginIntoAccountWithIncorrectData(RequestSpecification requestSpec, RestAssuredConfig config,
                                                             String loginUrl, String email, String password,
                                                             String kindOfTest) {
        Map<String, Object> requestData = new HashMap<>();
        Response response = null;

        if(kindOfTest == "withoutRegistration") {
            requestData.put("userEmail", email);
            requestData.put("userPassword", password);
            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(loginUrl);
        }
        if(kindOfTest == "withoutEmail") {
            requestData.put("userPassword", password);
            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(loginUrl);
        }
        if(kindOfTest == "withoutPassword") {
            requestData.put("userEmail", email);
            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(loginUrl);
        }
        if(kindOfTest == "emptyEmail") {
            requestData.put("userEmail", "");
            requestData.put("userPassword", password);
            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(loginUrl);
        }
        if(kindOfTest == "emptyPassword") {
                requestData.put("userEmail", email);
                requestData.put("userPassword", "");
                response = given()
                        .spec(requestSpec)
                        .config(config)
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(requestData)
                        .when()
                        .post(loginUrl);
        }
        return response;
    }
}
