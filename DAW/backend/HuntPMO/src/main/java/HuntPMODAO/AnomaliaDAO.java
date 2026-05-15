package HuntPMODAO;

import HuntPMOVO.AnomaliaVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnomaliaDAO {
    public List<AnomaliaVO> obtenerAnomalias(Connection con) {
        List<AnomaliaVO> anomalias = new ArrayList<>();

        String consulta = "SELECT AnomaliaId, Codigo, Nombre, Peligrosidad, Tipo, Descripcion, InstalacionId FROM Anomalia";

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AnomaliaVO anomalia = new AnomaliaVO(
                        rs.getInt("AnomaliaId"),
                        rs.getInt("Codigo"),
                        rs.getString("Nombre"),
                        rs.getInt("Peligrosidad"),
                        rs.getString("Tipo"),
                        rs.getString("Descripcion"),
                        rs.getInt("InstalacionId")
                );
                anomalias.add(anomalia);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener anomalias: " + e.getMessage());
        }

        return anomalias;
    }

    public List<AnomaliaVO> obtenerAnomaliasPorInstalacion(Connection con, int instalacionIdIntroducida) {

        List<AnomaliaVO> anomalias = new ArrayList<>();

        String consulta = """
            SELECT AnomaliaId, Codigo, Nombre, Peligrosidad, Tipo, Descripcion, InstalacionId
            FROM Anomalia
            WHERE InstalacionId = ?
            """;

        try (PreparedStatement stmt = con.prepareStatement(consulta)) {

            stmt.setInt(1, instalacionIdIntroducida);

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    int anomaliaId = resultado.getInt("AnomaliaId");
                    int codigo = resultado.getInt("Codigo");
                    String nombre = resultado.getString("Nombre");
                    int peligrosidad = resultado.getInt("Peligrosidad");
                    String tipo = resultado.getString("Tipo");
                    String descripcion = resultado.getString("Descripcion");
                    int instalacionId = resultado.getInt("InstalacionId");

                    AnomaliaVO anomalia = new AnomaliaVO(
                            anomaliaId,
                            codigo,
                            nombre,
                            peligrosidad,
                            tipo,
                            descripcion,
                            instalacionId
                    );

                    anomalias.add(anomalia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anomalias;
    }

    public AnomaliaVO crearAnomalia(Connection con, AnomaliaVO anomalia) throws SQLException {
        String insert = """
            INSERT INTO Anomalia (Codigo, Nombre, Peligrosidad, Tipo, Descripcion, InstalacionId)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, anomalia.getCodigo());
            stmt.setString(2, anomalia.getNombre());
            stmt.setInt(3, anomalia.getPeligrosidad());
            stmt.setString(4, anomalia.getTipo());
            stmt.setString(5, anomalia.getDescripcion());
            stmt.setInt(6, anomalia.getInstalacionId());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet res = stmt.getGeneratedKeys()) {
                    if (res.next()) {
                        int anomaliaId = res.getInt(1);

                        return new AnomaliaVO(
                                anomaliaId,
                                anomalia.getCodigo(),
                                anomalia.getNombre(),
                                anomalia.getPeligrosidad(),
                                anomalia.getTipo(),
                                anomalia.getDescripcion(),
                                anomalia.getInstalacionId()
                        );
                    }
                }
            }
        }

        return null;
    }


    public List<AnomaliaVO> obtenerAnomaliasPorPeligrosidad(Connection con, int peligrosidadIntroducida) {

        List<AnomaliaVO> anomalias = new ArrayList<>();

        String consulta = """
        SELECT AnomaliaId, Codigo, Nombre, Peligrosidad, Tipo, Descripcion, InstalacionId
        FROM Anomalia
        WHERE Peligrosidad = ?
        """;

        try (PreparedStatement stmt = con.prepareStatement(consulta)) {

            stmt.setInt(1, peligrosidadIntroducida);

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    AnomaliaVO anomalia = new AnomaliaVO(
                            resultado.getInt("AnomaliaId"),
                            resultado.getInt("Codigo"),
                            resultado.getString("Nombre"),
                            resultado.getInt("Peligrosidad"),
                            resultado.getString("Tipo"),
                            resultado.getString("Descripcion"),
                            resultado.getInt("InstalacionId")
                    );

                    anomalias.add(anomalia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anomalias;
    }

    public List<AnomaliaVO> obtenerAnomaliasPorTipo(Connection con, String tipoIntroducido) {

        List<AnomaliaVO> anomalias = new ArrayList<>();

        String consulta = """
        SELECT AnomaliaId, Codigo, Nombre, Peligrosidad, Tipo, Descripcion, InstalacionId
        FROM Anomalia
        WHERE Tipo = ?
        """;

        try (PreparedStatement stmt = con.prepareStatement(consulta)) {

            stmt.setString(1, tipoIntroducido);

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    AnomaliaVO anomalia = new AnomaliaVO(
                            resultado.getInt("AnomaliaId"),
                            resultado.getInt("Codigo"),
                            resultado.getString("Nombre"),
                            resultado.getInt("Peligrosidad"),
                            resultado.getString("Tipo"),
                            resultado.getString("Descripcion"),
                            resultado.getInt("InstalacionId")
                    );

                    anomalias.add(anomalia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anomalias;
    }

    public String obtenerDescripcionAnomalia(Connection con, int anomaliaId) {
        String consulta = "SELECT Descripcion FROM Anomalia WHERE AnomaliaId = ?";

        try (PreparedStatement stmt = con.prepareStatement(consulta)) {
            stmt.setInt(1, anomaliaId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Descripcion");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<AnomaliaVO> obtenerAnomaliasPendientesPorAgente(Connection con, int agenteIdIntroducido) {
        List<AnomaliaVO> anomalias = new ArrayList<>();

        String consulta = """
        SELECT a.AnomaliaId, a.Codigo, a.Nombre, a.Peligrosidad, a.Tipo, a.Descripcion, a.InstalacionId
        FROM Anomalia a
        INNER JOIN RolAsignacion ra ON a.AnomaliaId = ra.AnomaliaId
        INNER JOIN Rol r ON ra.RolId = r.RolId
        WHERE r.AgenteId = ? AND r.Rol = 'A' AND r.FechaFinalizacion IS NULL
        """;

        try (PreparedStatement stmt = con.prepareStatement(consulta)) {
            stmt.setInt(1, agenteIdIntroducido);

            try (ResultSet resultado = stmt.executeQuery()) {
                while (resultado.next()) {
                    AnomaliaVO anomalia = new AnomaliaVO(
                            resultado.getInt("AnomaliaId"),
                            resultado.getInt("Codigo"),
                            resultado.getString("Nombre"),
                            resultado.getInt("Peligrosidad"),
                            resultado.getString("Tipo"),
                            resultado.getString("Descripcion"),
                            resultado.getInt("InstalacionId")
                    );

                    anomalias.add(anomalia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anomalias;
    }


}
