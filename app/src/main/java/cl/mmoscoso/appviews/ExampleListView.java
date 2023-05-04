package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleListView extends AppCompatActivity {

    List<String> items = new ArrayList<String>();
    String newItem;

    ArrayList<HashMap<String, String>> dataList;
    ListView listView;
    Button buttonChangeAdapter;
    int option = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_list_view);

        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");

        buttonChangeAdapter = (Button)findViewById(R.id.buttonChangeAdapter);

        dataList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> dataMap1 = new HashMap<String, String>();
        dataMap1.put("title", "Title 1");
        dataMap1.put("subtitle", "Subtitle 1");
        dataList.add(dataMap1);

        HashMap<String, String> dataMap2 = new HashMap<String, String>();
        dataMap2.put("title", "Title 2");
        dataMap2.put("subtitle", "Subtitle 2");
        dataList.add(dataMap2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);

        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        defineListenerForListView(option);
    }
    public void changeAdapter(View view){


        if (option == 0) {
            buttonChangeAdapter.setText(getResources().getText(R.string.button_change_arrayadapter));
            String[] from = {"title", "subtitle"};
            int[] to = {android.R.id.text1, android.R.id.text2};

            // Create a SimpleAdapter and set it on the ListView
            SimpleAdapter adapter = new SimpleAdapter(this, dataList, android.R.layout.simple_list_item_2, from, to);
            listView.setAdapter(adapter);
            option = 1;
            defineListenerForListView(option);
        }
        else {
            buttonChangeAdapter.setText(getResources().getText(R.string.button_change_simpleadapter));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, items);
            listView.setAdapter(adapter);
            option = 0;
            newItem = "Item " + String.valueOf(items.size() + 1);
            items.add(newItem);
            defineListenerForListView(option);
        }
    }
    private void defineListenerForListView(int option){
        switch (option){
            case 0:
                //ArrayListener
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = (String) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(), "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();


                    }
                });
                break;
            case 1:
                //SimpleListener
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the data for the clicked item
                        HashMap<String, String> item = (HashMap<String, String>) parent.getItemAtPosition(position);
                        String title = item.get("title");
                        String subtitle = item.get("subtitle");

                        // Do something with the data, e.g. show a toast message
                        String message = "Clicked item: " + title + " (" + subtitle + ")";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

}