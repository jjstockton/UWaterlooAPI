
import java.net.*;
import java.io.*;

public class UWaterloo {

    public UWaterloo(String key) throws IOException {


        String url = "https://api.uwaterloo.ca/v2/api/usage.json?key=" + key;

        URL website = new URL(url);
        URLConnection conn = website.openConnection();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch(IOException e){
            throw new IllegalArgumentException("Invalid API key!");
        }
    }



}
