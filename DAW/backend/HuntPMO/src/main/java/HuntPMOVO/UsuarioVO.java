package HuntPMOVO;

public class UsuarioVO{
    private int UsuarioID;
    private String nombreUser;
    private String contrasenya;
    private PersonaVO persona;

    public UsuarioVO(int usuarioID, String nombreUser, String contrasenya, PersonaVO persona) {
        UsuarioID = usuarioID;
        this.nombreUser = nombreUser;
        this.contrasenya = contrasenya;
        this.persona = persona;
    }

    public UsuarioVO(int usuarioID, String nombreUser) {
        UsuarioID = usuarioID;
        this.nombreUser = nombreUser;
    }

    public int getUsuarioID() {
        return UsuarioID;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public PersonaVO getPersona() {
        return persona;
    }

    public void setPersona(PersonaVO persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" +
                "UsuarioID=" + UsuarioID +
                ", nombreUser='" + nombreUser + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", persona=" + persona +
                '}';
    }
}
