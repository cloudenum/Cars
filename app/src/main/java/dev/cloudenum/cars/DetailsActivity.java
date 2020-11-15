package dev.cloudenum.cars;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        toolbar = findViewById(R.id.toolbar_Detail);
        setSupportActionBar(toolbar);

        TextView tvCarMaker = findViewById(R.id.tvCarMaker_Detail);
        TextView tvCarYear = findViewById(R.id.tvCarYear_Detail);
        TextView tvCarModel = findViewById(R.id.tvCarModel_Detail);
        ImageView imgCar= findViewById(R.id.imgCar_Detail);
        TextView tvCarEngineCode = findViewById(R.id.tvCarEngineCode_Detail);
        TextView tvCarEngineLayout = findViewById(R.id.tvCarEngineLayout_Detail);
        TextView tvCarEngineType = findViewById(R.id.tvCarEngineType_Detail);
        TextView tvCarEnginePower = findViewById(R.id.tvCarEnginePower_Detail);
        TextView tvCarEngineTorque = findViewById(R.id.tvCarEngineTorque_Detail);

        String carMaker = getIntent().getStringExtra("car_maker");
        String carYear = String.valueOf(getIntent().getIntExtra("car_year", 0));
        String carModel = getIntent().getStringExtra("car_model");
        int carImgID = getIntent().getIntExtra("car_img", R.drawable.porsche_taycan_4s_2020);
        String carEngineCode = getIntent().getStringExtra("car_engine_code");
        String carEngineLayout = getIntent().getStringExtra("car_engine_layout");
        String carEngineType = getIntent().getStringExtra("car_engine_type");
        String carEnginePower = getIntent().getIntExtra("car_engine_power", 0) + " hp";
        String carEngineTorque = getIntent().getIntExtra("car_engine_torque", 0) + " N.m";

        tvCarMaker.setText(carMaker);
        tvCarYear.setText(carYear);
        tvCarModel.setText(carModel);
        Picasso.get()
                .load(carImgID)
                .fit()
                .centerCrop()
                .into(imgCar);
        tvCarEngineCode.setText(carEngineCode);
        tvCarEngineLayout.setText(carEngineLayout);
        tvCarEnginePower.setText(carEnginePower);
        tvCarEngineTorque.setText(carEngineTorque);
        tvCarEngineType.setText(carEngineType);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
