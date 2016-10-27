package UWaterloo.internal.http;

import UWaterloo.internal.json.JsonObject;

import java.io.InputStream;

import static UWaterloo.internal.utils.JsonUtils.getJson;

public class ApiResponseException extends RuntimeException {

    public ApiResponseException() {
        super();
    }

    private ApiResponseException(String message) {
        super(message);
    }

    public ApiResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiResponseException(Throwable cause) {
        super(cause);
    }


    public ApiResponseException(int responseCode, InputStream errorStream) {
        this(getApiResponse(responseCode, errorStream));
    }

    public static String getApiResponse(int responseCode, InputStream errorStream) {

        String errorMessage;

        switch (responseCode) {
            case 401:
                errorMessage = "Invalid API Key.";
                break;
            case 511:
                errorMessage = "API Key is required.";
                break;
            default:
                JsonObject obj = getJson(errorStream);
                errorMessage = obj.getJsonObject("meta").getString("message");
        }

        return errorMessage;

    }
}
