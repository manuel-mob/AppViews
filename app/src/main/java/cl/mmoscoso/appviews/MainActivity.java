package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import cl.mmoscoso.appviews.entity.Product;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    LinearLayout linearLayout;

    Product newProduct;

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

    public void goToProductsExample(View view){
        newProduct = new Product();
        newProduct.setName("Queso");
        newProduct.setAmount(990);
        newProduct.setQuantity(2);
        newProduct.setExpiration(new Date());
        Intent productActivity = new Intent(MainActivity.this,ProductInventary.class);

        startActivity(productActivity);

    }


    public void goToFragmentExample(View view){
        Intent fragmentActivity = new Intent(MainActivity.this,ProductFragmentMainActivity.class);
        startActivity(fragmentActivity);
    }

    public void goToPreferences(View view){
        Intent prefActivity = new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(prefActivity);
    }

    public void showDialog(View view){
        // Create a new dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Product");

        // Inflate the dialog layout
        View dialogLayout = getLayoutInflater().inflate(R.layout.product_detail_dialog, null);

        // Get references to the views in the layout
        TextView textView = dialogLayout.findViewById(R.id.dialog_textview);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "OK button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel button clicked", Toast.LENGTH_SHORT).show();
            }
        });


        builder.setView(dialogLayout);


        AlertDialog dialog = builder.create();


        dialog.show();
    }
}