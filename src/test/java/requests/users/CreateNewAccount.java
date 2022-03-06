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

    public static Response createNewAccount(RequestSpecification requestSpec, RestAssuredConfig config,
                                            String registerUrl, User userdata) {
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
    public static Response createNewAccountWithoutRequiredData(RequestSpecification requestSpec, RestAssuredConfig config,
                                                             String registerUrl, User userData, String kindOfTest) {
        Map<String, Object> requestData = new HashMap<>();
        Response response = null;

        if (kindOfTest == "withoutEmail") {
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if (kindOfTest == "withoutPassword") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if (kindOfTest == "withoutConfirmPassword") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if (kindOfTest == "withoutGender") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if (kindOfTest == "withoutVerification") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        return response;
    }
    public static Response createNewAccountWithEmptyData(RequestSpecification requestSpec, RestAssuredConfig config,
                                                         String registerUrl, User userData, String kindOfTest) {
        Map<String, Object> requestData = new HashMap<>();
        Response response = null;

        if(kindOfTest == "emptyEmail") {
            requestData.put("userEmail", "");
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "emptyPassword") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", "");
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "emptyConfirmPassword") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", "");
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "emptyGender") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", "");
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "falseVerification") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        return response;
    }
    public static Response createNewAccountWithIncorrectData(RequestSpecification requestSpec, RestAssuredConfig config,
                                                         String registerUrl, User userData, String kindOfTest) {
        Map<String, Object> requestData = new HashMap<>();
        Response response = null;

        if(kindOfTest == "incorrectEmail") {
            requestData.put("userEmail", "incorrectEmail.com");
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "incorrectPassword") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", "password");
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "shortPassword") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", "passw");
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "longPassword") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", "F5F2knsXAfhCAiX0ybJZMGQv4ZeOSniQx8wonSgyZjZB6dzCD0sIYAB7CvZLkVJQ9");
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "incorrectConfirmPassword") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", "incorrectConfirmPassword");
            requestData.put("userGender", userData.userGender);
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "incorrectGender") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", "incorrectGender");
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        if(kindOfTest == "longGender") {
            requestData.put("userEmail", userData.userEmail);
            requestData.put("userPassword", userData.userPassword);
            requestData.put("confirmPassword", userData.confirmPassword);
            requestData.put("userGender", "eBFCc9CaBAqACPEwGxUpfLEIs8wE8SRmg");
            requestData.put("userVerification", userData.userVerification);

            response = given()
                    .spec(requestSpec)
                    .config(config)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestData)
                    .when()
                    .post(registerUrl);
        }
        return response;
    }
}
