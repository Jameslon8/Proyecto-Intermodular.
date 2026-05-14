package HuntPMOVO;

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

    @Override
    public String toString() {
        return "InstalacionVO{" +
                "instalacionId=" + instalacionId +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", seguridad=" + seguridad +
                '}';
    }
}
