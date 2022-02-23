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

import static org.hamcrest.Matchers.*;
import static requests.users.CreateNewAccount.*;
import static requests.users.DeactivateAccount.deactivateAccount;
import static requests.users.LoginIntoAccount.loginIntoAccount;

public class CreateNewAccount {
    private static RequestSpecification requestSpec;
    private static RestAssuredConfig config;
    public static User user;
    private static String registerUrl = "/register";
    private static String loginUrl = "/login";
    private static String deactivateUrl = "/delete";

    CreateNewAccount() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://learnandtest.herokuapp.com/quiz/users").
                build();
        config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig());
    }

    @Test
    public void TestCreateNewAccount() {
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
    public void TestSendRequestWithoutUserEmail() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response tryToCreateAccountResponse = createNewAccountWithoutUserEmail(requestSpec, config, registerUrl, user);

        tryToCreateAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userEmail", equalTo("Brak wymaganych danych!"));
    }

    @Test
    public void TestSendRequestWithoutUserPassword() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response tryToCreateAccountResponse = createNewAccountWithoutUserPassword(requestSpec, config, registerUrl, user);

        tryToCreateAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userPassword", equalTo("Brak wymaganych danych!"));
    }

    @Test
    public void TestSendRequestWithoutConfirmPassword() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response tryToCreateAccountResponse = createNewAccountWithoutConfirmPassword(requestSpec, config, registerUrl, user);

        tryToCreateAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].confirmPassword", equalTo("Brak wymaganych danych!"));
    }

    @Test
    public void TestSendRequestWithoutUserGender() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response tryToCreateAccountResponse = createNewAccountWithoutUserGender(requestSpec, config, registerUrl, user);

        tryToCreateAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userGender", equalTo("Brak wymaganych danych!"));
    }

    @Test
    public void TestSendRequestWithoutUserVerification() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response tryToCreateAccountResponse = createNewAccountWithoutUserVerification(requestSpec, config, registerUrl, user);

        tryToCreateAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userVerification", equalTo("Brak wymaganych danych!"));
    }

    @Test
    public void TestCreateNewAccountWithEmptyEmail() {
        user = new User("", null, null , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userEmail", equalTo("Adres e-mail został wprowadzony niepoprawnie!"));
    }

    @Test
    public void TestCreateNewAccountWithEmptyPassword() {
        user = new User(null, "", null , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userPassword", equalTo("Hasło jest za krótkie!"))
                .assertThat()
                .body("validationError[1].confirmPassword", equalTo("Hasła są różne!"));
    }

    @Test
    public void TestCreateNewAccountWithEmptyConfirmPassword() {
        user = new User(null, null, "" , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].confirmPassword", equalTo("Hasła są różne!"));
    }

    @Test
    public void TestCreateNewAccountWithEmptyGender() {
        user = new User(null, null, null , "",true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userGender", equalTo("Wprowadzone dane są za krótkie!"));
    }

    @Test
    public void TestCreateNewAccountWithoutVerification() {
        user = new User(null, null, null , null,null);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userVerification", equalTo("Brak weryfikacji użytkownika!"));
    }

    @Test
    public void TestCreateNewAccountWithIncorrectEmail() {
        user = new User("incorrectEmail.com", null, null , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userEmail", equalTo("Adres e-mail został wprowadzony niepoprawnie!"));
    }

    @Test
    public void TestCreateNewAccountWithIncorrectPassword() {
        user = new User(null, "password", null , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userPassword", equalTo("Hasło nie zawiera minimum jednego znaku specjalnego!"));
    }

    @Test
    public void TestCreateNewAccountWithTooShortPassword() {
        user = new User(null, "passw", null , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userPassword", equalTo("Hasło jest za krótkie!"));
    }

    @Test
    public void TestCreateNewAccountWithTooLongPassword() {
        user = new User(null, "hy5yBNLTUWDU4VpxBwSnCUYNxUEbLPgLe", null , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userPassword", equalTo("Hasło jest za długie!"));
    }

    @Test
    public void TestCreateNewAccountWithIncorrectConfirmPassword() {
        user = new User(null, null, "differentPassword" , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].confirmPassword", equalTo("Hasła są różne!"));
    }

    @Test
    public void TestCreateNewAccountWithIncorrectGender() {
        user = new User(null, null, null , "incorrectGender",true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userGender", equalTo("Podano błędną wartość!"));
    }

    @Test
    public void TestCreateNewAccountWithTooLongValueInGender() {
        user = new User(null, null, null , "GL8NTHtvxBQ94IrSRrQV36uve7yx1axSB",true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(requestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("validationError[0].userGender", equalTo("Wprowadzone dane sa za długie!"));
    }
}
