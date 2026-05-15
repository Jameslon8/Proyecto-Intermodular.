package HuntPMODAO;


import HuntPMOVO.ReportaAnomaliaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReportaAnomaliaDAO {

    public List<ReportaAnomaliaVO> obtenerReportaAnomalias(Connection con) {
        List<ReportaAnomaliaVO> reportaAnomalias = new ArrayList<>();

        String consulta = "SELECT Conclusiones, AnomaliaId, ReporteId FROM ReportaAnomalia";

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReportaAnomaliaVO reportaAnomalia = new ReportaAnomaliaVO(
                        rs.getString("Conclusiones"),
                        rs.getInt("AnomaliaId"),
                        rs.getInt("ReporteId")
                );
                reportaAnomalias.add(reportaAnomalia);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener reportes de anomalia: " + e.getMessage());
        }

        return reportaAnomalias;
    }

    public boolean crearReportaAnomalia(Connection con, ReportaAnomaliaVO reportaAnomalia) throws SQLException {
        String insert = """
            INSERT INTO ReportaAnomalia (Conclusiones, AnomaliaId, ReporteId)
            VALUES (?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert)) {
            stmt.setString(1, reportaAnomalia.getConclusiones());
            stmt.setInt(2, reportaAnomalia.getAnomaliaId());
            stmt.setInt(3, reportaAnomalia.getReporteId());

            return stmt.executeUpdate() > 0;
        }
    }
}
