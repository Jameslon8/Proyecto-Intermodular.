package HuntPMOVO;

public class UsuarioVO{
    private int usuarioID;
    private String nombreUser;
    private String contrasenya;
    private PersonaVO persona;

    public UsuarioVO(int usuarioID, String nombreUser, String contrasenya, PersonaVO persona) {
        this.usuarioID = usuarioID;
        this.nombreUser = nombreUser;
        this.contrasenya = contrasenya;
        this.persona = persona;
    }

    public UsuarioVO(String nombreUser, String contrasenya, PersonaVO persona) {
        this.nombreUser = nombreUser;
        this.contrasenya = contrasenya;
        this.persona = persona;
    }


    public UsuarioVO(int usuarioID, String nombreUser) {
        this.usuarioID = usuarioID;
        this.nombreUser = nombreUser;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public void setUsuarioID(int usuarioID) {
        usuarioID = usuarioID;
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
                "UsuarioID=" + usuarioID +
                ", nombreUser='" + nombreUser + '\'' +
                ", persona=" + persona +
                '}';
    }
}
