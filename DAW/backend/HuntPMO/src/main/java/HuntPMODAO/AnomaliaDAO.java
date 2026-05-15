package HuntPMODAO;

import HuntPMOVO.AnomaliaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
