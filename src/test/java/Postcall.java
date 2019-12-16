import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Postcall {

    public static String authToken;
    public static String itemID;
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
        // Object itemid;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("content_type", "application/json");
        headers.put("Authorization", authToken);

        Response res = given()
                .headers(headers)
                .when()
                .post("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart");
        Assert.assertEquals(res.statusCode(), 201);
        System.out.println(res.prettyPrint());

        JsonPath jsonPathEvaluator = res.jsonPath();
        Map rel = (Map) jsonPathEvaluator.getMap("data").get("relationships");
      //  Map rel = (Map) res.jsonPath().getMap("data").get("relationships");
        List<Map> lineItems = (List<Map>) ((Map) rel.get("line_items")).get("data");
        // List<Map> lineItems = (List<Map>) ((Map)rel.get("line_items")).get("data");
        ArrayList<String>lineItemIds = new ArrayList<String>();
        for (Map m : lineItems) {
            System.out.println("Line item id added in the cart is ::::" + m.get("id"));
            lineItemIds.add(m.get("id").toString());
        }
        itemID=lineItemIds.get(1);
        System.out.println("item id is " +itemID);

    }

    @Test(priority = 4)
    public void delete_Item() {
        Map<String, String> headers  = new HashMap<String,String>();
        headers.put("content_type","application/json");
        headers.put("Authorization",authToken);

        Response res = given()
                .headers(headers)
                .when()
                .delete("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart/remove_line_item/"+itemID.toString());
        Assert.assertEquals(res.statusCode(),200);
    }

}
