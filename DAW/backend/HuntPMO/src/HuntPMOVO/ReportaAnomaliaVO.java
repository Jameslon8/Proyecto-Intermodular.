package HuntPMOVO;

public class ReportaAnomaliaVO {
    private String anomalia;
    private ReporteVO reporte;
    private AnomaliaVO anomaliaVO;

    public ReportaAnomaliaVO(String anomalia, ReporteVO reporte, AnomaliaVO anomaliaVO) {
        this.anomalia = anomalia;
        this.reporte = reporte;
        this.anomaliaVO = anomaliaVO;
    }

    public String getAnomalia() {
        return anomalia;
    }

    public void setAnomalia(String anomalia) {
        this.anomalia = anomalia;
    }

    public ReporteVO getReporte() {
        return reporte;
    }

    public void setReporte(ReporteVO reporte) {
        this.reporte = reporte;
    }

    public AnomaliaVO getAnomaliaVO() {
        return anomaliaVO;
    }

    public void setAnomaliaVO(AnomaliaVO anomaliaVO) {
        this.anomaliaVO = anomaliaVO;
    }
}
