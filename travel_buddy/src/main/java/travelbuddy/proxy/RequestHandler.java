package travelbuddy.proxy;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
* RequestHandler handles requests from the proxies
*/
class RequestHandler {

    public enum RequestMethod {
        GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
    }

    public static String execute(RequestMethod requestMethod, String targetURL) {
        return execute(requestMethod, targetURL, null);
    }

    public static String execute(RequestMethod requestMethod, String targetURL, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;

        try {
            // Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod.toString());
            //setting content-type is required for Flight API
            connection.setRequestProperty("Content-Type", "application/json");
            
            if (urlParameters != null && !"".equals(urlParameters)) {
                connection.setDoOutput(true);
                System.out.println("parameters are "  + urlParameters);
                // Send request parameters
                DataOutputStream writer;
                writer = new DataOutputStream(connection.getOutputStream());
                writer.writeBytes(urlParameters);
                writer.close();
            }

            // Get response
            InputStream is = connection.getInputStream();
            StringBuilder response;
            try (BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
                String line;
                response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
            }
            List<String> flight = new ArrayList<>();
            flight.add(response.toString());
            
           // System.out.println("The Response" + response);
            return response.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
