package HuntPMOVO;

public class AnomaliaVO {
    private int anomaliaId;
    private int codigo;
    private String nombre;
    private int peligrosidad;
    private String tipo;
    private String descripcion;
    private int instalacionId;

    public AnomaliaVO(int anomaliaId, int codigo, String nombre, int peligrosidad,
                      String tipo, String descripcion, int instalacionId) {
        this.anomaliaId = anomaliaId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.peligrosidad = peligrosidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.instalacionId = instalacionId;
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

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getInstalacionId() {
        return instalacionId;
    }

    public void setInstalacionId(int instalacionId) {
        this.instalacionId = instalacionId;
    }

    @Override
    public String toString() {
        return "AnomaliaVO{" +
                "anomaliaId=" + anomaliaId +
                ", codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", peligrosidad=" + peligrosidad +
                ", tipo='" + tipo + '\'' +
                ", instalacionId=" + instalacionId +
                '}';
    }
}
