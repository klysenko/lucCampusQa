package http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class Appache {

		public static void main(String[] args) throws IOException {
       HttpClient client = new DefaultHttpClient();

        HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/posts");

			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }

			System.out.println("status code: " + response.getStatusLine().getStatusCode());
		}


}
