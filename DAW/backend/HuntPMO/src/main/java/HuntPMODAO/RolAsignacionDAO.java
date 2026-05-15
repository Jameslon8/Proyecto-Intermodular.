package HuntPMODAO;

import HuntPMOVO.RolAsignacionVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolAsignacionDAO {
    public List<RolAsignacionVO> obtenerRolesAsignacion(Connection con) {
        List<RolAsignacionVO> rolesAsignacion = new ArrayList<>();

        String consulta = "SELECT NuevaDescripcion, AnomaliaId, RolId FROM RolAsignacion";

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RolAsignacionVO rolAsignacion = new RolAsignacionVO(
                        rs.getString("NuevaDescripcion"),
                        rs.getInt("AnomaliaId"),
                        rs.getInt("RolId")
                );
                rolesAsignacion.add(rolAsignacion);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener asignaciones de rol: " + e.getMessage());
        }

        return rolesAsignacion;
    }

    public boolean crearRolAsignacion(Connection con, RolAsignacionVO rolAsignacion) throws SQLException {
        String insert = """
            INSERT INTO RolAsignacion (NuevaDescripcion, AnomaliaId, RolId)
            VALUES (?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert)) {
            stmt.setString(1, rolAsignacion.getNuevaDescripcion());
            stmt.setInt(2, rolAsignacion.getAnomaliaId());
            stmt.setInt(3, rolAsignacion.getRolId());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean actualizarDescripcionAnomaliaDesdeRolAsignacion(Connection con, RolAsignacionVO rolAsignacion) {

        String update = """
            UPDATE Anomalia a
            INNER JOIN RolAsignacion ra ON a.AnomaliaId = ra.AnomaliaId
            SET a.Descripcion = ra.NuevaDescripcion
            WHERE ra.AnomaliaId = ? AND ra.RolId = ?
            """;

        try (PreparedStatement stmt = con.prepareStatement(update)) {

            stmt.setInt(1, rolAsignacion.getAnomaliaId());
            stmt.setInt(2, rolAsignacion.getRolId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarNuevaDescripcion(Connection con, RolAsignacionVO rolAsignacion) throws SQLException {
        String update = """
            UPDATE RolAsignacion
            SET NuevaDescripcion = ?
            WHERE AnomaliaId = ? AND RolId = ?
            """;

        try (PreparedStatement stmt = con.prepareStatement(update)) {
            stmt.setString(1, rolAsignacion.getNuevaDescripcion());
            stmt.setInt(2, rolAsignacion.getAnomaliaId());
            stmt.setInt(3, rolAsignacion.getRolId());

            return stmt.executeUpdate() > 0;
        }
    }

}
