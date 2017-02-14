package co.edu.unab.localizador.busmapa;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by josue on 2/11/17.
 */

public class LocalizacionListener implements LocationListener {

    MapsActivity mainActivity;

    public MapsActivity getMainActivity(){
        return mainActivity;
    }

    public void setMainActivity(MapsActivity _mainActivity){
        this.mainActivity = _mainActivity;
    }

    @Override
    public void onLocationChanged(Location location) {
        Localizacion loc = new Localizacion();
        loc.setLatitud( location.getLatitude());
        loc.setLongitud(location.getLongitude());
        this.mainActivity.cambiarUbicacion(loc);

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
