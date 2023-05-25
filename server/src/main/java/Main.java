import commons.Product;
import server.scraper.ProductScraper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        List<Product> products = ProductScraper.getInstance().getAllProducts();
//        System.out.println("pizza");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.hoogvliet.com/INTERSHOP/web/WFS/org-webshop-Site/nl_NL/-/EUR/ProcessTWProducts-GetTWProductsBySkus?products=205133000,718896000,705129000,033643000,070476000,707017000,075351000,081222000,009553000,214248000,040803000,201715000,062658000,205370000,205796000,047977000"))
                //.header("cookie", "sid=yRBuqZ0cPVhiqflzQ_PBqoYWRunjdXds1WDvulHgSAOiVQ%3D%3D; nlbi_2265421=QtlIZIjF1kHxBeCDFSnXtwAAAACNLvSMXt9JGDtC5gALfvmh; visid_incap_2265421=hvRo%2Bo7CQAGy9UK1V67Ex%2BOKb2QAAAAAQUIPAAAAAAA0lTXS96XAbn9MuJs9HCx9; incap_ses_1091_2265421=5O2QUbeb0TIagSxxnAMkDyKLb2QAAAAAlhNkEiaWBg9%2FKlc%2F9jPWJw%3D%3D")
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
