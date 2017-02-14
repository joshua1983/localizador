package co.edu.unab.localizador.bus;

/**
 * Created by josue on 2/3/17.
 */

public class Ubicacion {
    private double latitud = 0.0;
    private double longitud = 0.0;

    public Ubicacion(double _latitud, double _longitud){
        this.setLatitud(_latitud);
        this.setLongitud(_longitud);
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
