package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExampleCardView extends AppCompatActivity {

    CardView cardView;
    TextView textInsideCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_card_view);

        cardView = findViewById(R.id.card_view);
        textInsideCard = findViewById(R.id.info_text);

        textInsideCard.setText(R.string.app_name +" - Hola Mundo por aquí" );

// Set an OnClickListener for the card
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle card click event
            }
        });



    }
}