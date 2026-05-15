package HuntPMODAO;

import HuntPMOVO.AgenteVO;
import HuntPMOVO.PersonaVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenteDAO {
    public List<AgenteVO> obtenerAgentes(Connection con) {
        List<AgenteVO> agentes = new ArrayList<>();

        String consulta = """
            SELECT a.AgenteId, a.Mote, a.Rango, a.Especialidad, a.Contrasenya,
                p.DNI, p.Nombre, p.PrApellido, p.SgApellido, p.Domicilio, p.Telefono, p.Email, p.NSS
            FROM Agente a
            INNER JOIN Persona p ON a.DNI = p.DNI
            """;

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PersonaVO persona = new PersonaVO(
                        rs.getString("DNI"),
                        rs.getString("Nombre"),
                        rs.getString("PrApellido"),
                        rs.getString("SgApellido"),
                        rs.getString("Domicilio"),
                        rs.getString("Telefono"),
                        rs.getString("Email"),
                        rs.getString("NSS")
                );

                AgenteVO agente = new AgenteVO(
                        rs.getInt("AgenteId"),
                        rs.getString("Mote"),
                        rs.getInt("Rango"),
                        rs.getString("Especialidad"),
                        rs.getString("Contrasenya"),
                        persona
                );

                agentes.add(agente);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener agentes: " + e.getMessage());
        }

        return agentes;
    }

    public AgenteVO crearAgente(Connection con, AgenteVO agente) throws SQLException {

        String insert = """
            INSERT INTO Agente (Mote, Rango, Especialidad, Contrasenya, DNI)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, agente.getMote());
            stmt.setInt(2, agente.getRango());
            stmt.setString(3, agente.getEspecialidad());
            stmt.setString(4, agente.getContrasenya());
            stmt.setString(5, agente.getPersona().getDni());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet res = stmt.getGeneratedKeys()) {
                    if (res.next()) {
                        int agenteId = res.getInt(1);

                        return new AgenteVO(
                                agenteId,
                                agente.getMote(),
                                agente.getRango(),
                                agente.getEspecialidad(),
                                agente.getContrasenya(),
                                agente.getPersona()
                        );
                    }
                }
            }
        }
        return null;
    }

    public boolean eliminarAgente(Connection con, String mote) {

        String delete = "DELETE FROM Agente WHERE Mote = ?";

        try (PreparedStatement stmt = con.prepareStatement(delete)) {

            stmt.setString(1, mote);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AgenteVO verificarAgente(Connection con, String mote, String contrasenya) {

        String consulta = """
            SELECT a.AgenteId, a.Mote, a.Rango, a.Especialidad, a.Contrasenya,
                   p.DNI, p.Nombre, p.PrApellido, p.SgApellido,
                   p.Domicilio, p.Telefono, p.Email, p.NSS
            FROM Agente a
            INNER JOIN Persona p ON a.DNI = p.DNI
            WHERE a.Mote = ? AND a.Contrasenya = ?
            """;

        try (PreparedStatement stmt = con.prepareStatement(consulta)) {

            stmt.setString(1, mote);
            stmt.setString(2, contrasenya);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PersonaVO persona = new PersonaVO(
                        rs.getString("DNI"),
                        rs.getString("Nombre"),
                        rs.getString("PrApellido"),
                        rs.getString("SgApellido"),
                        rs.getString("Domicilio"),
                        rs.getString("Telefono"),
                        rs.getString("Email"),
                        rs.getString("NSS")
                );

                return new AgenteVO(
                        rs.getInt("AgenteId"),
                        rs.getString("Mote"),
                        rs.getInt("Rango"),
                        rs.getString("Especialidad"),
                        rs.getString("Contrasenya"),
                        persona
                );
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
