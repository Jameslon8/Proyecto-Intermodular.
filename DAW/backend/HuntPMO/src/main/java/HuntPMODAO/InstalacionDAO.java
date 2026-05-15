package HuntPMODAO;

import HuntPMOVO.InstalacionVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstalacionDAO {
    public List<InstalacionVO> obtenerInstalaciones(Connection con) {
        List<InstalacionVO> instalaciones = new ArrayList<>();

        String consulta = "SELECT InstalacionId, Nombre, Ubicacion, Seguridad FROM Instalacion";

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                InstalacionVO instalacion = new InstalacionVO(
                        rs.getInt("InstalacionId"),
                        rs.getString("Nombre"),
                        rs.getString("Ubicacion"),
                        rs.getInt("Seguridad")
                );
                instalaciones.add(instalacion);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener instalaciones: " + e.getMessage());
        }

        return instalaciones;
    }

    public InstalacionVO crearInstalacion(Connection con, InstalacionVO instalacion) throws SQLException {

        String insert = """
            INSERT INTO Instalacion (Nombre, Ubicacion, Seguridad)
            VALUES (?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, instalacion.getNombre());
            stmt.setString(2, instalacion.getUbicacion());
            stmt.setInt(3, instalacion.getSeguridad());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet res = stmt.getGeneratedKeys()) {
                    if (res.next()) {
                        int instalacionId = res.getInt(1);

                        return new InstalacionVO(
                                instalacionId,
                                instalacion.getNombre(),
                                instalacion.getUbicacion(),
                                instalacion.getSeguridad()
                        );
                    }
                }
            }
        }

        return null;
    }


}
