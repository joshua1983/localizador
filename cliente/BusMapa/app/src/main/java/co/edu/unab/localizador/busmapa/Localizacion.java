package co.edu.unab.localizador.busmapa;

/**
 * Created by josue on 2/11/17.
 */
public class Localizacion {
    double latitud;
    double longitud;

    public double getLatitud(){
        return latitud;
    }

    public double getLongitud(){
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
