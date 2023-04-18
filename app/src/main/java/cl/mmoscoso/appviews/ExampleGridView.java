package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class ExampleGridView extends AppCompatActivity {

    private GridView gridView;
    private String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_grid_view);

        gridView = findViewById(R.id.gridview);

        gridView.setNumColumns(2);
        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ExampleGridView.this, "Clicked item " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}