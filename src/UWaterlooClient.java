
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
        if(validKey(key)){
            this.apikey = key;
        }
    }

    private static boolean validKey(String key) throws IOException, HttpResponseException {

        String url = "https://api.uwaterloo.ca/v2/api/usage.json?key=" + key;
        URL website = new URL(url);

        HttpURLConnection conn = (HttpURLConnection)website.openConnection();

        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if(responseCode != 200) {
            throw new HttpResponseException(responseCode, conn.getErrorStream());
        }

        //This is necessary because the API sometimes returns a response code that is not correct
        int metaResponseCode = getResponseCode(conn.getInputStream());
        if(metaResponseCode != 200){
            throw new HttpResponseException(metaResponseCode, website.openStream());
        }

        conn.disconnect();

        return true;

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

    public static void printResponse(InputStream input) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String line;
        System.out.println("Response:");
        while((line = br.readLine()) != null){
            System.out.println(line);
        }

        System.out.println("end");
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

