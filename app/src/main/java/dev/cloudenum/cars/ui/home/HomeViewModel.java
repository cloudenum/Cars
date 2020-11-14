package dev.cloudenum.cars.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.cloudenum.cars.data.CarsData;
import dev.cloudenum.cars.model.Car;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<ArrayList<Car>> mCarList;

    public HomeViewModel(Application application) {
        super(application);
        mCarList = new MutableLiveData<>();
        ArrayList<Car> carList = new ArrayList<>(CarsData.getListData(getApplication().getApplicationContext()));
        mCarList.setValue(carList);
    }

    public LiveData<ArrayList<Car>> getCarList() { return mCarList; }
}