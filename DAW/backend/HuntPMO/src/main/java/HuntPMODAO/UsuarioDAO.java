package HuntPMODAO;

import HuntPMOVO.UsuarioVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<UsuarioVO> obtenerUsuarios (Connection con){

        List<UsuarioVO> usuarios = new ArrayList<>();

        String consulta = "SELECT UsuarioID, nombreUser FROM Usuario";

        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioVO usuario = new UsuarioVO(
                        rs.getInt("UsuarioId"),
                        rs.getString("NombreUser")
                );

                usuarios.add(usuario);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
        }

    public UsuarioVO crearUsuario(Connection con, UsuarioVO usuario) throws SQLException {

        String insert = """
            INSERT INTO Usuario (NombreUser, Contrasenya, DNI)
            VALUES (?, ?, ?)
            """;

        try (PreparedStatement stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNombreUser());
            stmt.setString(2, usuario.getContrasenya());
            stmt.setString(3, usuario.getPersona().getDni());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet res = stmt.getGeneratedKeys()) {
                    if (res.next()) {
                        int usuarioId = res.getInt(1);

                        return new UsuarioVO(
                                usuarioId,
                                usuario.getNombreUser(),
                                usuario.getContrasenya(),
                                usuario.getPersona()
                        );
                    }
                }
            }
        }

        return null;
    }

    public boolean eliminarUsuario(Connection con, String nombreUser) {

        String delete = "DELETE FROM Usuario WHERE NombreUser = ?";

        try (PreparedStatement stmt = con.prepareStatement(delete)) {

            stmt.setString(1, nombreUser);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}

