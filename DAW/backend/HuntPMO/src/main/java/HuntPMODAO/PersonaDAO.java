package HuntPMODAO;

import HuntPMOVO.PersonaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public List<PersonaVO> obtenerPersonas(Connection con) {
        List<PersonaVO> personas = new ArrayList<>();

        String consulta = "SELECT DNI, Nombre, PrApellido, SgApellido, Domicilio, Telefono, Email, NSS FROM Persona";

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
                personas.add(persona);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener personas: " + e.getMessage());
        }

        return personas;
    }

    public boolean insertarPersona(Connection con, PersonaVO persona) {
        String sql = """
            INSERT INTO Persona (DNI, Nombre, PrApellido, SgApellido, Domicilio, Telefono, Email, NSS)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, persona.getDni());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getPrApellido());
            if (persona.getSgApellido() == null || persona.getSgApellido().isBlank()) {
                ps.setNull(4, java.sql.Types.VARCHAR);
            } else {
                ps.setString(4, persona.getSgApellido());
            }
            ps.setString(5, persona.getDomicilio());
            ps.setString(6, persona.getTelefono());
            ps.setString(7, persona.getEmail());
            ps.setString(8, persona.getNSS());

            ps.executeUpdate();
            ps.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
