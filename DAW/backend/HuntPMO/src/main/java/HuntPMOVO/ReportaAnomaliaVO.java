package HuntPMOVO;

public class ReportaAnomaliaVO {
    private String conclusiones;
    private int anomaliaId;
    private int reporteId;

    public ReportaAnomaliaVO(String conclusiones, int anomaliaId, int reporteId) {
        this.conclusiones = conclusiones;
        this.anomaliaId = anomaliaId;
        this.reporteId = reporteId;
    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

    public int getAnomaliaId() {
        return anomaliaId;
    }

    public void setAnomaliaId(int anomaliaId) {
        this.anomaliaId = anomaliaId;
    }

    public int getReporteId() {
        return reporteId;
    }

    public void setReporteId(int reporteId) {
        this.reporteId = reporteId;
    }

    @Override
    public String toString() {
        return "ReportaAnomaliaVO{" +
                "conclusiones='" + conclusiones + '\'' +
                ", anomaliaId=" + anomaliaId +
                ", reporteId=" + reporteId +
                '}';
    }
}
