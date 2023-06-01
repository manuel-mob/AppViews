package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cl.mmoscoso.appviews.requests.HttpRequestsTemplate;

public class HTTPRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httprequest);

        String url = "https://restcountries.com/v3.1/all?fields=name,capital,currencies";
        HttpRequestsTemplate task = new HttpRequestsTemplate();
        task.execute(url);
    }
}