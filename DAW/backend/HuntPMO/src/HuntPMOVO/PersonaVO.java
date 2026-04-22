package HuntPMOVO;

public class PersonaVO {
    private String dni;
    private String nombre;
    private String prApellido;
    private String sgApellido;
    private String telefono;
    private String email;
    private String NSS;

    public PersonaVO(String dni, String nombre, String prApellido, String sgApellido, String telefono, String email, String NSS) {
        this.dni = dni;
        this.nombre = nombre;
        this.prApellido = prApellido;
        this.sgApellido = sgApellido;
        this.telefono = telefono;
        this.email = email;
        this.NSS = NSS;
    }

    public PersonaVO(String dni, String nombre, String prApellido, String telefono, String email, String NSS) {
        this.dni = dni;
        this.nombre = nombre;
        this.prApellido = prApellido;
        this.telefono = telefono;
        this.email = email;
        this.NSS = NSS;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrApellido() {
        return prApellido;
    }

    public void setPrApellido(String prApellido) {
        this.prApellido = prApellido;
    }

    public String getSgApellido() {
        return sgApellido;
    }

    public void setSgApellido(String sgApellido) {
        this.sgApellido = sgApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String NSS) {
        this.NSS = NSS;
    }


}
