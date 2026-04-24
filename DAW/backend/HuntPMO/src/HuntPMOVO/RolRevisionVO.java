package HuntPMOVO;

public class RolRevisionVO {
    private String nuevoEstado;
    private RolVO rol;
    private ReporteVO reporte;

    public RolRevisionVO(String nuevoEstado, RolVO rol, ReporteVO reporte) {
        this.nuevoEstado = nuevoEstado;
        this.rol = rol;
        this.reporte = reporte;
    }

    public String getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    public RolVO getRol() {
        return rol;
    }

    public void setRol(RolVO rol) {
        this.rol = rol;
    }

    public ReporteVO getReporte() {
        return reporte;
    }

    public void setReporte(ReporteVO reporte) {
        this.reporte = reporte;
    }
}
