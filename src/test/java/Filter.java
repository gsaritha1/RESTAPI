import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Filter {
    @Test
    public void testFilter()
    {
        Response res = given()
                .log().all()
                .queryParam("filter[name]", "bag")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPrint());

    }

    @Test
    public void ShirtFilter()
    {
        Response res = given()
                .log().all()
                .queryParam("filter[options][tshirt-color]", "S","Red")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPrint());

    }
    @Test
    public void PriceFilter()
    {
        Response res = given()
                .log().all()
                .queryParam("filter[price]", "15.99")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPrint());

    }
    @Test
    public void SortDe()
    {
        Response res = given()
                .log().all()
                .queryParam("sort=-updated_at%2","Cprice")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPrint());

    }
    @Test
    public void SortAc()
    {
        Response res = given()
                .log().all()
                .queryParam("sort=updated_at%2","Cprice")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPrint());

    }
    @Test
    public void per_page()
    {
        Response res = given()
                .log().all()
                .queryParam("per_page",6)
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPrint());

    }
    @Test
    public void page()
    {
        Response res = given()
                .log().all()
                .queryParam("page",2)
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPrint());

    }
}

