package co.edu.unab.localizador.bus;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


/**
 * Created by josue on 2/3/17.
 */

public class MyLocationListener implements LocationListener {

    MainActivity mainActivity;

    public void setMainActivity(MainActivity _mainActivity){
        this.mainActivity = _mainActivity;
    }

    public MainActivity getMainActivity(){
        return this.mainActivity;
    }

    @Override
    public void onLocationChanged(Location location) {
        Ubicacion ubica = new Ubicacion(location.getLatitude(), location.getLongitude());
        this.mainActivity.actualizarUI(ubica);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
