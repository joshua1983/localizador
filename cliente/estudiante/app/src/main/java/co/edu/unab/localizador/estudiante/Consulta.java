package co.edu.unab.localizador.estudiante;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by josue on 25/01/17.
 * Clase para consultar en el servidor la ubicacion del dispositivo
 */

public class Consulta {

    GoogleMap googleMap = null;
    Context _context = null;
    Localizacion _ultima = null;
    TextView _coordenada = null;
    int i=0;

    public Consulta(GoogleMap _googleMap, Context context, TextView coordenada){
        this._context = context;
        this.googleMap = _googleMap;
        this._coordenada = coordenada;
    }

    private class Consultar extends AsyncTask<String, Long, String>{

        @Override
        protected String doInBackground(String...urls){
            try{
                return HttpRequest.get(urls[0]).accept("application/json")
                        .body();
            }catch (Exception ex){
                return ex.getMessage();
            }
        }

        protected void onPostExecute(String response){
            procesarUbicacion(response);
        }
    }

    public void ubicar(){
        String url = "http://52.25.3.165:3000/data";
        new Consultar().execute(url);
    }

    private void procesarUbicacion(String respuesta){
        Log.d("joshua", "-"+ respuesta);
        Localizacion loc = new Localizacion();
        JSONObject jObject = null;

        try{

            jObject = new JSONObject(respuesta);
            //JSONArray ly_datos = jObject.getJSONArray("");
            //JSONObject ubicacion = ly_datos.getJSONObject(0);

            loc.latitud = jObject.getDouble("latitud");
            loc.longitud = jObject.getDouble("longitud");



        }catch (Exception ex){
            ex.printStackTrace();
            loc = null;
        }

        if (loc != null){
            LatLng sydney = new LatLng(loc.latitud, loc.longitud);
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marcador "+i));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            googleMap.setMinZoomPreference(17f);
            this._ultima = loc;
            this._coordenada.setText("lat: "+loc.latitud+" long: "+loc.longitud);
            i++;
        }else{
            Toast toast = Toast.makeText(this._context, "Red no encontrada", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
