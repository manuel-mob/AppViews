package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import cl.mmoscoso.appviews.adapters.LocaleHelper;

public class LanguageChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change);

        Button changeLanguageButton = findViewById(R.id.btn_change_language);
        changeLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the app's locale to a different language
                LocaleHelper.setLocale(LanguageChangeActivity.this, "es");
                recreate();
            }
        });
    }

}