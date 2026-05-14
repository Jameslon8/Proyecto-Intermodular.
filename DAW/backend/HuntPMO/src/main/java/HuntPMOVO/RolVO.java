package HuntPMOVO;

import java.time.LocalDateTime;

public class RolVO {
    private int rolId;
    private String rol;
    private String detalles;
    private LocalDateTime fechaComienzo;
    private LocalDateTime fechaFinalizacion;
    private int agenteId;

    public RolVO(int rolId, String rol, String detalles, LocalDateTime fechaComienzo,
                 LocalDateTime fechaFinalizacion, int agenteId) {
        this.rolId = rolId;
        this.rol = rol;
        this.detalles = detalles;
        this.fechaComienzo = fechaComienzo;
        this.fechaFinalizacion = fechaFinalizacion;
        this.agenteId = agenteId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public LocalDateTime getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(LocalDateTime fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public int getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(int agenteId) {
        this.agenteId = agenteId;
    }

    @Override
    public String toString() {
        return "RolVO{" +
                "rolId=" + rolId +
                ", rol='" + rol + '\'' +
                ", detalles='" + detalles + '\'' +
                ", fechaComienzo=" + fechaComienzo +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", agenteId=" + agenteId +
                '}';
    }
}
