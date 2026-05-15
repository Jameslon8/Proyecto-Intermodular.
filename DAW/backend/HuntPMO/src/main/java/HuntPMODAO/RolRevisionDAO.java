package HuntPMODAO;

import HuntPMOVO.RolRevisionVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolRevisionDAO {
    public List<RolRevisionVO> obtenerRolesRevision(Connection con) {
        List<RolRevisionVO> rolesRevision = new ArrayList<>();

        String consulta = "SELECT NuevoEstado, ReporteId, RolId FROM RolRevision";

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RolRevisionVO rolRevision = new RolRevisionVO(
                        rs.getString("NuevoEstado"),
                        rs.getInt("ReporteId"),
                        rs.getInt("RolId")
                );
                rolesRevision.add(rolRevision);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener revisiones de rol: " + e.getMessage());
        }

        return rolesRevision;
    }

    public boolean crearRolRevision(Connection con, RolRevisionVO rolRevision) throws SQLException {
        String insert = """
            INSERT INTO RolRevision (NuevoEstado, ReporteId, RolId)
            VALUES (?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert)) {
            stmt.setString(1, rolRevision.getNuevoEstado());
            stmt.setInt(2, rolRevision.getReporteId());
            stmt.setInt(3, rolRevision.getRolId());

            return stmt.executeUpdate() > 0;
        }
    }

}
