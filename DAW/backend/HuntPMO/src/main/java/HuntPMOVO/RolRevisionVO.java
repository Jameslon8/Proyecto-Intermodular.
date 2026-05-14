package HuntPMOVO;

public class RolRevisionVO {
    private String nuevoEstado;
    private int reporteId;
    private int rolId;

    public RolRevisionVO(String nuevoEstado, int reporteId, int rolId) {
        this.nuevoEstado = nuevoEstado;
        this.reporteId = reporteId;
        this.rolId = rolId;
    }

    public String getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    public int getReporteId() {
        return reporteId;
    }

    public void setReporteId(int reporteId) {
        this.reporteId = reporteId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    @Override
    public String toString() {
        return "RolRevisionVO{" +
                "nuevoEstado='" + nuevoEstado + '\'' +
                ", reporteId=" + reporteId +
                ", rolId=" + rolId +
                '}';
    }
}
