package tests.schoolSubjects;

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
import static requests.schoolsubjects.GetListOfSchoolSubjects.getListOfSubjects;
import static requests.users.CreateNewAccount.createNewAccount;
import static requests.users.DeactivateAccount.deactivateAccount;
import static requests.users.LoginIntoAccount.loginIntoAccount;

public class GetSubjectsAsCommonUser {
    private static RequestSpecification requestSpec, userRequestSpec;
    private static RestAssuredConfig config;
    public static User user;
    private static String registerUrl = "/register";
    private static String loginUrl = "/login";
    private static String deactivateUrl = "/delete";
    private static String getSubjectsUrl = "/subjects";

    GetSubjectsAsCommonUser() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://learnandtest.herokuapp.com/quiz/schoolSubjects").
                build();
        userRequestSpec = new RequestSpecBuilder().
                setBaseUri("https://learnandtest.herokuapp.com/quiz/users").
                build();
        config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig());
    }

    @Test
    public void TakeListOfSubjects() {
        user = new User(null, null, null , null,true);
        user.createNewUserAccount(user);

        Response createAccountResponse = createNewAccount(userRequestSpec, config, registerUrl, user);

        createAccountResponse.then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("message", equalTo("Rejestracja przebiegła pomyślnie!"));

        Response loginIntoAccountResponse = loginIntoAccount(userRequestSpec, config, loginUrl, user);
        loginIntoAccountResponse.then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("token", notNullValue());

        JsonPath jsonPathEvaluator = loginIntoAccountResponse.jsonPath();
        String userToken = "Bearer " + jsonPathEvaluator.get("token");

        Response getSubjectsAsCommonUser = getListOfSubjects(requestSpec, config, getSubjectsUrl, userToken);
        getSubjectsAsCommonUser.then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("schoolSubjects[0].name",is("Język polski"))
                .body("schoolSubjects[1].name",is("Język angielski"))
                .body("schoolSubjects[2].name",is("Język niemiecki"))
                .body("schoolSubjects[3].name",is("Muzyka"))
                .body("schoolSubjects[4].name",is("Plastyka"))
                .body("schoolSubjects[5].name",is("Historia"))
                .body("schoolSubjects[6].name",is("Wiedza o społeczeństwie"))
                .body("schoolSubjects[7].name",is("Przyroda"))
                .body("schoolSubjects[8].name",is("Geografia"))
                .body("schoolSubjects[9].name",is("Biologia"))
                .body("schoolSubjects[10].name",is("Chemia"))
                .body("schoolSubjects[11].name",is("Fizyka"))
                .body("schoolSubjects[12].name",is("Matematyka"))
                .body("schoolSubjects[13].name",is("Informatyka"))
                .body("schoolSubjects[14].name",is("Technika"))
                .body("schoolSubjects[15].name",is("Wychowanie fizyczne"))
                .body("schoolSubjects[16].name",is("Edukacja dla bezpieczeństwa"))
                .body("schoolSubjects[17].name",is("Podstawy przedsiębiorczości"))
                .body("schoolSubjects[18].name",is("Etyka"))
                .body("schoolSubjects[19].name",is("Religia"))
                .body("schoolSubjects[20].name",is("Filozofia"));


        Response deactivateAccountResponse = deactivateAccount(userRequestSpec, config, deactivateUrl, userToken, user);
        deactivateAccountResponse.then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("message", equalTo("Konto zostało dezaktywowane!"));
    }
}
