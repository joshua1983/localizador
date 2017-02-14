package co.edu.unab.localizador.bus;

import java.util.Date;

/**
 * Created by josue on 2/3/17.
 */

public class LogData {
    private String mensaje = "";
    private Date fecha;

    public LogData(String _mensaje){
        this.setMensaje(_mensaje);
        setFecha(new Date());
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
