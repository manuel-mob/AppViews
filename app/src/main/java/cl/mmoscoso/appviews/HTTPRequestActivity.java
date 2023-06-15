package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import cl.mmoscoso.appviews.requests.HttpRequestsTemplate;

public class HTTPRequestActivity extends AppCompatActivity {


    private TextView name;
    private TextView capital;
    private TextView currency;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httprequest);

        name = (TextView)findViewById(R.id.idNameCountry);
        capital = (TextView)findViewById(R.id.idCapitalCountry);
        progress = (ProgressBar) findViewById(R.id.idProgressBar);

        //String url = "https://restcountries.com/v3.1/all?fields=name,capital,currencies";
        String url = "https://restcountries.com/v3.1/all?fields=name,capital";
        HttpRequestsTemplate task = new HttpRequestsTemplate();
        task.setViewGuis(name,capital,progress);

        task.execute(url);
    }
}