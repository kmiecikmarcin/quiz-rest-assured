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

public class CreateNewAccount {
    public static Gson gson = new Gson();

    public static Response createNewAccount(RequestSpecification requestSpec, RestAssuredConfig config, String registerUrl, User userdata) {
        String requestData = gson.toJson(userdata);

        var response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestData)
                .when()
                .post(registerUrl);

        return response;
    }
    public static Response createNewAccountWithoutUserEmail(RequestSpecification requestSpec, RestAssuredConfig config, String registerUrl, User userData) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("userPassword", userData.userPassword);
        requestData.put("confirmPassword", userData.confirmPassword);
        requestData.put("userGender", userData.userGender);
        requestData.put("userVerification", userData.userVerification);

        Response response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestData)
                .when()
                .post(registerUrl);

        return response;
    }
    public static Response createNewAccountWithoutUserPassword(RequestSpecification requestSpec, RestAssuredConfig config, String registerUrl, User userData) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("userEmail", userData.userEmail);
        requestData.put("confirmPassword", userData.confirmPassword);
        requestData.put("userGender", userData.userGender);
        requestData.put("userVerification", userData.userVerification);

        Response response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestData)
                .when()
                .post(registerUrl);

        return response;
    }
    public static Response createNewAccountWithoutConfirmPassword(RequestSpecification requestSpec, RestAssuredConfig config, String registerUrl, User userData) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("userEmail", userData.userEmail);
        requestData.put("userPassword", userData.userPassword);
        requestData.put("userGender", userData.userGender);
        requestData.put("userVerification", userData.userVerification);

        Response response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestData)
                .when()
                .post(registerUrl);

        return response;
    }
    public static Response createNewAccountWithoutUserGender(RequestSpecification requestSpec, RestAssuredConfig config, String registerUrl, User userData) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("userEmail", userData.userEmail);
        requestData.put("userPassword", userData.userPassword);
        requestData.put("confirmPassword", userData.confirmPassword);
        requestData.put("userVerification", userData.userVerification);

        Response response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestData)
                .when()
                .post(registerUrl);

        return response;
    }
    public static Response createNewAccountWithoutUserVerification(RequestSpecification requestSpec, RestAssuredConfig config, String registerUrl, User userData) {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("userEmail", userData.userEmail);
        requestData.put("userPassword", userData.userPassword);
        requestData.put("confirmPassword", userData.confirmPassword);
        requestData.put("userGender", userData.userGender);

        Response response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestData)
                .when()
                .post(registerUrl);

        return response;
    }
}
