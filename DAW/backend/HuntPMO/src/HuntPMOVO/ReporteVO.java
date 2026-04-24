package HuntPMOVO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReporteVO {
    private int idReporte;
    private String Comentario;
    private LocalDateTime fecha;
    private Enum estado;
    private UsuarioVO usuario;

    public ReporteVO(int idReporte, String comentario, LocalDateTime fecha, Enum estado, UsuarioVO usuario) {
        this.idReporte = idReporte;
        Comentario = comentario;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Enum getEstado() {
        return estado;
    }

    public void setEstado(Enum estado) {
        this.estado = estado;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }
}
