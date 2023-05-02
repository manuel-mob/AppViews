package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.mmoscoso.appviews.adapters.CarAdapter;
import cl.mmoscoso.appviews.entity.Car;

public class ExampleListViewCustomAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_list_view_custom_adapter);

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Ford", "Mustang", 2020));
        cars.add(new Car("Chevrolet", "Camaro", 2019));
        cars.add(new Car("Dodge", "Challenger", 2021));

        ListView listView = findViewById(R.id.list_view_car);
        CarAdapter adapter = new CarAdapter(this, cars);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car selectedItem = (Car) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Selected item: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Car selectedCar = (Car) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Long clicked on car: " + selectedCar.getManufacture() + " " + selectedCar.getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

}