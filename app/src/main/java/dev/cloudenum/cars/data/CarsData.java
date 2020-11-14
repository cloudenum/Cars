package dev.cloudenum.cars.data;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dev.cloudenum.cars.model.Car;
import dev.cloudenum.cars.model.Engine;

public class CarsData {
    private static final String TAG = "CarsData";

    public static ArrayList<Car> getListData(Context context) {
        ArrayList<Car> carsList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = context.getAssets().open("cars.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

            is.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        String jsonString = sb.toString();
        Resources resources = context.getResources();

        if (jsonString != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonString);
                JSONArray cars = jsonObj.getJSONArray("cars");
                for (int i = 0; i < cars.length(); ++i) {
                    JSONObject carJSON = cars.getJSONObject(i);
                    JSONObject engineJSON = carJSON.getJSONObject("engine");

                    Car car = new Car();
                    Engine engine = new Engine();
                    engine.setCode(engineJSON.getString("code"));
                    engine.setType(engineJSON.getString("type"));
                    engine.setLayout(engineJSON.getString("layout"));
                    engine.setPower(engineJSON.getInt("power"));
                    engine.setTorque(engineJSON.getInt("torque"));
                    car.setMaker(carJSON.getString("maker"));
                    car.setModel(carJSON.getString("model"));
                    car.setYear(carJSON.getInt("year"));
                    car.setImg(resources.getIdentifier(carJSON.getString("img"), "drawable", context.getPackageName()));
                    car.setEngine(engine);

                    carsList.add(car);
                }
            } catch (final JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }

        return carsList;
    }
}
