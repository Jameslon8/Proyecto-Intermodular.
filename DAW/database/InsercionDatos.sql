INSERT INTO Persona (DNI, Nombre, PrApellido, SgApellido, Telefono, Email, NSS)
VALUES ('48275931G', 'Alexis', 'Nessie', 'Johnson', '612345789', 'Ness2342@gmail.com', '281234567890'),
('72649183H', 'James', 'Logan', NULL, '678912345', 'longesd29@gmail.com', '301987654321'),
('19384756T', 'Mock', 'Nessie', 'Johnson', '623456781', 'Mock2342@gmail.com', '150456789123'),
('05837462M', 'Astrid', 'Williams', 'Mertifur', '655789123', 'MercuryyyyyQ9@gmail.com', '421789456032'),
('91726384Q', 'Jenn', 'Nevermind', 'Hollow', '644556677', 'TheDestroyerASMGF@gmail.com', '081234987654'),
('36482915L', 'Marcial', 'Line', NULL, '677889900', 'Marcial76786@gmail.com', '361258963147'),
('27591846Z', 'Alon', 'Marinee', 'Dafoe', '622334455', '99Raven99@gmail.com', '200147258369'),
('64018392S', 'Chris', 'Fernandez', NULL, '611987654', 'SoyYomismoSisime@gmail.com', '471369258014'),
('81927365B', 'Standford', 'Williams', 'Jerinfors', '634567892', 'diaryNNNumber4@gmail.com', '091753486201'),
('50372691R', 'Paco', 'Gustavez', 'Cordonez', '699123456', 'The5thrue0ne@gmail.com', '331468275903');

INSERT INTO Usuario(NombreUser, Domicilio, DNI)
VALUES ('TheJenn', 'Nevermind Street 44', '91726384Q'),
('Jameslon', 'Niohigi Street 31', '64018392S'),
('Marceline', 'Adevnture Street 44', '36482915L'),
('Raven', 'Down Niohigi Street 81', '27591846Z'),
('Longesd342', 'Nevermind Street 22', '72649183H');

INSERT INTO Agente (Mote, Rango, Especialidad, DNI)
VALUES ('Ness1', '3', 'Anomalias digitales', '48275931G'),
('Ness2', '4', 'Anomalias aquaticas', '19384756T'),
('Morequier', '4', 'Anomalias locales', '05837462M'),
('TheCreator', '5', 'Anomalias urbanas', '81927365B'),
('Mex', '2', 'Anomalias lingüisticas', '50372691R');

INSERT INTO Reporte (Comentario, Fecha, Estado, UsuarioId)
VALUES ("He comprado un juguete para mi hijo y vienen cosas bastante raras", '2020-03-15 14:30:00', 'Examinado', 6);

INSERT INTO Instalacion (Nombre, Ubicacion, Seguridad)
VALUES ("Sitio 9B", "Alaska", 4),
("Sitio 7G", "Mexico", 2),
("Sitio 1Z", "España", 3),
("Sitio 0Y", "España", 5);

INSERT INTO Anomalia (Codigo, Nombre, Peligrosidad, Descripcion, Instalacion)
VALUES (0432, "Kit de supervivencia zombie", 2, "Kit de supervivencia para niños creado en los años 90 por Prepare Entertaimenº (No se han 
encontrado registros de que tal compañia extista). Los propiedades anomalas de este objeto son principalmente con su contenido.
El kit contiene objetos para defensa personal como armas y pistolas funcionales, jeringuillas para curar estados de parisitación (Totalmente funcionales)
,set medico y una radio con retransmisiones habituales con un futuro apocaliptico. Las veces que se ha intentado registrar de donde proviene
la señal han sido en vano.", 3),
(0333, "Monsbear", 2, "Oso pardo con pulsaciones al ritmo de la canción Monster de Skillex. Las pulsaciones del corazón palpitan al ritmo exacto
de la canción incluso con efectos de sonidos ocasionales. Una vez que termine la canción se detiene por un mili segundo y vuelve a sonar desde el principio", 2),
(2351, "Sujetos llamados []", 3, "Esta anomalia ocurre tras poner legalmente el nombre de [borrado] a un recien nacido en la forma que se haga en cada pais.
Tras que el bebe sea nombrado pasara a ser parte de la anomalia y cambiara su conducta totalmente. Datos historicos registran que todas las personas que han
sido llamadas [datos borrados] forman parte de un culto que pasa entre generaciones con el objetivo de crear un arma definitiva la cual se desconoce su funcion. 
 Todo intento de construir el
arma ha sido detenido con exito.", 4),
(0007, "Hombre lobo normal y corriente", 2, "Es un sujeto baron de 21 años llamado", 1),
(1129, "Almacen mobilistico", 2, "yet", 3),
(0173, "La estatua", 2, "", 3),
(5546, "Nessie", 2, "", 3),
(0065, "El cache", 2, "", 3),
(1345, "Acontecimientos catastróficos", 2, "", 3);

INSERT INTO Rol (Rol, Detalles, FechaComienzo, FechaFinalización, AgenteId)
VALUES ("R", "Reporte revisado y se detecto anomalia. Se llevaran a cabo recolecciones de todo producto en todas las tiendas locales de la zona.", "2020-03-15 15:30:00",
 "2020-03-15 18:45:10", 3)
 ("A", "Recolección de todo kit titulado 'Kit de supervivencia zombie' en un area de 900 km2 de donde ocurrio el reporte.", "2020-03-15 20:00:00",
 "2020-03-16 01:45:10", 3);

INSERT INTO RolRevision (NuevoEstado, ReporteId, RoldId)
VALUES("Examinado", 1, 1);

INSERT INTO RolAsignacion(NuevaDescripcion, AnomaliaId, RoldId)
VALUES ("Kit de supervivencia para niños creado en los años 90 por Prepare Entertaimenº (No se han 
encontrado registros de que tal compañia extista). Los propiedades anomalas de este objeto son principalmente con su contenido.
El kit contiene objetos para defensa personal como armas y pistolas funcionales, jeringuillas para curar estados de parisitación (Totalmente funcionales)
,set medico y una radio con retransmisiones habituales con un futuro apocaliptico.", 1, 2);

INSERT VALUES ReportaAnomalia (Conclusiones, ReporteId, AnomaliaId)
VALUES ("Detectada anomalia relacionada con el caso de la señora que compro el kit", 1, 1);