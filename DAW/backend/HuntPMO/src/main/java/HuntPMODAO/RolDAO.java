package HuntPMODAO;

import HuntPMOVO.RolVO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    public List<RolVO> obtenerRoles(Connection con) {
        List<RolVO> roles = new ArrayList<>();

        String consulta = "SELECT RolId, Rol, Detalles, FechaComienzo, FechaFinalizacion, AgenteId FROM Rol";

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timestamp fechaFinalizacion = rs.getTimestamp("FechaFinalizacion");

                RolVO rol = new RolVO(
                        rs.getInt("RolId"),
                        rs.getString("Rol"),
                        rs.getString("Detalles"),
                        rs.getTimestamp("FechaComienzo").toLocalDateTime(),
                        fechaFinalizacion == null ? null : fechaFinalizacion.toLocalDateTime(),
                        rs.getInt("AgenteId")
                );
                roles.add(rol);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener roles: " + e.getMessage());
        }

        return roles;
    }

    public RolVO crearRol(Connection con, RolVO rol) throws SQLException {

        String insert = """
            INSERT INTO Rol (Rol, Detalles, FechaComienzo, FechaFinalizacion, AgenteId)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, rol.getRol());
            stmt.setString(2, rol.getDetalles());
            stmt.setTimestamp(3, Timestamp.valueOf(rol.getFechaComienzo()));

            if (rol.getFechaFinalizacion() == null) {
                stmt.setNull(4, Types.TIMESTAMP);
            } else {
                stmt.setTimestamp(4, Timestamp.valueOf(rol.getFechaFinalizacion()));
            }

            stmt.setInt(5, rol.getAgenteId());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet res = stmt.getGeneratedKeys()) {
                    if (res.next()) {
                        int rolId = res.getInt(1);

                        return new RolVO(
                                rolId,
                                rol.getRol(),
                                rol.getDetalles(),
                                rol.getFechaComienzo(),
                                rol.getFechaFinalizacion(),
                                rol.getAgenteId()
                        );
                    }
                }
            }
        }

        return null;
    }

    public boolean finalizarRol(Connection con, int rolId, LocalDateTime fechaFinalizacion) throws SQLException {
        String update = "UPDATE Rol SET FechaFinalizacion = ? WHERE RolId = ?";

        try (PreparedStatement stmt = con.prepareStatement(update)) {
            stmt.setTimestamp(1, Timestamp.valueOf(fechaFinalizacion));
            stmt.setInt(2, rolId);

            return stmt.executeUpdate() > 0;
        }
    }

}
