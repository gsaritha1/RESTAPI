import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicTest {

    @Test
    public void TestStatusCode() {
        given().
                get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products")
                .then()
                .statusCode(200);
    }

    @Test
    public void testLogging() {
        given()
                .log().all()
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
    }

    @Test
    public void printReponse() {
        Response res = given().when()
                .log().all()
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");

        System.out.println(res.asString());
        System.out.println(res.prettyPrint());
    }

    @Test
    public void testCurrency() {
        given()
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products/1")
                .then()
                .body("data[0].attributes.currency", equalTo("USD"));
        //        System.out.println(data.length);

    }

    @Test
    public void testCurrency1() {
        Response res = given()
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products/1");
        JsonPath jsonPathEvaluator = res.jsonPath();
        System.out.println("currency: \n" + jsonPathEvaluator.get("currency"));

        List<Map> products = jsonPathEvaluator.getList("data");
        for (Map product : products) {
            Map attributes = (Map) product.get("atrributes");

            //  Assert.assertTrue(attributes.containsValue("USD"));
            Assert.assertTrue(attributes.get(("currency")).equals("USD"));
            // System.out.println("currency is ");

        }

       /* List<Map> Products = jsonPathEvaluator.getList("currency");
        for (Map product : products) {
            Map attributes = (Map) product.get("atrributes");
            //  Assert.assertTrue(attributes.containsValue("USD"));
            Assert.assertTrue(attributes.get("currency").equals("USD"));
            // System.out.println("currency is ");

        }*/
        //Creation of JsonPath object
      //  JsonPath jsonPathValidator = response.jsonPath();
        //1. To print the list of id from the response
        //System.out.println("ID : \n" + jsonPathValidator.get("id"));

        //2. Count Number of Records(Employees) in the Response
        /*List < String > jsonResponseRoot = jsonPathValidator.getList("$");
        System.out.println("Number of Employees : " + jsonResponseRoot.size());


        //3. Get the list of all the employee names
        List < String > allEmployeeNames = jsonPathValidator.getList("employee_name");
        System.out.println("\n Here is the names of all the employees :\n");
        for (String i: allEmployeeNames) {
            System.out.println(i);
        }

        //4. To Get the list of ages of all the employees
        String employeeAge = jsonPathValidator.getString("employee_age");
        System.out.println(employeeAge);


        //5. To get the name of the sixth employee in the list using 2 ways:
        //1.
        String sixthEmployeeName = jsonPathValidator.getString("employee_name[5]");
        System.out.println(sixthEmployeeName);
        //2.
        System.out.println(allEmployeeNames.get(5));


        //6. To validate if the 10th employee salary is greater than 100
        if (Integer.parseInt(jsonPathValidator.getString("employee_salary[9]")) > 100) {
            System.out.println("True - 10th employee salary is " + jsonPathValidator.getString("employee_salary[9]") + " which is greater than 100");
        } else {
            System.out.println("False - 10th employee salary is " + jsonPathValidator.getString("employee_salary[9]") + " which is lesser than 100");
        }*/


    }



}


