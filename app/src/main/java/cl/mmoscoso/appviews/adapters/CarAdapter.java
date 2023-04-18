package cl.mmoscoso.appviews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cl.mmoscoso.appviews.R;
import cl.mmoscoso.appviews.entity.Car;

public class CarAdapter extends ArrayAdapter<Car> {
    private final Context context;
    private final List<Car> cars;

    public CarAdapter(Context context, List<Car> cars) {
        super(context, R.layout.item_car_row, cars);
        this.context = context;
        this.cars = cars;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_car_row, parent, false);

        TextView carManufactureTextView = rowView.findViewById(R.id.car_manufacture);
        TextView carNameTextView = rowView.findViewById(R.id.car_name);
        TextView carYearTextView = rowView.findViewById(R.id.car_year);

        Car car = cars.get(position);
        carManufactureTextView.setText(car.getManufacture());
        carNameTextView.setText(car.getName());
        carYearTextView.setText(String.valueOf(car.getYear()));

        return rowView;
    }
}
