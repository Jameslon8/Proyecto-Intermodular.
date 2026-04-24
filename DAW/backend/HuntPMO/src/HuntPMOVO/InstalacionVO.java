package HuntPMOVO;

import java.util.ArrayList;

public class InstalacionVO {
    private int instalacionId;
    private String nombre;
    private String ubicacion;
    private int seguridad;

    public InstalacionVO(int instalacionId, String nombre, String ubicacion, int seguridad) {
        this.instalacionId = instalacionId;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.seguridad = seguridad;
    }

    public int getInstalacionId() {
        return instalacionId;
    }

    public void setInstalacionId(int instalacionId) {
        this.instalacionId = instalacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(int seguridad) {
        this.seguridad = seguridad;
    }

    public ArrayList<AnomaliaVO> getAnomalias() {
        return anomalias;
    }

    public void setAnomalias(ArrayList<AnomaliaVO> anomalias) {
        this.anomalias = anomalias;
    }
}
