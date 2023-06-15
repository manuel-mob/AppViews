package cl.mmoscoso.appviews.requests;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class HttpRequestsTemplate extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> name;
    private WeakReference<TextView> capital;
    private WeakReference<TextView> currency;
    private WeakReference<ProgressBar> progress;


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

        try {
            JSONArray jsonArray = new JSONArray(result.toString());
            // Process each JSON object in the array
            //Random country
            Random random = new Random();
            int randomNumber = random.nextInt(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {

                if ( randomNumber == i ) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    JSONObject nameObject = jsonObj.getJSONObject("name");
                    String name = nameObject.getString("common");

                    JSONArray capitalArray = jsonObj.getJSONArray("capital");
                    if (capitalArray.length() == 1) {
                        String capital = capitalArray.getString(0);
                        this.capital.get().setText(capital.toString());
                        this.capital.get().setVisibility(TextView.VISIBLE);
                    }

                    this.name.get().setText(name.toString());
                    this.name.get().setVisibility(TextView.VISIBLE);

                    this.progress.get().setVisibility(ProgressBar.GONE);
                    Log.i(this.getClass().getName(),Integer.toString(i) +" : " + jsonObj.toString());
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    public void setViewGuis(TextView name, TextView capital, ProgressBar progress) {
        this.name = new WeakReference<>(name);
        this.capital = new WeakReference<>(capital);
        this.progress = new WeakReference<>(progress);

    }
}
