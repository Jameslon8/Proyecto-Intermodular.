package controller;

import HuntPMOVO.PersonaVO;
import HuntPMOVO.ReporteVO;
import HuntPMOVO.UsuarioVO;
import service.ReporteService;
import service.UsuarioService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MenuTotales {
    private Scanner sc = new Scanner(System.in);
    private UsuarioService usuarioService = new UsuarioService();
    private ReporteService reporteService = new ReporteService();

    private static final String PasswordMaestra = "Contraseña";

    public void inicio(){
        int opcion = 0;

        do{
            System.out.println("\n|=== HUNTPMO ===|");
            System.out.println("Como desea entrar?");
            System.out.println("1. Usuario");
            System.out.println("2. Agente");
            System.out.println("3. Registrarse como usuario");
            System.out.println("4. Acceso director");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
        }while(opcion != 0);

        opcion = Integer.parseInt(sc.nextLine());

        switch (opcion) {
            case 1:
                loginUser();
                break;

            case 2:
                /*loginAgente();*/
                break;

            case 3:
                registrarUsuario();
                break;

            case 4:
                /*accesoDirector();*/
                break;

            case 0:
                System.out.println("Saliendo...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    }

    private void registrarUsuario() {
        System.out.println("\n|=== REGISTRO USUARIO ===|");
        System.out.println("DATOS PERSONALES:");

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Primer apellido: ");
        String prApellido = sc.nextLine();

        System.out.print("Segundo apellido, si no tiene dejalo vacio: ");
        String sgApellido = sc.nextLine();

        System.out.print("Domicilio: ");
        String domicilio = sc.nextLine();

        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("NSS: ");
        String nss = sc.nextLine();

        PersonaVO persona;

        if (sgApellido.equals("")) {
            persona = new PersonaVO(dni, nombre, prApellido, domicilio, telefono, email, nss);
        } else {
            persona = new PersonaVO(dni, nombre, prApellido, sgApellido, domicilio, telefono, email, nss);
        }

        System.out.println("DATOS USUARIO:");
        System.out.print("Nombre de usuario: ");
        String nombreUser = sc.nextLine();

        System.out.print("Contrasenya: ");
        String contrasenya = sc.nextLine();

        UsuarioVO usuario = new UsuarioVO(
                0,
                nombreUser,
                contrasenya,
                persona
        );

        boolean registrado = usuarioService.registrarUsuario(persona, usuario);

        if (registrado) {
            System.out.println("Usuario registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el usuario.");
        }
    }

    private void loginUser() {
        System.out.print("Introduzca nombre del usuario: ");
        String nombreUser = sc.nextLine();

        System.out.print("Introduzca contrasenya: ");
        String contrasenya = sc.nextLine();

        UsuarioVO usuario = usuarioService.verificarUsuario(nombreUser, contrasenya);

        if (usuario != null) {
            System.out.println("Has iniciado sesion correctamente.");
            System.out.println("Bienvenido, " + usuario.getNombreUser());

            menuUsuario(usuario);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private void menuUsuario(UsuarioVO usuario) {
        int opcion = 0;

        do {
            System.out.println("\n|=== MENU USUARIO ===|");
            System.out.println("Usuario: " + usuario.getNombreUser());
            System.out.println("1. Ver mis datos");
            System.out.println("2. Escribir reporte");
            System.out.println("3. Ver mis reportes");
            System.out.println("0. Cerrar sesion");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    /*verMisDatos(usuario);*/
                    break;
                case 2:
                    escribirReporte(usuario);
                    break;
                case 3:
                    System.out.println("Tus reportes: ");
                    reporteService.listarReportesPorUsuario(usuario.getUsuarioID());
                    break;
                case 0:
                    System.out.println("Sesion cerrada.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void verMisDatos(UsuarioVO usuario) {
        System.out.println("\n|=== MIS DATOS ===|");

        System.out.println("ID usuario: " + usuario.getUsuarioID());
        System.out.println("Nombre usuario: " + usuario.getNombreUser());

        if (usuario.getPersona() != null) {
            System.out.println("DNI: " + usuario.getPersona().getDni());
            System.out.println("Nombre: " + usuario.getPersona().getNombre());
            System.out.println("Primer apellido: " + usuario.getPersona().getPrApellido());
            System.out.println("Segundo apellido: " + usuario.getPersona().getSgApellido());
            System.out.println("Domicilio: " + usuario.getPersona().getDomicilio());
            System.out.println("Telefono: " + usuario.getPersona().getTelefono());
            System.out.println("Email: " + usuario.getPersona().getEmail());
            System.out.println("NSS: " + usuario.getPersona().getNSS());
        } else {
            System.out.println("No se han cargado los datos personales.");
        }
    }

    private void escribirReporte(UsuarioVO usuario) {
        System.out.println("\n|==== ESCRIBIR REPORTE ====|");

        System.out.print("Comentario: ");
        String comentario = sc.nextLine();

        ReporteVO reporte = new ReporteVO(
                comentario,
                LocalDateTime.now(),
                "Pendiente",
                usuario.getUsuarioID()
        );

        boolean verificar = reporteService.crearReporte(reporte);

        if (verificar) {
            System.out.println("Reporte creado correctamente.");
        } else {
            System.out.println("No se pudo crear el reporte.");
        }
    }

    private void verMisReportes(UsuarioVO usuario) {
        System.out.println("\n|=== MIS REPORTES ===|");
        reporteService.listarReportesPorUsuario(usuario.getUsuarioID());
    }
}
