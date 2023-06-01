package cl.mmoscoso.appviews.requests;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestsTemplate extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to make the request to

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method and headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Make the request
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                bufferedReader.close();
                return response.toString();
            } else {
                // Handle unsuccessful response
                return "Error: " + responseCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the response here
        // This method runs on the UI thread after the request is completed
        // You can update UI components or perform any other necessary actions with the response data
        // For example, you can parse the JSON response and display it in your app's UI
        Log.i(this.getClass().getName(),result.toString());
    }
}
