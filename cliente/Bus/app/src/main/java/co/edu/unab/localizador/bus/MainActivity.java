package co.edu.unab.localizador.bus;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import static android.R.attr.duration;

public class MainActivity extends AppCompatActivity {
    ArrayList<LogData> ly_logs = new ArrayList<LogData>();

    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    private static final int INITIAL_REQUEST=1337;
    private static final int CAMERA_REQUEST=INITIAL_REQUEST+1;
    private static final int CONTACTS_REQUEST=INITIAL_REQUEST+2;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;

    private LocationManager mlocManager;
    private MyLocationListener locacion;

    TextView val_longitud;
    TextView val_latitud;
    EditText txt_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        val_longitud = (TextView) findViewById(R.id.val_longitud);
        val_latitud = (TextView) findViewById(R.id.val_latitud);

        txt_ip = (EditText) findViewById(R.id.txt_server);



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mlocManager = (LocationManager) getSystemService(this.getApplication().LOCATION_SERVICE);
            locacion = new MyLocationListener();
            locacion.setMainActivity(this);
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locacion);
            return;
        }else{
            //requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                LinkedList<String> missingPermissions = new LinkedList<>();
                for(String p : INITIAL_PERMS){
                    if(checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED){
                        missingPermissions.add(p);
                    }
                }
                if(!missingPermissions.isEmpty()){
                    String[] mpArray = new String[missingPermissions.size()];
                    missingPermissions.toArray(mpArray);
                    requestPermissions(mpArray, INITIAL_REQUEST);
                }
            }
        }

    }

    public void actualizarUI(Ubicacion loc){
        if (loc != null){
            String longitud = String.valueOf(loc.getLongitud());
            String latitud = String.valueOf(loc.getLatitud());
            String val_actual_latitud = (String) val_latitud.getText();
            String val_actual_longitud = (String) val_longitud.getText();

            if (val_actual_latitud != latitud && val_actual_longitud != longitud){
                val_latitud.setText(String.valueOf(loc.getLatitud()));
                val_longitud.setText(String.valueOf(loc.getLongitud()));
                String ip = (String) txt_ip.getText().toString();
                String url = "http://"+ip+":3000/setData/"+latitud+"/"+longitud;
                mostrarMensaje(url);
                new Consultar().execute(url);
            }
        }else{
            val_latitud.setText("(Desconocida)");
            val_longitud.setText("(Desconocida)");
        }
    }

    private void mostrarMensaje(String mensaje){
        Toast toast = Toast.makeText(this.getApplicationContext(), mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

    private class Consultar extends AsyncTask<String, Void, String>{
        protected String doInBackground(String ... urls){
            try{
                return HttpRequest.get(urls[0]).accept("application/json")
                        .body();
            }catch(Exception ex){
                return null;
            }

        }
    }

}

