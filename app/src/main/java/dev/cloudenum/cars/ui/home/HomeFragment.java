package dev.cloudenum.cars.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.cloudenum.cars.R;
import dev.cloudenum.cars.adapter.ListCarAdapter;
import dev.cloudenum.cars.adapter.OnItemClickCallback;
import dev.cloudenum.cars.model.Car;
import dev.cloudenum.cars.DetailsActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView rvCars;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rvCars = root.findViewById(R.id.rv_cars);
        rvCars.setHasFixedSize(true);
        homeViewModel.getCarList().observe(getViewLifecycleOwner(), cars -> {
            rvCars.setLayoutManager(new LinearLayoutManager(getContext()));
            ListCarAdapter listCarAdapter = new ListCarAdapter(cars);
            rvCars.setAdapter(listCarAdapter);

            listCarAdapter.setOnItemClickCallback(new OnItemClickCallback() {
                @Override
                public void onItemClicked(Car car) {
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("car_maker", car.getMaker());
                    intent.putExtra("car_year", car.getYear());
                    intent.putExtra("car_model", car.getModel());
                    intent.putExtra("car_img", car.getImg());
                    intent.putExtra("car_engine_code", car.getEngine().getCode());
                    intent.putExtra("car_engine_layout", car.getEngine().getLayout());
                    intent.putExtra("car_engine_power", car.getEngine().getPower());
                    intent.putExtra("car_engine_torque", car.getEngine().getTorque());
                    intent.putExtra("car_engine_type", car.getEngine().getType());
                    startActivity(intent);
                }
            });
        });

        return root;
    }
}