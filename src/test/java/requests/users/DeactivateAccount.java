package requests.users;

import com.google.gson.Gson;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeactivateAccount {
    public static Gson gson = new Gson();

    public static Response deactivateAccount(RequestSpecification requestSpec, RestAssuredConfig config, String deactivateUrl, String userToken, User userData) {

        Map<String, Object> user = new HashMap<>();
        user.put("userPassword", userData.userPassword);
        user.put("confirmPassword", userData.confirmPassword);

        String requestData = gson.toJson(user);

        var response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userToken)
                .body(requestData)
                .when()
                .patch(deactivateUrl);

        return response;
    }
}
