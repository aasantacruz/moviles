import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import com.google.gson.*;


public class GetExample {
	
	public static void main(String[] args){
		try {
			Gson gson = new Gson();
			URL url = new URL("http://localhost:3000/api/v1/dishes");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				JsonParser parser = new JsonParser();
				JsonArray array = parser.parse(output).getAsJsonArray();
				System.out.println(gson.fromJson(array.get(0), Dish.class).getId());
				Iterator iterator = array.iterator();
				while(iterator.hasNext()){
					System.out.println(gson.fromJson(iterator.next().toString(),Dish.class).getId());
				}
			}
			conn.disconnect();
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		}
	
}
