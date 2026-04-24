package HuntPMOVO;

import java.time.LocalDateTime;

public class RolVO {
    private int rolId;
    private char rol;
    private String detalles;
    private LocalDateTime fechaComienzo;
    private LocalDateTime fechaFinalizacion;

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public char getRol() {
        return rol;
    }

    public void setRol(char rol) {
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
}
