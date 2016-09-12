
import javax.naming.AuthenticationException;
import java.net.*;
import java.io.*;
import org.json.*;

public class UWaterlooClient {

    private String apikey;

    //public UWaterlooClient() throws UnauthorizedException {
    //    throw new UnauthorizedException("API key is required.");
    //}

    public UWaterlooClient(String key) throws IOException, HttpResponseException {
        authenticate(key);
    }

    private static void authenticate(String key) throws IOException, HttpResponseException {

        String url = "https://api.uwaterloo.ca/v2/api/usage.json?key=" + key;

        //url = "https://api.uwaterloo.ca/v2/courses/C/135/schedule.json?key=abf875e0dcd95bc93484f9437934fc6e";

        URL website = new URL(url);
        //URLConnection conn = website.openConnection();

        HttpURLConnection conn = (HttpURLConnection)website.openConnection();



        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();

        System.out.println(responseCode);

        if(responseCode != 200) {

            throw new HttpResponseException(responseCode, conn.getErrorStream());

        }


    }

    public static int getResponseCode(InputStream response) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        String text= "";
        String line;
        while((line = br.readLine()) != null){
            text += line;
        }

        JSONObject obj = new JSONObject(text);

        return obj.getJSONObject("meta").getInt("status");


    }
}


class HttpResponseException extends Exception {

    public HttpResponseException() { super(); }
    public HttpResponseException(String message) { super(message); }
    public HttpResponseException(String message, Throwable cause) { super(message, cause); }
    public HttpResponseException(Throwable cause) { super(cause); }


    public HttpResponseException(int responseCode, InputStream errorStream) throws IOException, HttpResponseException {
        this(getErrorMessage(responseCode, errorStream));
    }

    private static String getErrorMessage(int responseCode, InputStream errorStream) throws IOException {

        String errorMessage ;

        switch (responseCode) {
            case 401:
                errorMessage = "Invalid API Key.";
                break;
            case 511:
                errorMessage = "API Key is required.";
                break;
            default:

                BufferedReader br = new BufferedReader(new InputStreamReader(errorStream));
                String text= "";
                String line;
                while((line = br.readLine()) != null){
                    text += line;
                }

                JSONObject obj = new JSONObject(text);

                errorMessage = obj.getJSONObject("meta").getString("message");
        }

        return errorMessage;

    }


}

