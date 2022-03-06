package tests.users;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requests.users.CreateNewAccount.createNewAccount;
import static requests.users.DeactivateAccount.deactivateAccount;
import static requests.users.LoginIntoAccount.loginIntoAccount;
import static requests.users.LoginIntoAccount.loginIntoAccountWithIncorrectData;

public class LoginIntoAccount {
    private static RequestSpecification requestSpec;
    private static RestAssuredConfig config;
    public static User user;
    private static String registerUrl = "/register";
    private static String loginUrl = "/login";
    private static String deactivateUrl = "/delete";

    LoginIntoAccount() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://learnandtest.herokuapp.com/quiz/users").
                build();
        config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig());
    }

    @Test
    public void TestLoginIntoAccount() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("message", equalTo("Rejestracja przebiegła pomyślnie!"));

        Response loginIntoAccountResponse = loginIntoAccount(requestSpec, config, loginUrl, user);
        loginIntoAccountResponse.then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("token", notNullValue());

        JsonPath jsonPathEvaluator = loginIntoAccountResponse.jsonPath();
        String userToken = "Bearer " + jsonPathEvaluator.get("token");

        Response deactivateAccountResponse = deactivateAccount(requestSpec, config, deactivateUrl, userToken, user);
        deactivateAccountResponse.then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("message", equalTo("Konto zostało dezaktywowane!"));
    }

    @Test
    public void TestTryToLoginWithoutRegistration() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response loginIntoAccountResponse = loginIntoAccountWithIncorrectData(requestSpec, config, loginUrl, user.userEmail, user.userPassword, "withoutRegistration");
        loginIntoAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("error", equalTo("Wprowadzony email nie istnieje!"));
    }

    @Test
    public void TestTryToLoginWithoutEmail() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response loginIntoAccountResponse = loginIntoAccountWithIncorrectData(requestSpec, config, loginUrl,
                user.userEmail, user.userPassword, "withoutEmail");
        loginIntoAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userEmail", equalTo("Brak wymaganych danych!"));
    }

    @Test
    public void TestTryToLoginWithoutPassword() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response loginIntoAccountResponse = loginIntoAccountWithIncorrectData(requestSpec, config, loginUrl,
                user.userEmail, user.userPassword, "withoutPassword");
        loginIntoAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userPassword", equalTo("Brak wymaganych danych!"));
    }

    @Test
    public void TestLoginWithEmptyEmail() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response loginIntoAccountResponse = loginIntoAccountWithIncorrectData(requestSpec, config, loginUrl,
                user.userEmail, user.userPassword, "emptyEmail");
        loginIntoAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userEmail", equalTo("Adres e-mail został wprowadzony niepoprawnie!"));
    }

    @Test
    public void TestLoginWithEmptyPassword() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response loginIntoAccountResponse = loginIntoAccountWithIncorrectData(requestSpec, config, loginUrl,
                user.userEmail, user.userPassword, "emptyPassword");
        loginIntoAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userPassword", equalTo("Hasło jest za krótkie!"));
    }
}
