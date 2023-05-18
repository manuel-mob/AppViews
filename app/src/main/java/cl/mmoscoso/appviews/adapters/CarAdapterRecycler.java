package cl.mmoscoso.appviews.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.mmoscoso.appviews.R;
import cl.mmoscoso.appviews.entity.Car;

public class CarAdapterRecycler extends RecyclerView.Adapter<CarAdapterRecycler.CarViewHolder> {
    private List<Car> carList;

    public CarAdapterRecycler(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car_row, parent, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);

        holder.textCarName.setText(car.getName());
        holder.textCarManufacturer.setText(car.getManufacture());
        holder.textCarYear.setText(String.valueOf(car.getYear()));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView textCarName, textCarManufacturer, textCarYear, textCarDoors;

        public CarViewHolder(View itemView) {
            super(itemView);
            textCarName = itemView.findViewById(R.id.car_name);
            textCarManufacturer = itemView.findViewById(R.id.car_manufacture);
            textCarYear = itemView.findViewById(R.id.car_year);
        }
    }
}
