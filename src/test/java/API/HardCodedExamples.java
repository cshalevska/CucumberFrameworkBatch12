package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = " Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTUyNTI2NDUsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NTI5NTg0NSwidXNlcklkIjoiMzg1MiJ9.u0NhcsDMW-ipbjnJR3Ymxeb0noPbGGXr00LKbJI2B8I";
static String employee_id;
    @Test
    public void createEmployee() {
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"emp_firstname\": \"Cece\",\n" +
                        "  \"emp_lastname\": \"Shalevska\",\n" +
                        "  \"emp_middle_name\": \"S\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1989-06-12\",\n" +
                        "  \"emp_status\": \"Full\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");
     Response response =request.when().post("/createEmployee.php");
    //response.prettyPrint();
    response.then().assertThat().statusCode(201);

        //Hamcreast matchers - to deal with the keys,body, to perform all the kind of validation
        response.then().assertThat().body("Message",equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname",equalTo("Cece"));
        response.jsonPath().getString("Employee.emp_firstname_id");
        System.out.println(employee_id);
    }
}