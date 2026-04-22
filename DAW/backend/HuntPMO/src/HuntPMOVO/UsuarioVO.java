package HuntPMOVO;

public class UsuarioVO extends PersonaVO {
    private static int UsuarioID;
    private String nombreUser;
    private String domicilio;

    public UsuarioVO(String dni, String nombre, String prApellido, String sgApellido, String telefono, String email, String NSS, String nombreUser, String domicilio) {
        super(dni, nombre, prApellido, sgApellido, telefono, email, NSS);
        this.nombreUser = nombreUser;
        this.domicilio = domicilio;
    }

    public UsuarioVO(String dni, String nombre, String prApellido, String telefono, String email, String NSS, String nombreUser, String domicilio) {
        super(dni, nombre, prApellido, telefono, email, NSS);
        this.nombreUser = nombreUser;
        this.domicilio = domicilio;
    }

    public static int getUsuarioID() {
        return UsuarioID;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public static void setUsuarioID(int usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {

    }


}
