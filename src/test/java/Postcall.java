import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Postcall {

    public static String authToken;
    @BeforeClass
    public void auth_token() {
        Response res = given()
                .formParam("grant_type", "password")
                .formParam("username", "gsar@rediffmail.com")
                .formParam("password", "Testing12")
                .post("https://spree-vapasi-prod.herokuapp.com/spree_oauth/token");
        System.out.println(res.prettyPrint());
        authToken= "Bearer "+res.path("access_token");
    }

    @Test(priority = 0)
    public void emptyCart() {
        Response res = given()
                .patch("https://spree-vapasi-prod.herokuapp.com/spree_oauth/api/v2/storefront/cart/empty");
        System.out.println(res.prettyPrint());
    }
    @Test(priority = 1)
    public void AddItem() {
        Map<String, String> headers  = new HashMap<String,String>();
        headers.put("content_type","application/json");
        headers.put("Authorization",authToken);

        String createBody = "{\n" +
                "  \"variant_id\": \"17\",\n" +
                "  \"quantity\": 5\n" +
                "}";
        Response res = given()
                        .headers(headers)
                        .body(createBody)
                        .when()
                        .post("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart/add_item");
            Assert.assertEquals(res.statusCode(),200);
    }
    @Test(priority = 2)
    public void Add_Item() {
        Map<String, String> headers  = new HashMap<String,String>();
        headers.put("content_type","application/json");
        headers.put("Authorization",authToken);

        String createBody = "{\n" +
                "  \"variant_id\": \"10\",\n" +
                "  \"quantity\": 8\n" +
                "}";
        Response res = given()
                .headers(headers)
                .body(createBody)
                .when()
                .post("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart/add_item");
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 3)
    public void ViewCart() {
        Map<String, String> headers  = new HashMap<String,String>();
        headers.put("content_type","application/json");
        headers.put("Authorization",authToken);

        Response res = given()
                .headers(headers)
                .when()
                .post("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart");
        Assert.assertEquals(res.statusCode(),201);
        System.out.println(res.prettyPrint());

        JsonPath jsonPathEvaluator = res.jsonPath();
      //  System.out.println("currency: \n"+ jsonPathEvaluator.get("currency"));

        List<Map> products = jsonPathEvaluator.getList("data");

        for(int i=0; i<products.size();i++) {
            System.out.println(products.get(i));
        }
   //     List<Map> relation = products.g
     //   System.out.println("Relationships: \n"+ jsonPathEvaluator.get("products"));
       // for (Map product : products) {
         //   Map data = (Map) product.get("line_items");
           // System.out.println("the ID number is "+data.get("id"));
            //  Assert.assertTrue(attributes.containsValue("USD"));
           // Assert.assertTrue(attributes.get(("currency")).equals("USD"));
            // System.out.println("currency is ");

        }

        /*  "line_items": {
                "data": [
                    {
                        "id": "414",
                        "type": "line_item"
                    },
                    {
                        "id": "417",
                        "type": "line_item"
                    },
                    {
                        "id": "418",
                        "type": "line_item"
                    }*/
    //}
    @Test(priority = 4)
    public void delete_Item() {
        Map<String, String> headers  = new HashMap<String,String>();
        headers.put("content_type","application/json");
        headers.put("Authorization",authToken);


        Response res = given()
                .headers(headers)
                .when()
                .post("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart/remove_line_item/378");
        Assert.assertEquals(res.statusCode(),200);
    }

}
