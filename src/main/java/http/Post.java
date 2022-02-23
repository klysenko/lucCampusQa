package http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Post {
        public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://postman-echo.com/post"))
                    .POST(HttpRequest.BodyPublishers.ofString("Sample request body"))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());


            System.out.println("status code: " + response.statusCode());
            System.out.println(response.body());

            //HttpRequest.BodyPublishers.ofString("Sample request body");


        }
    }
