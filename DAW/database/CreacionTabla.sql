CREATE DATABASE HuntPMO;

CREATE TABLE Persona (
    DNI varchar(9) PRIMARY KEY,
    Nombre varchar(50) NOT NULL,
    PrApellido varchar(50) NOT NULL,
    SgApellido varchar(50),
    Telefono varchar(15) NOT NULL,
    Email varchar(100) NOT NULL,
    NSS varchar(15) NOT NULL unique
);

CREATE TABLE Usuario (
    UsuarioId int AUTO_INCREMENT PRIMARY KEY,
    NombreUser varchar(50) NOT NULL,
    Domicilio varchar(100) NOT NULL,
    DNI varchar(9),
    CONSTRAINT fk_Persona
    FOREIGN KEY (DNI)
    REFERENCES Persona(DNI)
);

CREATE TABLE Agente (
    AgenteId  int AUTO_INCREMENT PRIMARY KEY,
    Mote varchar(50) NOT NULL,
    Rango int NOT NULL,
    Especialidad varchar(50) NOT NULL,
    DNI varchar(9),
    CONSTRAINT fk_PersonaA
    FOREIGN KEY (DNI)
    REFERENCES Persona(DNI)
);

CREATE TABLE Reporte (
    ReporteId int AUTO_INCREMENT PRIMARY KEY,
    Comentario TEXT NOT NULL,
    Fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    Estado varchar(50),
    UsuarioId int,
    CONSTRAINT fk_Usuario
    FOREIGN KEY (UsuarioId)
    REFERENCES Usuario(UsuarioId)
);

CREATE TABLE Instalacion (
    InstalacionId int AUTO_INCREMENT PRIMARY KEY,
    Nombre varchar(50) NOT NULL,
    Ubicacion varchar(100) NOT NULL,
    Seguridad int NOT NULL
);

CREATE TABLE Anomalia(
    AnomaliaId int AUTO_INCREMENT PRIMARY KEY,
    Codigo int NOT NULL,
    Nombre varchar (50) NOT NULL,
    Peligrosidad varchar (50) NOT NULL,
    Descripcion TEXT,
    InstalacionId int,
    CONSTRAINT fk_Instalacion
    FOREIGN KEY (InstalacionId)
    REFERENCES Instalacion(InstalacionId)
);

CREATE TABLE Rol (
    RolId int AUTO_INCREMENT PRIMARY KEY,
    Rol varchar (1) NOT NULL,
    Detalles varchar (255) NOT NULL,
    FechaComienzo DATETIME NOT NULL,
    FechaFinalización DATETIME,
    AgenteId int,
    CONSTRAINT fk_Agente
    FOREIGN KEY (AgenteId)
    REFERENCES Agente(AgenteId)
);

CREATE TABLE RolRevision (
    NuevoEstado varchar(50),
    ReporteId int,
    RolId int,
    CONSTRAINT fk_Reporte
    FOREIGN KEY (ReporteId)
    REFERENCES Reporte(ReporteId),
    CONSTRAINT fk_RolR
    FOREIGN KEY (RolId)
    REFERENCES Rol(RolId)
);

CREATE TABLE RolAsignacion (
    NuevaDescripcion TEXT NOT NULL,
    AnomaliaId int,
    RolId int,
    CONSTRAINT fk_Asignacion
    FOREIGN KEY (AnomaliaId)
    REFERENCES Anomalia(AnomaliaId),
    CONSTRAINT fk_RolA
    FOREIGN KEY (RolId)
    REFERENCES Rol(RolId)
);

CREATE TABLE ReportaAnomalia (
    Conclusiones varchar(255) NOT NULL,
    AnomaliaId int,
    ReporteId int,
    CONSTRAINT fk_ReporteR
    FOREIGN KEY (ReporteId)
    REFERENCES Reporte(ReporteId),
    CONSTRAINT fk_AsignacionR
    FOREIGN KEY (AnomaliaId)
    REFERENCES Anomalia(AnomaliaId)
);