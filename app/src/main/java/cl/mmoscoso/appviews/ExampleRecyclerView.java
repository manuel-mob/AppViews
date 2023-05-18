package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.mmoscoso.appviews.adapters.CarAdapterRecycler;
import cl.mmoscoso.appviews.entity.Car;

public class ExampleRecyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CarAdapterRecycler carAdapter;
    private List<Car> carList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_recycler_view);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of Car objects
        carList = new ArrayList<>();
        carList.add(new Car( "Manufacturer 1","Car 1", 2022));
        carList.add(new Car( "Manufacturer 2","Car 2", 2023));
        carList.add(new Car( "Manufacturer 3","Car 3", 2021));

        Log.i("TEST","Size:"+String.valueOf(carList.size()));
        // Create and set the adapter
        carAdapter = new CarAdapterRecycler(carList);
        recyclerView.setAdapter(carAdapter);


    }
}