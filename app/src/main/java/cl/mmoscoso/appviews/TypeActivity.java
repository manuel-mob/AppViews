package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.mmoscoso.appviews.controller.DBManager;
import cl.mmoscoso.appviews.controller.DBManagerType;
import cl.mmoscoso.appviews.entity.Product;

public class TypeActivity extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    DBManagerType dbManagerType;

    ListView listType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        //Getting information from DATABASE
        dbManagerType = new DBManagerType(this);
        dbManagerType.open();
        Cursor cursor = dbManagerType.fetch();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            list.add(name);
        }
        Long rowId = dbManagerType.insert("Tipo " + String.valueOf(list.size()));
        if (rowId != null){
            Toast.makeText(this,"Type saved",Toast.LENGTH_LONG).show();
        }


        listType = (ListView)findViewById(R.id.ListViewType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);

        listType.setAdapter(adapter);

    }
}