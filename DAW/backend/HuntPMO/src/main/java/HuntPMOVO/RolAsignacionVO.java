package HuntPMOVO;

public class RolAsignacionVO {
    private String nuevaDescripcion;
    private int anomaliaId;
    private int rolId;

    public RolAsignacionVO(String nuevaDescripcion, int anomaliaId, int rolId) {
        this.nuevaDescripcion = nuevaDescripcion;
        this.anomaliaId = anomaliaId;
        this.rolId = rolId;
    }

    public String getNuevaDescripcion() {
        return nuevaDescripcion;
    }

    public void setNuevaDescripcion(String nuevaDescripcion) {
        this.nuevaDescripcion = nuevaDescripcion;
    }

    public int getAnomaliaId() {
        return anomaliaId;
    }

    public void setAnomaliaId(int anomaliaId) {
        this.anomaliaId = anomaliaId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    @Override
    public String toString() {
        return "RolAsignacionVO{" +
                "nuevaDescripcion='" + nuevaDescripcion + '\'' +
                ", anomaliaId=" + anomaliaId +
                ", rolId=" + rolId +
                '}';
    }
}
