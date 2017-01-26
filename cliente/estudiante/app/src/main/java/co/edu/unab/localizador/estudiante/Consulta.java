package co.edu.unab.localizador.estudiante;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

/**
 * Created by josue on 25/01/17.
 * Clase para consultar en el servidor la ubicacion del dispositivo
 */

public class Consulta {

    GoogleMap googleMap = null;

    public Consulta(GoogleMap _googleMap){
        this.googleMap = _googleMap;
    }

    private class Consultar extends AsyncTask<String, Long, String>{

        @Override
        protected String doInBackground(String...urls){
            try{
                return HttpRequest.get(urls[0]).accept("application/json")
                        .body();
            }catch (Exception ex){
                return null;
            }
        }

        protected void onPostExecute(String response){
            procesarUbicacion(response);
        }
    }

    public void ubicar(){
        String url = "http://ec2-52-24-181-82.us-west-2.compute.amazonaws.com:3000/data";
        new Consultar().execute(url);
    }

    private void procesarUbicacion(String respuesta){
        Localizacion loc = new Localizacion();
        JSONObject jObject = null;

        try{
            jObject = new JSONObject(respuesta);
            loc.latitud = jObject.getDouble("lat");
            loc.longitud = jObject.getDouble("lon");

        }catch (Exception ex){
            loc = null;
        }


        LatLng sydney = new LatLng(loc.latitud, loc.longitud);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marcador 1"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.setMinZoomPreference(17f);

    }

}
