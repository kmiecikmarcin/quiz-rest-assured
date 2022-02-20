package tests.users;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static requests.users.CreateNewAccount.createNewAccount;

public class CreateNewAccount {
    private static RequestSpecification requestSpec;
    private static RestAssuredConfig config;
    private static String registerUrl = "/register";

    CreateNewAccount() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://learnandtest.herokuapp.com/quiz/users").
                build();
        config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig());
    }

    @Test
    public void TestCreateNewAccount(){
        Response response = createNewAccount(requestSpec, config, registerUrl);
        response.then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void TestCreateNewAccountWithEmptyEmail(){

    }
}
