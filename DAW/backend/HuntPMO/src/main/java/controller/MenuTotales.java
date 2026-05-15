package controller;

import HuntPMOVO.*;
import service.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MenuTotales {
    private Scanner sc = new Scanner(System.in);
    private UsuarioService usuarioService = new UsuarioService();
    private ReporteService reporteService = new ReporteService();
    private AgenteService agenteService = new AgenteService();
    private AnomaliaService anomaliaService = new AnomaliaService();
    private RolService rolService = new RolService();

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
                loginAgente();
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

    private void loginAgente() {
        System.out.print("Introduzca mote del agente: ");
        String mote = sc.nextLine();

        System.out.print("Introduzca contraseña: ");
        String contrasenya = sc.nextLine();

        AgenteVO agente = agenteService.verificarAgente(mote, contrasenya);

        if (agente != null) {
            System.out.println("Has iniciado sesión como agente.");
            System.out.println("Bienvenido, " + agente.getMote());

            menuAgente(agente);
        } else {
            System.out.println("Mote o contraseña incorrectos.");
        }
    }

    private void menuAgente(AgenteVO agente) {
        int opcion = -1;

        do {
            System.out.println("\n|=== MENU AGENTE ===|");
            System.out.println("Agente: " + agente.getMote());
            System.out.println("1. Ver mis datos");
            System.out.println("2. Gestionar usuarios");
            System.out.println("3. Ver reportes");
            System.out.println("4. Gestionar reportes");
            System.out.println("5. Ver anomalias");
            System.out.println("6. Gestionar anomalias");
            System.out.println("0. Cerrar sesion");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    verDatosAgente(agente);
                    break;

                case 2:
                    menuGestionUsuarios();
                    break;

                case 3:
                    menuVerReportes();
                    break;

                case 4:
                    menuGestionReportes(agente);
                    break;

                case 5:
                    menuVerAnomalias(agente);
                    break;

                case 6:
                    menuGestionAnomalias(agente);
                    break;

                case 7:
                    rolService.listarRoles();
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

    private void verDatosAgente(AgenteVO agente) {
        System.out.println("\n|=== MIS DATOS DE AGENTE ===|");

        System.out.println("ID agente: " + agente.getAgenteID());
        System.out.println("Mote: " + agente.getMote());
        System.out.println("Rango: " + agente.getRango());
        System.out.println("Especialidad: " + agente.getEspecialidad());

        PersonaVO persona = agente.getPersona();

        if (persona != null) {
            System.out.println("DNI: " + persona.getDni());
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Primer apellido: " + persona.getPrApellido());
            System.out.println("Segundo apellido: " + persona.getSgApellido());
            System.out.println("Domicilio: " + persona.getDomicilio());
            System.out.println("Telefono: " + persona.getTelefono());
            System.out.println("Email: " + persona.getEmail());
            System.out.println("NSS: " + persona.getNSS());
        }
    }

    private void menuGestionUsuarios(){
            int opcion = -1;

            do {
                System.out.println("\n|=== GESTIONAR USUARIOS ===|");
                System.out.println("1. Ver usuarios");
                System.out.println("2. Eliminar usuario");
                System.out.println("0. Volver");
                System.out.print("Opcion: ");

                opcion = Integer.parseInt(sc.nextLine());


                switch (opcion) {
                    case 1:
                        usuarioService.listarUsuarios();
                        break;

                    case 2:
                        eliminarUsuarioDesdeAgente();
                        break;

                    case 0:
                        System.out.println("Volviendo al menu agente...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

            } while (opcion != 0);
    }

    private void eliminarUsuarioDesdeAgente() {
        System.out.print("Nombre del usuario a eliminar: ");
        String nombreUser = sc.nextLine();

        boolean eliminado = usuarioService.eliminarUsuario(nombreUser);

        if (eliminado) {
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el usuario.");
        }
    }

    private void menuVerReportes() {
        int opcion = -1;

        do {
            System.out.println("\n|=== VER REPORTES ===|");
            System.out.println("1. Ver todos los reportes");
            System.out.println("2. Ver reportes con anomalias");
            System.out.println("3. Ver reportes sin anomalias");
            System.out.println("4. Ver reportes de un usuario");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    reporteService.listarReportes();
                    break;

                case 2:
                    reporteService.listarReportesConAnomalias();
                    break;

                case 3:
                    reporteService.listarReportesSinAnomalias();
                    break;

                case 4:
                    verReportesDeUsuarioComoAgente();
                    break;

                case 0:
                    System.out.println("Volviendo al menu agente...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void verReportesDeUsuarioComoAgente() {
        System.out.print("ID del usuario: ");
        int usuarioId = Integer.parseInt(sc.nextLine());

        reporteService.listarReportesPorUsuario(usuarioId);
    }

    private void menuGestionReportes(AgenteVO agente) {
        int opcion = -1;

        do {
            System.out.println("\n|=== GESTIONAR REPORTES ===|");
            System.out.println("1. Revisar reporte y cambiar estado");
            System.out.println("2. Relacionar reporte con anomalia");
            System.out.println("3. Ver revisiones de reportes");
            System.out.println("4. Ver reportes relacionados con anomalias");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    revisarReporteYCambiarEstado(agente);
                    break;

                case 2:
                    relacionarReporteConAnomalia();
                    break;

                case 3:
                    reporteService.listarRolesRevision();
                    break;

                case 4:
                    reporteService.listarReportaAnomalias();
                    break;

                case 0:
                    System.out.println("Volviendo al menu agente...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void revisarReporteYCambiarEstado(AgenteVO agente) {
        System.out.println("\n|=== REVISION DE REPORTE ===|");

        System.out.print("ID del reporte a revisar: ");
        int reporteId = Integer.parseInt(sc.nextLine());

        System.out.print("Detalles de la revision: ");
        String detalles = sc.nextLine();

        System.out.print("Nuevo estado del reporte: ");
        String nuevoEstado = sc.nextLine();

        RolVO rolCreado = rolService.crearRolRevision(detalles, agente.getAgenteID());

        if (rolCreado == null) {
            System.out.println("No se pudo crear el rol de revision.");
            return;
        }

        RolRevisionVO rolRevision = new RolRevisionVO(
                nuevoEstado,
                reporteId,
                rolCreado.getRolId()
        );

        boolean creado = reporteService.crearRolRevision(rolRevision);

        if (creado) {
            System.out.println("Revision creada y estado del reporte actualizado.");
        } else {
            System.out.println("No se pudo crear la revision.");
        }
    }

    private void relacionarReporteConAnomalia() {
        System.out.println("\n|=== RELACIONAR REPORTE CON ANOMALIA ===|");

        System.out.print("ID del reporte: ");
        int reporteId = Integer.parseInt(sc.nextLine());

        System.out.print("ID de la anomalia: ");
        int anomaliaId = Integer.parseInt(sc.nextLine());

        System.out.print("Conclusiones: ");
        String conclusiones = sc.nextLine();

        ReportaAnomaliaVO reportaAnomalia = new ReportaAnomaliaVO(
                conclusiones,
                anomaliaId,
                reporteId
        );

        boolean creado = reporteService.crearReportaAnomalia(reportaAnomalia);

        if (creado) {
            System.out.println("Reporte relacionado con anomalia correctamente.");
        } else {
            System.out.println("No se pudo relacionar el reporte con la anomalia.");
        }
    }


    private void menuVerAnomalias(AgenteVO agente) {
        int opcion = -1;

        do {
            System.out.println("\n|=== VER ANOMALIAS ===|");
            System.out.println("1. Ver todas las anomalias");
            System.out.println("2. Ver anomalias por peligrosidad");
            System.out.println("3. Ver anomalias por tipo");
            System.out.println("4. Ver anomalias por instalacion");
            System.out.println("5. Ver mis anomalias pendientes");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    anomaliaService.listarAnomalias();
                    break;

                case 2:
                    System.out.print("Nivel de peligrosidad: ");
                    int peligrosidad = Integer.parseInt(sc.nextLine());
                    anomaliaService.listarAnomaliasPorPeligrosidad(peligrosidad);
                    break;

                case 3:
                    System.out.print("Tipo de anomalia: ");
                    String tipo = sc.nextLine();
                    anomaliaService.listarAnomaliasPorTipo(tipo);
                    break;

                case 4:
                    System.out.print("ID de instalacion: ");
                    int instalacionId = Integer.parseInt(sc.nextLine());
                    anomaliaService.listarAnomaliasPorInstalacion(instalacionId);
                    break;

                case 5:
                    anomaliaService.listarMisAnomaliasPendientes(agente.getAgenteID());
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void menuGestionAnomalias(AgenteVO agente) {
        int opcion = -1;

        do {
            System.out.println("\n|=== GESTIONAR ANOMALIAS ===|");
            System.out.println("1. Empezar trabajo con anomalia");
            System.out.println("2. Finalizar trabajo y actualizar descripcion");
            System.out.println("3. Ver asignaciones");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    empezarTrabajoAnomalia(agente);
                    break;

                case 2:
                    finalizarTrabajoAnomalia();
                    break;

                case 3:
                    anomaliaService.listarRolesAsignacion();
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void empezarTrabajoAnomalia(AgenteVO agente) {
        System.out.print("ID de la anomalia: ");
        int anomaliaId = Integer.parseInt(sc.nextLine());

        System.out.print("Informacion sobre lo que va a hacer el agente: ");
        String detalles = sc.nextLine();

        System.out.print("Fecha finalizacion prevista, si no hay pulsa ENTER (ej: 2026-05-15 18:30): ");
        String fechaTexto = sc.nextLine();

        LocalDateTime fechaFinalizacion = null;

        if (!fechaTexto.trim().isEmpty()) {
            fechaFinalizacion = LocalDateTime.parse(fechaTexto.replace(" ", "T"));
        }

        RolVO rolCreado = anomaliaService.iniciarAsignacionAnomalia(
                anomaliaId,
                detalles,
                fechaFinalizacion,
                agente.getAgenteID()
        );

        if (rolCreado != null) {
            System.out.println("Trabajo iniciado correctamente.");
            System.out.println("ID del rol creado: " + rolCreado.getRolId());
        } else {
            System.out.println("No se pudo iniciar el trabajo.");
        }
    }

    private void finalizarTrabajoAnomalia() {
        System.out.print("ID de la anomalia: ");
        int anomaliaId = Integer.parseInt(sc.nextLine());

        System.out.print("ID del rol de asignacion: ");
        int rolId = Integer.parseInt(sc.nextLine());

        System.out.print("Informacion nueva para añadir a la descripcion: ");
        String textoAnyadido = sc.nextLine();

        boolean finalizado = anomaliaService.finalizarAsignacionAnomalia(
                anomaliaId,
                rolId,
                textoAnyadido
        );

        if (finalizado) {
            System.out.println("Trabajo finalizado y descripcion actualizada.");
        } else {
            System.out.println("No se pudo finalizar el trabajo.");
        }
    }

    private void accesoDirector() {
        System.out.print("Introduce la contrasenya de director: ");
        String password = sc.nextLine();

        if (password.equals(PasswordMaestra)) {
            menuDirector();
        } else {
            System.out.println("Contrasenya incorrecta.");
        }
    }

    private void menuDirector() {
        int opcion = -1;

        do {
            System.out.println("\n|=== MENU DIRECTOR ===|");
            System.out.println("1. Crear agente");
            System.out.println("2. Crear anomalia");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    crearAgenteDirector();
                    break;

                case 2:
                    crearAnomaliaDirector();
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void crearAgenteDirector() {
        System.out.println("\n|=== CREAR AGENTE ===|");

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

        System.out.print("Mote: ");
        String mote = sc.nextLine();

        System.out.print("Rango: ");
        int rango = Integer.parseInt(sc.nextLine());

        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();

        System.out.print("Contrasenya: ");
        String contrasenya = sc.nextLine();

        AgenteVO agente = new AgenteVO(
                mote,
                rango,
                especialidad,
                contrasenya,
                persona
        );

        boolean creado = agenteService.registrarAgente(persona, agente);

        if (creado) {
            System.out.println("Agente creado correctamente.");
        } else {
            System.out.println("No se pudo crear el agente.");
        }
    }

    private void crearAnomaliaDirector() {
        System.out.println("\n|=== CREAR ANOMALIA ===|");

        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(sc.nextLine());

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Peligrosidad: ");
        int peligrosidad = Integer.parseInt(sc.nextLine());

        System.out.print("Tipo: ");
        String tipo = sc.nextLine();

        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();

        System.out.print("ID instalacion: ");
        int instalacionId = Integer.parseInt(sc.nextLine());

        AnomaliaVO anomalia = new AnomaliaVO(
                codigo,
                nombre,
                peligrosidad,
                tipo,
                descripcion,
                instalacionId
        );

        boolean creada = anomaliaService.crearAnomalia(anomalia);

        if (creada) {
            System.out.println("Anomalia creada correctamente.");
        } else {
            System.out.println("No se pudo crear la anomalia.");
        }
    }
}
