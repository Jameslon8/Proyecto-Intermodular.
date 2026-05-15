--Select para ver todos los reportes de un usuario.

SELECT 
    R.ReporteId,
    R.Comentario,
    R.Fecha,
    R.Estado,
    U.NombreUser
FROM Reporte
JOIN Usuario U ON R.UsuarioId = U.UsuarioId
ORDER BY Fecha DESC;

--Select para ver todos los reportes de un usuario.

SELECT 
    R.ReporteId,
    R.Comentario,
    R.Fecha,
    R.Estado,
    U.NombreUser
FROM Reporte
JOIN Usuario U ON R.UsuarioId = U.UsuarioId
WHERE ;


-- Ver agentes y cuántos roles tienen
SELECT 
    P.Nombre,
    P.Apellidos,
    A.Especialidad,
    COUNT(R.RolId) AS TotalRoles
FROM Agente A
JOIN Persona P ON A.DNI = P.DNI
LEFT JOIN Rol R ON A.AgenteId = R.AgenteId
GROUP BY A.AgenteId, P.Nombre, P.Apellidos, A.Especialidad;


--Select para ver cuantas anomalias hay en cada instalación

SELECT 
    I.Nombre,
    COUNT(A.AnomaliaId) AS TotalAnomalias
FROM Instalacion I
LEFT JOIN Anomalia A ON I.InstalacionId = A.InstalacionId
GROUP BY Instalacion.InstalacionId, Instalacion.Nombre;

--Reportes sin anomalias detectadas

SELECT 
    r.ReporteId,
    r.Comentario,
    r.Estado,
    r.Fecha
FROM Reporte r
LEFT JOIN ReportaAnomalia ra ON r.ReporteId = ra.ReporteId
WHERE ra.AnomaliaId IS NULL;

--Reportes con anomalias detectadas

SELECT 
    r.ReporteId,
    r.Comentario,
    r.Estado,
    r.Fecha
FROM Reporte r
LEFT JOIN ReportaAnomalia ra ON r.ReporteId = ra.ReporteId
WHERE ra.AnomaliaId IS NOT NULL;


--Reportes con anomalias detectadas

SELECT 
    r.ReporteId,
    r.Comentario,
    r.Estado,
    r.Fecha
FROM Reporte r
LEFT JOIN ReportaAnomalia ra ON r.ReporteId = ra.ReporteId
WHERE ra.AnomaliaId = 1;


SELECT 
    a.AnomaliaId,
    a.Tipo,
    a.Descripcion,
    a.Peligrosidad,
    a.Estado,
    i.Nombre AS Instalacion,
    i.Ubicacion
FROM Anomalia a
JOIN Instalacion i 
    ON a.InstalacionId = i.InstalacionId
WHERE i.InstalacionId = 1;


