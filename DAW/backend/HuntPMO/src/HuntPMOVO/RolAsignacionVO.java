package HuntPMOVO;

public class RolAsignacionVO {
    private String nuevaDescripcion;
    private RolVO rol;
    private AnomaliaVO anomalia;

    public RolAsignacionVO(String nuevaDescripcion, RolVO rol, AnomaliaVO anomalia) {
        this.nuevaDescripcion = nuevaDescripcion;
        this.rol = rol;
        this.anomalia = anomalia;
    }

    public String getNuevaDescripcion() {
        return nuevaDescripcion;
    }

    public void setNuevaDescripcion(String nuevaDescripcion) {
        this.nuevaDescripcion = nuevaDescripcion;
    }

    public RolVO getRol() {
        return rol;
    }

    public void setRol(RolVO rol) {
        this.rol = rol;
    }

    public AnomaliaVO getAnomalia() {
        return anomalia;
    }

    public void setAnomalia(AnomaliaVO anomalia) {
        this.anomalia = anomalia;
    }
}
