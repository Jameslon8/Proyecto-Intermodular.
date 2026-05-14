package HuntPMOVO;

import java.time.LocalDateTime;

public class ReporteVO {
    private int reporteId;
    private String comentario;
    private LocalDateTime fecha;
    private String estado;
    private int usuarioId;

    public ReporteVO(int reporteId, String comentario, LocalDateTime fecha, String estado, int usuarioId) {
        this.reporteId = reporteId;
        this.comentario = comentario;
        this.fecha = fecha;
        this.estado = estado;
        this.usuarioId = usuarioId;
    }

    public int getReporteId() {
        return reporteId;
    }

    public void setReporteId(int reporteId) {
        this.reporteId = reporteId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "ReporteVO{" +
                "reporteId=" + reporteId +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
