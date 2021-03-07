package admhe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.io.DataInputStream;
import java.util.Arrays;
import java.util.OptionalDouble;

public class Average {
	public static String getdata(String start_date, String end_date) {
		try 
		{
		  URL weburl=new URL("https://data.gov.gr/api/v1/query/admie_realtimescadasystemload?date_from="+start_date+"&date_to="+end_date);
		  HttpURLConnection conn = (HttpURLConnection) weburl.openConnection();
		  conn.setRequestMethod("GET");
		  conn.setRequestProperty("Accept", "application/json");
		  conn.setRequestProperty("Authorization", "Token b487c4bf21886584befce6b034b6cb2ddb03eb18");
		  System.out.println("Output is: "+conn.getResponseCode());
		  BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
          String output;
          StringBuffer values = new StringBuffer();
          StringBuffer response = new StringBuffer();
          
          while ((output = in.readLine()) != null) {              //Από τη σύνδεσή μας πήραμε με GET τα δεδομένα
              response.append(output);                            //και τα αποθηκεύσαμε σε μορφή String
          }
          in.close();
          
          //System.out.println(response.toString());                  //Θέλουμε να βρούμε τις τιμές του πεδίου 
          for (int i = 0;i<response.toString().length()-6;i++) {      //"energy_mwh" και κάνουμε ένα αυτοσχέδιο
            String slice = response.toString().substring(i,i+3);      //"mapping"
            //System.out.println(slice);
            if (slice.contentEquals("mwh")) {
            	int j = i+5;
                while(response.toString().charAt(j)!= ',') {
                	values.append(response.toString().charAt(j));
                	j++;
                }
                values.append(",");
            }
          }
          //System.out.println(values.toString());                  //μετατρέπουμε το String με τις τιμές σε Array 
          String[] s_array = values.toString().split(",");          //και μετά σε int
          //System.out.println(Arrays.toString(s_array));
          int[] int_array = new int[s_array.length];
          for(int i = 0;i < s_array.length;i++)
          {
             int_array[i] = Integer.parseInt(s_array[i]);
          }
          //System.out.println(Arrays.toString(int_array));             //βρίσκουμε το μέσο όρο και το επιστρέφουμε
          OptionalDouble Avg = Arrays.stream(int_array).average();      //ως String
          System.out.println(Avg);
          return Avg.toString().substring(15,22);
        }
		catch(Exception e)
        {
            System.out.println(e.getMessage());
            return " ";
        }	
	}

}
