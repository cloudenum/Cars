package dev.cloudenum.cars.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.cloudenum.cars.R;
import dev.cloudenum.cars.model.Car;

public class ListCarAdapter extends RecyclerView.Adapter<ListCarAdapter.ListViewHolder> {

    private final ArrayList<Car> carList;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListCarAdapter(ArrayList<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_cars, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Car car = carList.get(position);
        Picasso.get()
                .load(car.getImg())
                .resize(55,55)
                .centerCrop()
                .into(holder.carImg);

        String carName = car.getMaker() + " " + car.getModel();
        holder.tvCarName.setText(carName);
        holder.tvCarYear.setText(String.valueOf(car.getYear()));
        holder.tvCarType.setText(car.getEngine().getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(carList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView carImg;
        TextView tvCarName, tvCarYear, tvCarType;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            carImg = itemView.findViewById(R.id.item_car_photo);
            tvCarName = itemView.findViewById(R.id.tvCarModel_Detail);
            tvCarYear = itemView.findViewById(R.id.detail_car_year_label);
            tvCarType = itemView.findViewById(R.id.tvCarYear_Detail);
        }
    }
}
