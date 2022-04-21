package requests.schoolsubjects;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetListOfSchoolSubjects {
    public static Response getListOfSubjects(RequestSpecification requestSpec, RestAssuredConfig config,
                                             String getSubjectsUrl, String userToken) {
        var response = given()
                .spec(requestSpec)
                .config(config)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userToken)
                .when()
                .get(getSubjectsUrl);

        return response;
    }
}
