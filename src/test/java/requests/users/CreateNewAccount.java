package requests.users;

import com.google.gson.Gson;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import static io.restassured.RestAssured.given;

public class CreateNewAccount {
    public static User user;
    public static Gson gson = new Gson();

    public static Response createNewAccount(RequestSpecification requestSpec, RestAssuredConfig config, String registerUrl) {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);
        String userData = gson.toJson(user);

        var response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userData)
                .when()
                .post(registerUrl);

        return response;
    }
}
