package HuntPMOVO;

public class AnomaliaVO {
    private int anomaliaId;
    private int codigo;
    private String nombre;
    private String peligrosidad;
    private String descripcion;
    private InstalacionVO instalacion;

    public AnomaliaVO(int anomaliaId, int codigo, String nombre, String peligrosidad, String descripcion) {
        this.anomaliaId = anomaliaId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.peligrosidad = peligrosidad;
        this.descripcion = descripcion;
    }

    public int getAnomaliaId() {
        return anomaliaId;
    }

    public void setAnomaliaId(int anomaliaId) {
        this.anomaliaId = anomaliaId;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(String peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
