package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.idLinearLayout);


        seekBar = findViewById(R.id.idSeekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Handle progress change

                int red = 255 - progress; // calculate the red component
                int green = 255 - progress; // calculate the green component
                int blue = 255 - progress; // calculate the blue component
                int backgroundColor = Color.rgb(red, green, blue); // create the background color
                linearLayout.setBackgroundColor(backgroundColor);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Handle start of tracking touch
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Handle end of tracking touch
            }
        });
    }

    public void setRedBackground(View view){
        linearLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }
    public void goToListViewExample(View view){
        Intent listViewActivity = new Intent(MainActivity.this,ExampleListView.class);
        startActivity(listViewActivity);
    }
    public void goToListViewCustomAdapterExample(View view){
        Intent listViewActivity = new Intent(MainActivity.this,ExampleListViewCustomAdapter.class);
        startActivity(listViewActivity);

    }
    public void goToGridViewExample(View view){
        Intent gridViewActivity = new Intent(MainActivity.this,ExampleGridView.class);
        startActivity(gridViewActivity);

    }
    public void goToCardViewExample(View view){
        Intent cardViewActivity = new Intent(MainActivity.this,ExampleCardView.class);
        startActivity(cardViewActivity);

    }
}