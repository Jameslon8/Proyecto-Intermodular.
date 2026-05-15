package HuntPMODAO;

import HuntPMOVO.ReporteVO;
import HuntPMOVO.RolRevisionVO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {
    public List<ReporteVO> obtenerReportes(Connection con) {
        List<ReporteVO> reportes = new ArrayList<>();

        String consulta = "SELECT ReporteId, Comentario, Fecha, Estado, UsuarioId FROM Reporte";

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReporteVO reporte = new ReporteVO(
                        rs.getInt("ReporteId"),
                        rs.getString("Comentario"),
                        rs.getTimestamp("Fecha").toLocalDateTime(),
                        rs.getString("Estado"),
                        rs.getInt("UsuarioId")
                );
                reportes.add(reporte);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener reportes: " + e.getMessage());
        }

        return reportes;
    }

    public List<ReporteVO> obtenerReportesPorUsuario(Connection con, int usuarioIdIntroducido) {

        List<ReporteVO> reportes = new ArrayList<>();

        String consulta = """
            SELECT ReporteId, Comentario, Fecha, Estado, UsuarioId 
            FROM Reporte
            WHERE UsuarioId = ?
            """;

        try (PreparedStatement stmt = con.prepareStatement(consulta)) {

            stmt.setInt(1, usuarioIdIntroducido);

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    int reporteId = resultado.getInt("ReporteId");
                    String comentario = resultado.getString("Comentario");
                    LocalDateTime fecha = resultado.getTimestamp("Fecha").toLocalDateTime();
                    String estado = resultado.getString("Estado");
                    int usuarioId = resultado.getInt("UsuarioId");

                    ReporteVO reporte = new ReporteVO(
                            reporteId,
                            comentario,
                            fecha,
                            estado,
                            usuarioId
                    );

                    reportes.add(reporte);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportes;
    }

    public List<ReporteVO> obtenerReportesConAnomalias(Connection con) {

        List<ReporteVO> reportes = new ArrayList<>();

        String consulta = """
            SELECT r.ReporteId, r.Comentario, r.Fecha, r.Estado, r.UsuarioId
            FROM Reporte r
            INNER JOIN ReportaAnomalia ra ON r.ReporteId = ra.ReporteId
            """;

        try (PreparedStatement stmt = con.prepareStatement(consulta);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                int reporteId = resultado.getInt("ReporteId");
                String comentario = resultado.getString("Comentario");
                LocalDateTime fecha = resultado.getTimestamp("Fecha").toLocalDateTime();
                String estado = resultado.getString("Estado");
                int usuarioId = resultado.getInt("UsuarioId");

                ReporteVO reporte = new ReporteVO(
                        reporteId,
                        comentario,
                        fecha,
                        estado,
                        usuarioId
                );

                reportes.add(reporte);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportes;
    }

    public List<ReporteVO> obtenerReportesSinAnomalias(Connection con) {

        List<ReporteVO> reportes = new ArrayList<>();

        String consulta = """
            SELECT r.ReporteId, r.Comentario, r.Fecha, r.Estado, r.UsuarioId
            FROM Reporte r
            LEFT JOIN ReportaAnomalia ra ON r.ReporteId = ra.ReporteId
            WHERE ra.AnomaliaId IS NULL
            """;

        try (PreparedStatement stmt = con.prepareStatement(consulta);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                int reporteId = resultado.getInt("ReporteId");
                String comentario = resultado.getString("Comentario");
                LocalDateTime fecha = resultado.getTimestamp("Fecha").toLocalDateTime();
                String estado = resultado.getString("Estado");
                int usuarioId = resultado.getInt("UsuarioId");

                ReporteVO reporte = new ReporteVO(
                        reporteId,
                        comentario,
                        fecha,
                        estado,
                        usuarioId
                );

                reportes.add(reporte);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportes;
    }


    public ReporteVO crearReporte(Connection con, ReporteVO reporte) throws SQLException {

        String insert = """
            INSERT INTO Reporte (Comentario, Fecha, Estado, UsuarioId)
            VALUES (?, ?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, reporte.getComentario());
            stmt.setTimestamp(2, Timestamp.valueOf(reporte.getFecha()));
            stmt.setString(3, reporte.getEstado());
            stmt.setInt(4, reporte.getUsuarioId());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet res = stmt.getGeneratedKeys()) {
                    if (res.next()) {
                        int reporteId = res.getInt(1);

                        return new ReporteVO(
                                reporteId,
                                reporte.getComentario(),
                                reporte.getFecha(),
                                reporte.getEstado(),
                                reporte.getUsuarioId()
                        );
                    }
                }
            }
        }
        return null;
    }

    public boolean actualizarEstadoReporteDesdeRolRevision(Connection con, RolRevisionVO rolRevision) {

        String update = """
            UPDATE Reporte r
            INNER JOIN RolRevision rr ON r.ReporteId = rr.ReporteId
            SET r.Estado = rr.NuevoEstado
            WHERE rr.ReporteId = ? AND rr.RolId = ?
            """;

        try (PreparedStatement stmt = con.prepareStatement(update)) {

            stmt.setInt(1, rolRevision.getReporteId());
            stmt.setInt(2, rolRevision.getRolId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
