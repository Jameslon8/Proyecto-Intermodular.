--Select para ver todos los reportes de un usuario.

SELECT 
    R.ReporteId,
    R.Comentario,
    R.Fecha,
    R.Estado,
    U.NombreUser
FROM Reporte
JOIN Usuario U ON R.UsuarioId = U.UsuarioId
ORDER BY FechaReporte DESC;

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


-- 11. Ver agentes y cuántos roles tienen
SELECT 
    P.Nombre,
    P.Apellidos,
    A.Especialidad,
    COUNT(R.RolId) AS TotalRoles
FROM Agente A
JOIN Persona P ON Agente.DNI = Persona.DNI
LEFT JOIN Rol R ON Agente.AgenteId = Rol.AgenteId
GROUP BY A.AgenteId, P.Nombre, P.Apellidos, A.Especialidad;


--Select para ver cuantas anomalias hay en cada instalación

SELECT 
    I.Nombre,
    COUNT(A.AnomaliaId) AS TotalAnomalias
FROM Instalacion I
LEFT JOIN Anomalia A ON I.InstalacionId = A.InstalacionId
GROUP BY Instalacion.InstalacionId, Instalacion.Nombre;